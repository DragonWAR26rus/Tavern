package ru.sfedu.tavern.dataproviders;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.animation.Animation;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import ru.sfedu.tavern.database.DbConnection;
import ru.sfedu.tavern.model.Result;
import ru.sfedu.tavern.model.StatusType;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.OurUser;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.PlatformUser;

/**
 *
 * @author entropy
 */
public class JdbcAPI implements IDataProvider{

    
    private static Logger logger = Logger.getLogger(CsvAPI.class);
    private DbConnection instance;
        
    public JdbcAPI() {
        try {
            instance = DbConnection.getInstance();
        } catch (Exception ex) {
            logger.error(ex);
        }
        
    }

    @Override
    public Result insert(List<Entity> object) throws Exception {
        ClassType classType = object.get(0).getClassType();
        try {
            object.stream().forEach(iterator -> {
                String query = "INSERT INTO " + classType.getTableName() + "(" + classType.getHeaderString() + ") VALUES (" + iterator.toString(true) + ");";
                instance.execQuery(query, classType);
            });
        } catch (Exception ex) {
            return new Result(StatusType.ERROR);
        }
        return new Result(StatusType.OK);
    }

    @Override
    public Result insert(Optional<Entity> optObject) throws Exception {
        if(!optObject.isPresent()) return new Result(StatusType.ERROR);
        Entity object = optObject.get();
        ArrayList<Entity> objList = new ArrayList();
        objList.add(object);
        return insert(objList);
    }

    @Override
    public Result update(Optional<Entity> optObject) throws Exception {
        if(!optObject.isPresent()) return new Result(StatusType.ERROR);
        Entity object = optObject.get();
        ClassType classType = object.getClassType();
        try {
            instance.execQuery("UPDATE " + classType.getTableName() + " " + setExpGen(object) + " WHERE id=" + object.getId() + ";", classType);   
        } catch (Exception ex) {
            return new Result(StatusType.ERROR);
        }
        return new Result(StatusType.OK);
    }

    @Override
    public Result delete(Optional<Entity> optObject) throws Exception {
        if(!optObject.isPresent()) return new Result(StatusType.ERROR);
        Entity object = optObject.get();
        ClassType classType = object.getClassType();
        try {
            instance.execQuery("DELETE FROM " + classType.getTableName() + " WHERE id=" + object.getId() + ";", classType);   
        } catch (Exception ex) {
            return new Result(StatusType.ERROR);
        }
        return new Result(StatusType.OK);
    }

    @Override
    public Result delete(List<Entity> objects) throws Exception {
        objects.stream().forEach(e -> {
            try{
                delete(Optional.ofNullable(e));
            } catch (Exception ex) {
                logger.warn(ex);
            }
        });
        return new Result(StatusType.OK);
    }

    @Override
    public Optional<Entity> getObjectByID(long id, ClassType type) {
        Optional<Entity> result;
        List<Entity> list = new ArrayList();
        try {
            list = select("id", String.valueOf(id), type);
        }catch(Exception ex) {
            logger.error(ex);
        }
        
        result = Optional.ofNullable(list.get(0));
        return result;
    }

    public List<Entity> select(ClassType type) throws Exception {
        return select(null, null, type);
    }
        
    @Override
    public List<Entity> select(String col, String val, ClassType type) throws Exception {
        String whereCond = "";
        if(col != null && val != null) {
            whereCond = " WHERE " + col + "=" + val;
        }
        
        List<Entity> result = new ArrayList();
        result = instance.execQuery("SELECT * FROM " + type.getTableName() + whereCond + ";", type);
        return result;
    }

    private String setExpGen(Entity obj) {
        String res = "";
        switch(obj.getClassType()) {
            case MESSAGE:
                Message mes = (Message) obj;
                res = " SET id=" + mes.getId() + 
                      ",senderId=" + mes.getSenderId() +
                      ",text='" + mes.getText() + '\'' +
                      ",sendTime='" + mes.getSendTime() + '\'' +
                      ",platformId=" + mes.getPlatformId();
                break;
            case OURUSER:
                OurUser ou = (OurUser) obj;
                res = " SET id=" + ou.getId() + 
                      ",login='" + ou.getLogin() + '\'' +
                      ",passwordHash='" + ou.getPasswordHash() + '\'' +
                      ",email='" + ou.getEmail() + '\'' +
                      ",lastAct='" + ou.getLastAct() + '\'';
                break;
            case PLATFORM:
                Platform pl = (Platform) obj;
                res = " SET id=" + pl.getId() + 
                      ",domain='" + pl.getDomain() + '\'' +
                      ",key='" + pl.getKey() + '\'' +
                      ",ownerId=" + pl.getOwnerId();
                break;
            case PLATFORMUSER:
                PlatformUser pu = (PlatformUser) obj;
                res = " SET id=" + pu.getId() + 
                      ",login='" + pu.getLogin() + '\'' +
                      ",avatarLink='" + pu.getAvatarLink() + '\'' +
                      ",lastAct='" + pu.getLastAct() + '\'' +
                      ",banned=" + pu.getBanned() +
                      ",platformId=" + pu.getPlatformId();
                break;
        }
        return res;
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
