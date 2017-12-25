package ru.sfedu.tavern.dataproviders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.model.Constants;
import ru.sfedu.tavern.model.Result;
import ru.sfedu.tavern.model.StatusType;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.OurUser;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.PlatformUser;
import ru.sfedu.tavern.utils.ConfigurationUtil;

/**
 *
 * @author entropy
 */
public class JdbcAPI implements IDataProvider{

    /**
     *
     * @param col
     * @param val
     * @param type
     * @param needWarngs
     * @return
     */
    @Override
    public List<Entity> select(String col, String val, ClassType type, boolean needWarngs) {
        try {   
            return select(col, val, type);
        } catch (Exception ex){ 
            return new ArrayList();
        }
    }

    
    private static Logger log = Logger.getLogger(JdbcAPI.class);
    
    private static List<Entity> ourUserList;
    private static List<Entity> platformList;
    private static List<Entity> platformUserList;
    private static List<Entity> messageList;
    private static boolean transactionThere;
    
    private Connection con  = null;
    private String url;
    private String login;
    private String password;
    private String driver;
        
    /**
     *
     */
    public JdbcAPI() {
        try{
            url      = ConfigurationUtil.getConfigurationEntry(Constants.CONFIG_DB_URL);
            login    = ConfigurationUtil.getConfigurationEntry(Constants.CONFIG_DB_LOGIN);
            password = ConfigurationUtil.getConfigurationEntry(Constants.CONFIG_DB_PASSWORD);
            driver   = ConfigurationUtil.getConfigurationEntry(Constants.CONFIG_DB_DRIVER);
        } catch ( Exception ex ) {
            log.info(ex.getMessage());
        } 
        openConnection();
        
    }
    
    private void openConnection() {
        log.info("Trying to open DB connection...");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, login, password);
            log.info("DB connection opened!");
        } catch (Exception ex) {
            log.info("Error connection: " + ex);
        } 
    }
    
    // Close connection

    /**
     *
     */
    public void closeConnection(){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                log.info(ex);
            }
        }
    }

    @Override
    public Result insert(List<Entity> object) throws Exception {
        ClassType classType = object.get(0).getClassType();
        try {
            object.stream().forEach(iterator -> {
                String query = "INSERT INTO " + classType.getTableName() + "(" + classType.getHeaderString() + ") VALUES (" + iterator.toString(true) + ");";
                execQuery(query, classType);
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
            execQuery("UPDATE " + classType.getTableName() + " " + setExpGen(Optional.ofNullable(object)) + " WHERE id=" + object.getId() + ";", classType);   
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
            execQuery("DELETE FROM " + classType.getTableName() + " WHERE id=" + object.getId() + ";", classType);   
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
                log.warn(ex);
            }
        });
        return new Result(StatusType.OK);
    }

    @Override
    public Optional<Entity> getObjectByID(long id, ClassType type) {
        Optional<Entity> result = Optional.empty();
        List<Entity> list = new ArrayList();
        try {
            list.addAll(select("id", String.valueOf(id), type));
        }catch(Exception ex) {
            log.error(ex);
        }
        
        if(!list.isEmpty()) result = Optional.ofNullable(list.get(0));
        return result;
    }

    /**
     *
     * @param type
     * @return
     * @throws Exception
     */
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
        result.addAll(execQuery("SELECT * FROM " + type.getTableName() + whereCond + ";", type));
        return result;
    }

    private String setExpGen(Optional<Entity> optObject) {
        if(!optObject.isPresent()) return "";
        Entity obj = optObject.get();
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
        long maxId = 0;
        Optional<Entity> entity = list.stream().max((p1, p2) -> Long.valueOf(p1.getId()).compareTo(p2.getId()));
        if(entity.isPresent()) {
            maxId = entity.get().getId();
        }
        
        return maxId + 1;
    }

    @Override
    public void checkAndRemoveAllWrongDependecies() {
        return;
    }
    
    /**
     *
     * @param query
     * @param type
     * @return
     */
    public List<Entity> execQuery (String query, ClassType type){
        List<Entity> result = new ArrayList();
        if(con != null){
            try (Statement stmt = con.createStatement(); ){
                try{
                    ResultSet rs = stmt.executeQuery(query);
                    while(rs.next()) {
                        Entity obj = null;
                        switch(type) {
                            case OURUSER:
                                obj = new OurUser(
                                        rs.getLong(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getLong(4),
                                        rs.getString(5)
                                );
                                break;
                            case PLATFORM:
                                obj = new Platform(
                                        rs.getLong(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getLong(4)  
                                );
                                break;
                            case PLATFORMUSER:
                                obj = new PlatformUser(
                                        rs.getLong(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getLong(4),
                                        rs.getBoolean(5),
                                        rs.getLong(6)
                                );
                                break;
                            case MESSAGE:
                                obj = new Message(
                                        rs.getLong(1),
                                        rs.getLong(2),
                                        rs.getLong(4),
                                        rs.getString(3),
                                        rs.getLong(5)
                                );
                                break;
                        }
                        result.add(obj);
                    }
                } catch (Exception ex) {
//                    log.warn("Exception: " + ex);
                }
            } catch (Exception ex){
                log.warn("Exception: " + ex);
                return result;
            }
        } else {
            openConnection();
        }
        return result;
    }
    
}
