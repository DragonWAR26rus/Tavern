
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
import ru.sfedu.tavern.model.Constants;
import ru.sfedu.tavern.model.Result;
import ru.sfedu.tavern.model.StatusType;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
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
    public Result insert(ArrayList<Entity> objectList) {
        logger.debug("CSV->INSERT(ARRAYLIST<ENTITY>)....");
        List<Entity> savedRecords = null;
        ClassType classType = objectList.get(0).getClassType();
        try {
            savedRecords = readFromCsv(classType);
        } catch (Exception ex) {
            logger.error(ex);
            return new Result(StatusType.ERROR);
        }
        
        if(savedRecords == null) savedRecords = new ArrayList();
        for(Entity obj: objectList) {
            logger.debug(obj.toString());
            if(getObjectByID(obj.getId(), obj.getClassType()) == null){
                savedRecords.add(obj);
            }
        }
        
        return writeToCsv(savedRecords);
    }

    @Override
    public Result insert(Entity object) {
        logger.debug("CSV->INSERT(ENTITY)....");
        List<Entity> savedRecords = null;
        ClassType classType = object.getClassType();
        try {
            savedRecords = readFromCsv(classType);
        } catch (Exception ex) {
            logger.error(ex);
            return new Result(StatusType.ERROR);
        }
        
        if(savedRecords == null) savedRecords = new ArrayList();
        if(getObjectByID(object.getId(), object.getClassType()) == null){
            savedRecords.add(object);
        }
        
        return writeToCsv(savedRecords);
    }

    @Override
    public Result update(Entity updateableObject) {
        ArrayList<Entity> savedRecords = null;
        ClassType classType = updateableObject.getClassType();
        try {
            savedRecords = (ArrayList<Entity>) readFromCsv(classType);
        } catch (Exception ex) {
            logger.error(ex);
            return new Result(StatusType.ERROR);
        }
        
        if(savedRecords == null) savedRecords = new ArrayList();
        for(Entity obj: savedRecords) {
            if(obj.getId() == updateableObject.getId()){
                savedRecords.remove(obj);
                savedRecords.add(updateableObject);
                break;
            }
        }
        
        return writeToCsv(savedRecords);
    }

    @Override
    public Result delete(Entity removeableObject) {
        List<Entity> savedRecords = null;
        ClassType classType = removeableObject.getClassType();
        try {
            savedRecords = readFromCsv(classType);
        } catch (Exception ex) {
            logger.error(ex);
            return new Result(StatusType.ERROR);
        }
        
        if(savedRecords == null) savedRecords = new ArrayList();
        for(Entity obj: savedRecords) {
            if(obj.getId() == removeableObject.getId()){
                savedRecords.remove(obj);
                break;
            }
        }
        
        return writeToCsv(savedRecords);
    }

    @Override
    public Optional<Entity> getObjectByID(long id, ClassType type) {
        Optional<Entity> result = Optional.empty();
        try {
            List<Entity> records;
            records = readFromCsv(type);
            for(Entity obj: records) {
                if( obj.getId() == id ) {
                    records.clear();
                    records.add(obj);
                    break;
                };
                records.remove(obj);
            }
            result = Optional.ofNullable(records.get(0));
        } catch ( Exception ex ) {
            logger.error(ex);
        }
        
        return result;
    }
    
    private Result writeToCsv(List<Entity> list) {
        try {
            String path = getFilePath(list.get(0).getClassType());
            
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
    
    public String getFilePath(ClassType classType) throws IOException {
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
    
    public List<Entity> select(String col, String val, ClassType type) 
            throws Exception{
        List<Entity> result;
//        CSVReader reader = new CSVReader(new FileReader(getFilePath(type)));
        try {
            result = readFromCsv(col, val, type);
//            CsvToBean ctb = new CsvToBean();
//            CsvFilter filter = new CsvFilter(getStrategy(type), col, val);
//            List<Entity> list = ctb.parse(getStrategy(type), reader, filter);
//            if(list.isEmpty()) throw new RecordNotFoundException(0);
//            result = Optional.ofNullable(list);
//            reader.close();
//        } catch (RecordNotFoundException e){
//            logger.trace(e.getMessage());
//            throw e;
        } catch (Exception e) {
//            reader.close();
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
            if(records.isEmpty()) throw new RecordNotFoundException(0);
            result = records;
            
        } catch (IOException | IllegalStateException | RecordNotFoundException ex) {
            logger.error(ex);            
            throw ex;
        }  
        return result;
    }

}
