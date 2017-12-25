package ru.sfedu.tavern.dataproviders;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.tavern.exception.RecordNotFoundException;
import ru.sfedu.tavern.exception.ThereAreDependentRecordsException;
import ru.sfedu.tavern.exception.ThereIsNoDependenceException;
import ru.sfedu.tavern.model.Constants;
import ru.sfedu.tavern.model.Result;
import ru.sfedu.tavern.model.StatusType;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.OurUser;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.utils.ConfigurationUtil;
import ru.sfedu.tavern.utils.CsvFilter;
import ru.sfedu.tavern.utils.XmlFilter;
import ru.sfedu.tavern.utils.ValidateUtil;

/**
 *
 * @author entropy
 */
public class CsvAPI implements IDataProvider{
    
    private static final Logger log = Logger.getLogger(CsvAPI.class);

    /**
     *
     */
    protected Serializer serializer = new Persister();

    /**
     *
     */
    protected Map<ClassType, Long> lastModifiedFileTime = new HashMap();

    /**
     *
     */
    protected Map<ClassType, List<Entity>> cachedLists = new HashMap();
    
    /**
     *
     */
    public CsvAPI() {
        lastModifiedFileTime.put(ClassType.OURUSER, Long.valueOf(0));
        lastModifiedFileTime.put(ClassType.PLATFORM, Long.valueOf(0));
        lastModifiedFileTime.put(ClassType.PLATFORMUSER, Long.valueOf(0));
        lastModifiedFileTime.put(ClassType.MESSAGE, Long.valueOf(0));
        cachedLists.put(ClassType.OURUSER, new ArrayList());
        cachedLists.put(ClassType.PLATFORM, new ArrayList());
        cachedLists.put(ClassType.PLATFORMUSER, new ArrayList());
        cachedLists.put(ClassType.MESSAGE, new ArrayList());
        checkAndRemoveAllWrongDependecies();
        log.debug("CSV data provider initialised...");
    }

    @Override
    public Result insert(List<Entity> iList) {
        List<Entity> savedRecords = new ArrayList();
        ClassType type = iList.get(0).getClassType();
        try {
            savedRecords.addAll(readFromFile(type));
        } catch (Exception ex) {
            log.warn(ex);
        }
        
        iList.stream().forEach(iter->{
            
            if(savedRecords.stream().noneMatch(e->(e.getId() == iter.getId())) && ValidateUtil.isUnique(this, Optional.ofNullable(iter)) && ValidateUtil.findDependencyUp(this, Optional.ofNullable(iter))){
                savedRecords.add(iter);
            } else {
                log.warn("Can't append record with type=" + type + " and id=" + iter.getId() + "!");
            }
        });        
        return writeToFile(savedRecords, type);
    }

    @Override
    public Result insert(Optional<Entity> optObject) throws Exception{
        if(!optObject.isPresent()) return new Result(StatusType.ERROR);
        Entity object = optObject.get();
        switch(object.getClassType()) {
            case OURUSER:
                try {
                    if(!select("login", ((OurUser)object).getLogin(), ClassType.OURUSER, false).isEmpty()){
                        throw new Exception("User with this login already exist.");
                    }
                }catch(Exception ex){
                    throw ex;
                }
                break;
                
            case PLATFORM:
                try {
                    if(!select("domain", ((Platform)object).getDomain(), ClassType.PLATFORM, false).isEmpty()){
                        throw new Exception("Platform with that domain already exist.");
                    }
                }catch(Exception ex){
                    throw ex;
                }
        }
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
        List<Entity> newDependencies = ValidateUtil.findDependencyDown(this, Optional.ofNullable(object));
        ClassType classType = object.getClassType();
        savedRecords.addAll(readFromFile(classType));
        Entity oldRecord = savedRecords.stream()
                .filter(e -> (e.getId() == object.getId()))
                .findAny()
                .get();
        oldDependencies.addAll(ValidateUtil.findDependencyDown(this, Optional.ofNullable(oldRecord)));
        if(!newDependencies.equals(oldDependencies)) {
            throw new ThereAreDependentRecordsException();
        }
        if(ValidateUtil.findDependencyUp(this, Optional.ofNullable(object))) {
            savedRecords.remove(oldRecord);
            savedRecords.add(object);
        } else {
            throw new ThereIsNoDependenceException();
        }
        
        return writeToFile(savedRecords, classType);
    }

    @Override
    public long getNextId(ClassType type) {
        List<Entity> list = new ArrayList();
        try {
            list.addAll(select(type));
        } catch(Exception ex) {
            return 1;
        }
        
        long maxId = 0;
        if(list.size() != 0) {
            maxId = list.stream().max((p1, p2) -> Long.valueOf(p1.getId()).compareTo(p2.getId())).get().getId();
        }
        return maxId + 1;
    }
    
    @Override
    public Result delete (List<Entity> objects) {
        if(objects.isEmpty()) return new Result(StatusType.OK);
        List<Entity> savedRecords = new ArrayList();        
        List<Entity> dependeny = new ArrayList();
        ClassType classType = objects.get(0).getClassType();
        try {
            savedRecords.addAll(readFromFile(classType));
        } catch (Exception ex) {
            log.error(ex);
            return new Result(StatusType.ERROR);
        }
        
        objects.stream().forEach(e -> {
            if(ValidateUtil.isUnique(this, Optional.ofNullable(e))) {
                dependeny.addAll(ValidateUtil.findDependencyDown(this, Optional.ofNullable(e)));
            }
        });
        
        savedRecords.removeAll(objects);
        Result res = writeToFile(savedRecords, classType);
        
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
            if(!records.isEmpty()) result = Optional.ofNullable(records.get(0));
        } catch ( Exception ex ) {
            log.error(ex);
        }
        
        return result;
    }
    
    /**
     *
     * @param type
     * @return
     * @throws Exception
     */
    public List<Entity> select(ClassType type) throws Exception{
        return readFromFile(type);
    }
    
    @Override
    public List<Entity> select(String col, String val, ClassType type) 
            throws Exception{
        List<Entity> result = new ArrayList();
        try {
            result.addAll(readFromFile(col, val, type));
        } catch (RecordNotFoundException e) {
            log.warn(e.getMessage());
        }
        return result;
    }
    
    /**
     *
     * @param col
     * @param val
     * @param type
     * @param needWarngs
     * @return
     */
    public List<Entity> select(String col, String val, ClassType type, boolean needWarngs){
        List<Entity> result = new ArrayList();
        try {
            result.addAll(readFromFile(col, val, type));
        } catch (RecordNotFoundException e) {
            if(needWarngs) log.warn(e.getMessage());
        } catch(Exception ex){
            log.error(ex.getMessage());
        }
        return result;
    }
    
    public void checkAndRemoveAllWrongDependecies() {
        log.info("Checking integrity...");
        List<Entity> globListToDel = new ArrayList();
        for(ClassType type : ClassType.values()) {
            log.info("Checking " + type + " integrity...");
            globListToDel.addAll(ValidateUtil.findWrongDependecies(this, type));
            delete(globListToDel);
            globListToDel.clear();
        }
    }

    /**
     *
     * @param classType
     * @return
     * @throws Exception
     */
    protected List<Entity> readFromFile(ClassType classType) throws Exception {
        return readFromFile(null, null, classType);
    }
    
    /**
     *
     * @param col
     * @param val
     * @param type
     * @return
     * @throws Exception
     */
    protected List<Entity> readFromFile(String col, String val, ClassType type) throws Exception {
        List<Entity> result = new ArrayList();
        CsvToBean ctb;
        try {
            String path = getFilePath(type);
            List<Entity> records;
            File fl = new File(path);
            if(!fl.exists()) {
                if(fl.createNewFile()){
                    log.warn("File " + path + " has been created");
                }
            }
            if(fl.lastModified() != lastModifiedFileTime.get(type)) {
                FileReader fr = new FileReader(fl);
                CsvFilter filter = new CsvFilter(getStrategy(type), col, val);
                records = new CsvToBeanBuilder(fr)
                        .withMappingStrategy(getStrategy(type))
                        .withType(type.getCl())
                        .withFilter(filter)
                        .build()
                        .parse();
                result.addAll(records);
                cachedLists.put(type, result);
                lastModifiedFileTime.put(type, fl.lastModified());
            } else {
                result.addAll(XmlFilter.filter(col, val, cachedLists.get(type), type));
            }
            
        } catch (Exception ex) {
            log.error(ex);            
            throw ex;
        }  
        return result;
    }

    /**
     *
     * @param classType
     * @return
     * @throws IOException
     */
    protected String getFilePath(ClassType classType) throws IOException {
        String dirPath = System.getProperty("user.home") + "/TavernFiles";
        File dkr = new File(dirPath);
        if(!dkr.exists()) dkr.mkdir();
        dirPath += ConfigurationUtil.getConfigurationEntry(Constants.CONFIG_PATH_TO_SCV);
        dkr = new File(dirPath);
        if(!dkr.exists()) dkr.mkdir();
        String filePath = dirPath.concat(classType.getFileName()).concat(".csv");
        return filePath;
    }

    /**
     *
     * @param list
     * @param type
     * @return
     */
    protected Result writeToFile(List<Entity> list, ClassType type) {
        try {
            String path = getFilePath(type);
            File fl = new File(path);
            if(!fl.exists()) {
                if(fl.createNewFile()){
                    log.warn("File " + path + " has been created");
                }
            }
            list.sort((p1, p2) -> Long.valueOf(p1.getId()).compareTo(p2.getId()));
            try(FileWriter fw = new FileWriter(fl, false)){
                StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(fw)
                        .withMappingStrategy(getStrategy(type))
                        .build();

                beanToCsv.write(list);
                cachedLists.put(type, list);
                lastModifiedFileTime.put(type,fl.lastModified());  
            }   
        } catch ( CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex ) {
            log.error("Ошибка записи: " + ex.getMessage());
            return new Result(StatusType.ERROR);
        } catch (Exception ex) {
            log.error(ex);
        }
        return new Result(StatusType.OK);
    }
    
    private ColumnPositionMappingStrategy getStrategy( ClassType type ) {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(type.getCl());
        strategy.setColumnMapping(type.getHeaders());
        return strategy;
    }

}
