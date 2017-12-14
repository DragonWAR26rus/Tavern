
package ru.sfedu.tavern.dataproviders;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.exception.RecordNotFoundException;
import ru.sfedu.tavern.exception.ThereAreDependentRecordsException;
import ru.sfedu.tavern.exception.ThereIsNoDependenceException;
import ru.sfedu.tavern.model.Constants;
import ru.sfedu.tavern.model.Result;
import ru.sfedu.tavern.model.StatusType;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.PlatformUser;
import ru.sfedu.tavern.utils.ConfigurationUtil;
import ru.sfedu.tavern.utils.CsvFilter;

/**
 *
 * @author entropy
 */
public class CsvAPI implements IDataProvider{
    
    private static Logger logger = Logger.getLogger(CsvAPI.class);

    public CsvAPI() {}

    @Override
    public Result insert(List<Entity> objectList) {
        List<Entity> savedRecords = new ArrayList();
        ClassType type = objectList.get(0).getClassType();
        try {
            savedRecords.addAll(readFromCsv(type));
        } catch (Exception ex) {
            logger.warn(ex);
        }
        
        objectList.stream().forEach(iter->{
            if(savedRecords.stream().noneMatch(e->(e.getId() == iter.getId())) && findDependencyUp(iter)){
                savedRecords.add(iter);
            } else {
                logger.warn("Record with type=" + type + " and id=" + iter.getId() + " exists!");
            }
        });        
        return writeToCsv(savedRecords, type);
    }

    @Override
    public Result insert(Optional<Entity> optObject) {
        if(!optObject.isPresent()) return new Result(StatusType.ERROR);
        Entity object = optObject.get();
        List<Entity> l = new ArrayList();
        l.add(object);
        return insert(l);
    }

    @Override
    public Result update(Optional<Entity> optObject) throws Exception{
        if(!optObject.isPresent()) return new Result(StatusType.ERROR);
        Entity object = optObject.get();
        List<Entity> savedRecords = new ArrayList();
        List<Entity> oldDependencies = new ArrayList();
        List<Entity> newDependencies = findDependencyDown(object);
        ClassType classType = object.getClassType();
        savedRecords.addAll(readFromCsv(classType));
        Entity oldRecord = savedRecords.stream()
                .filter(e -> (e.getId() == object.getId()))
                .findAny()
                .get();
        oldDependencies.addAll(findDependencyDown(oldRecord));
        if(!newDependencies.equals(oldDependencies)) {
            throw new ThereAreDependentRecordsException();
        }
        if(findDependencyUp(object)) {
            savedRecords.remove(oldRecord);
            savedRecords.add(object);
        } else {
            throw new ThereIsNoDependenceException();
        }
        
        return writeToCsv(savedRecords, classType);
    }

    @Override
    public long getNextId(ClassType type) {
        List<Entity> list = new ArrayList();
        try {
            list.addAll(select(type));
        } catch(Exception ex) {
            return 1;
        }
        long maxId = list.stream().max((p1, p2) -> Long.valueOf(p1.getId()).compareTo(p2.getId())).get().getId();
        return maxId + 1;
    }
    
    @Override
    public Result delete (List<Entity> objects) {
        
        List<Entity> savedRecords = new ArrayList();        
        List<Entity> dependeny = new ArrayList();
        ClassType classType = objects.get(0).getClassType();
        try {
            savedRecords.addAll(readFromCsv(classType));
        } catch (Exception ex) {
            logger.error(ex);
            return new Result(StatusType.ERROR);
        }
        
        objects.stream().forEach(e -> {
            dependeny.addAll(findDependencyDown(e));
        });
        
        savedRecords.removeAll(objects);
        Result res = writeToCsv(savedRecords, classType);
        
        if(!dependeny.isEmpty()) {
            delete(dependeny);
        }
        return res;
    }

    @Override
    public Result delete(Optional<Entity> optObject) {
        if(!optObject.isPresent()) return new Result(StatusType.ERROR);
        Entity object = optObject.get();
        List<Entity> l = (new ArrayList());
        l.add(object);
        return delete(l);
    }

    @Override
    public Optional<Entity> getObjectByID(long id, ClassType type) {
        Optional<Entity> result = Optional.empty();
        try {
            List<Entity> records;
            records = select("id", String.valueOf(id), type);
            result = Optional.ofNullable(records.get(0));
        } catch ( Exception ex ) {
            logger.error(ex);
        }
        
        return result;
    }
    
    private Result writeToCsv(List<Entity> list, ClassType type) {
        try {
            String path = getFilePath(type);
            list.sort((p1, p2) -> Long.valueOf(p1.getId()).compareTo(p2.getId()));
            try (FileWriter fw = new FileWriter(path, false)) {
                StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(fw)
                        .withMappingStrategy(getStrategy(list.get(0).getClassType()))
                        .build();
                
                beanToCsv.write(list);
            }
        } catch ( CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex ) {
            logger.error("Ошибка записи: " + ex.getMessage());
            return new Result(StatusType.ERROR);
        }
        return new Result(StatusType.OK);
    }
    
    private List<Entity> readFromCsv(ClassType classType) 
            throws Exception {
        return readFromCsv(null, null, classType);
    }
    
    private String getFilePath(ClassType classType) throws IOException {
        logger.debug("getCsvFile(" + classType.toString() + ")......");
        String dirPath = ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_SCV);
        String filePath = dirPath.concat(classType.getFileName()).concat(".csv");
        logger.debug(filePath);
        return filePath;
    }
    
    private ColumnPositionMappingStrategy getStrategy( ClassType type ) {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(type.getCl());
        strategy.setColumnMapping(type.getHeaders());
        return strategy;
    }
    
    // =======================================================================
    
    public List<Entity> select(ClassType type) throws Exception{
        return readFromCsv(type);
    }
    
    @Override
    public List<Entity> select(String col, String val, ClassType type) 
            throws Exception{
        List<Entity> result;
        try {
            result = readFromCsv(col, val, type);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return result;
    }
    
    private List<Entity> readFromCsv(String col, String val, ClassType classType) 
            throws Exception {
        List<Entity> result;
        CsvToBean ctb;
        try {
            String path = getFilePath(classType);
            List<Entity> records;
            try (FileReader fr = new FileReader(path)) {
                CsvFilter filter = new CsvFilter(getStrategy(classType), col, val);
                records = new CsvToBeanBuilder(fr)
                        .withMappingStrategy(getStrategy(classType))
                        .withType(classType.getCl())
                        .withFilter(filter)
                        .build()
                        .parse();
            }
            if(records.isEmpty()) throw new RecordNotFoundException();
            result = records;
            
        } catch (IOException | IllegalStateException | RecordNotFoundException ex) {
            logger.error(ex);            
            throw ex;
        }  
        return result;
    }
    
    private boolean findDependencyUp(Entity obj) {
        switch (obj.getClassType()) {
            case PLATFORM:
                try{
                    return !select("id", String.valueOf(((Platform)obj).getOwnerId()), ClassType.OURUSER).isEmpty();
                } catch(Exception ex) {
                    return false;
                }
            case PLATFORMUSER:
                try{
                    return !select("id", String.valueOf(((PlatformUser)obj).getPlatformId()), ClassType.PLATFORM).isEmpty();
                } catch(Exception ex) {
                    return false;
                }
            case MESSAGE:
                try {
                    return !select("id", String.valueOf(((Message)obj).getSenderId()), ClassType.PLATFORMUSER).isEmpty();
                } catch(Exception ex) {
                    return false;
                }
        }
        return true;
    }
    
    private List<Entity> findDependencyDown(Entity obj) {
        List<Entity> findedEls = new ArrayList();
        switch (obj.getClassType()) {
            case OURUSER:
                try {
                    findedEls.addAll(select("ownerId", String.valueOf(obj.getId()), ClassType.PLATFORM));
                } catch(Exception ex) {
                    logger.warn(ex);
                }
                break;
            case PLATFORM:
                try{
                    findedEls.addAll(select("platformId", String.valueOf(obj.getId()), ClassType.PLATFORMUSER));
                } catch(Exception ex) {
                    logger.warn(ex);
                }
                break;
            case PLATFORMUSER:
                try{
                    findedEls.addAll(select("senderId", String.valueOf(obj.getId()), ClassType.MESSAGE));    
                } catch(Exception ex) {
                    logger.warn(ex);
                }
               
                break;
        }
        return findedEls;
    }

}
