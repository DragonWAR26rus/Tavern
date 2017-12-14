package ru.sfedu.tavern.dataproviders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.tavern.exception.ThereAreDependentRecordsException;
import ru.sfedu.tavern.exception.ThereIsNoDependenceException;
import ru.sfedu.tavern.model.Constants;
import ru.sfedu.tavern.model.Result;
import ru.sfedu.tavern.model.StatusType;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.EntityList;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.PlatformUser;
import ru.sfedu.tavern.utils.ConfigurationUtil;
import ru.sfedu.tavern.utils.XmlFilter;

/**
 *
 * @author entropy
 */
public class XmlAPI implements IDataProvider{

    
    private static Logger logger = Logger.getLogger(XmlAPI.class);
    private Serializer serializer = new Persister();
    
    
    public XmlAPI() {

    }

    @Override
    public Result insert(List<Entity> objects) throws Exception {
        List<Entity> savedRecords = new ArrayList();
        ClassType type = objects.get(0).getClassType();
        try {
            savedRecords.addAll(readFromXml(type));
        } catch (Exception ex) {
            logger.warn(ex);
        }
        
        objects.stream().forEach(iter->{
            if(savedRecords.stream().noneMatch(e->(e.getId() == iter.getId())) && findDependencyUp(iter)){
                savedRecords.add(iter);
            } else {
                logger.warn("Record with type=" + type + " and id=" + iter.getId() + " exists!");
            }
        });
        return writeToXml(savedRecords, type);
    }

    @Override
    public Result insert(Optional<Entity> optObject) throws Exception {
        if(!optObject.isPresent()) return new Result(StatusType.ERROR);
        Entity object = optObject.get();
        List<Entity> l = new ArrayList();
        l.add(object);
        return insert(l);   
    }

    @Override
    public Result update(Optional<Entity> optObject) throws Exception {
        if(!optObject.isPresent()) return new Result(StatusType.ERROR);
        Entity object = optObject.get();
        List<Entity> savedRecords = new ArrayList();
        List<Entity> oldDependencies = new ArrayList();
        List<Entity> newDependencies = findDependencyDown(object);
        ClassType type = object.getClassType();
        savedRecords.addAll(readFromXml(type));
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
        return writeToXml(savedRecords, type);    
    }

    @Override
    public Result delete (List<Entity> objects) {
        List<Entity> savedRecords = new ArrayList();        
        List<Entity> dependeny = new ArrayList();
        ClassType classType = objects.get(0).getClassType();
        savedRecords.addAll(readFromXml(classType));
        objects.stream().forEach(e -> {
            dependeny.addAll(findDependencyDown(e));
        });
        
        savedRecords.removeAll(objects);
        Result res = writeToXml(savedRecords, classType);
        
        if(!dependeny.isEmpty()) {
            delete(dependeny);
        }
        return res;
    }
    
    @Override
    public Result delete(Optional<Entity> optObject) throws Exception {
        if(!optObject.isPresent()) return new Result(StatusType.ERROR);
        Entity object = optObject.get();
        List<Entity> l = new ArrayList();
        l.add(object);
        return delete(l);  
    }

    @Override
    public Optional<Entity> getObjectByID(long id, ClassType type) {
        List<Entity> savedRecods = new ArrayList();
        Optional<Entity> record;
        savedRecods.addAll(readFromXml(type));
        record = savedRecods.stream().filter(e -> (e.getId() == id)).findAny();
        return record;
    }
    
    public List<Entity> select(ClassType type) throws Exception {
        return select(null, null, type);
    }

    @Override
    public List<Entity> select(String col, String val, ClassType type) throws Exception {
        List<Entity> savedRecords = new ArrayList();
        savedRecords.addAll(XmlFilter.filter(col, val, readFromXml(type), type));
        return savedRecords;
    }
    
    private Result writeToXml(List<Entity> list, ClassType type){
        try {
            File fr = new File(getFilePath(type));
            list.sort((p1, p2) -> Long.valueOf(p1.getId()).compareTo(p2.getId()));
            EntityList ls = new EntityList(list);
            serializer.write(ls, fr);
        } catch (Exception ex) {
            logger.error("Ошибка записи: " + ex.getMessage());
            return new Result(StatusType.ERROR);
        }
        return new Result(StatusType.OK);
       
    }
    
    private List<Entity> readFromXml(ClassType classType) {
        List<Entity> records = new ArrayList();
        try {
                File fr = new File(getFilePath(classType));
                EntityList entityList = serializer.read(EntityList.class, fr);
                records = entityList.getList();
        } catch(Exception ex) {

        }
        return records;
    }
    
    private String getFilePath(ClassType classType) throws IOException {
        logger.debug("getFilePath(" + classType.toString() + ")......");
        String dirPath = ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_XML);
        String filePath = dirPath.concat(classType.getFileName()).concat(".xml");
        logger.debug(filePath);
        return filePath;
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
    
}
