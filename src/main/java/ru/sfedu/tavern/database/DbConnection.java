package ru.sfedu.tavern.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.dataproviders.JdbcAPI;
import ru.sfedu.tavern.model.Constants;
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
public class DbConnection {  
    
    private static DbConnection instance;
    private static final Logger log = Logger.getLogger(DbConnection.class);
    
    private String insertPreStatement = "INSERT INTO %s (%s) VALUES %s;";
    private String selectPreStatement = "SELECT * FROM %s;";
    
    private static List<Entity> ourUserList;
    private static List<Entity> platformList;
    private static List<Entity> platformUserList;
    private static List<Entity> messageList;
    private static Savepoint savepoint;
    private static boolean transactionThere;
    
    private Connection con  = null;
    private String url;
    private String login;
    private String password;
    private String driver;
    
    private DbConnection() throws Exception{
        try{
            url      = ConfigurationUtil.getConfigurationEntry(Constants.DB_URL);
            login    = ConfigurationUtil.getConfigurationEntry(Constants.DB_LOGIN);
            password = ConfigurationUtil.getConfigurationEntry(Constants.DB_PASSWORD);
            driver   = ConfigurationUtil.getConfigurationEntry(Constants.DB_DRIVER);
        } catch ( Exception ex ) {
            log.info(ex.getMessage());
            throw ex;
        } 
        openConnection();
    }
    
    public void openConnection() {
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
    public void closeConnection(){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                log.info(ex);
            }
        }
    }
    
    public List<Entity> execQuery (String query, ClassType classType){
        List<Entity> result = new ArrayList();
        if(con != null){
            try (Statement stmt = con.createStatement(); ){
                try (ResultSet rs = stmt.executeQuery(query)){
                    ResultSetMetaData rsmd = rs.getMetaData();
                    while(rs.next()) {
                        Entity obj = null;
                        switch(classType) {
                            case OURUSER:
                                obj = new OurUser(
                                        rs.getLong(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4),
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
                                        rs.getString(4),
                                        rs.getBoolean(5),
                                        rs.getLong(6)
                                );
                                break;
                            case MESSAGE:
                                obj = new Message(
                                        rs.getLong(1),
                                        rs.getLong(2),
                                        rs.getString(3),
                                        rs.getString(4),
                                        rs.getLong(5)
                                );
                                break;
                        }
                        result.add(obj);
                    }
                } catch (Exception ex) {
                    
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
    
    // Thread safe singleton
    public static DbConnection getInstance() throws Exception{
        if(instance == null) {
            synchronized (DbConnection.class){
                if(instance == null){
                    try{
                        instance = new DbConnection();   
                    } catch (Exception ex) {
                        throw ex;
                    }
                }
            }
        }
        return instance;
    }
    
    public static void beginTransaction() {
        JdbcAPI jdbc = new JdbcAPI();
        try {
            ourUserList = jdbc.select(ClassType.OURUSER);
            platformList = jdbc.select(ClassType.PLATFORM);
            platformUserList = jdbc.select(ClassType.PLATFORMUSER);
            messageList = jdbc.select(ClassType.MESSAGE);
            transactionThere = true;
        }catch (Exception ex) {
            transactionThere = false;
        }
    }
    
    public static void commitTransaction() {
        ourUserList = null;
        platformList = null;
        platformUserList = null;
        messageList = null;
        transactionThere = false;
    }
    
    public static void rollbackTransaction() {
        JdbcAPI jdbc = new JdbcAPI();
        try {
            jdbc.select(ClassType.OURUSER).stream().forEach(iterator -> {
                try {
                    jdbc.delete(Optional.ofNullable(iterator));
                } catch (Exception ex){}
            });
            jdbc.insert((ArrayList)ourUserList);
            jdbc.insert((ArrayList)platformList);
            jdbc.insert((ArrayList)platformUserList);
            jdbc.insert((ArrayList)messageList);
            ourUserList = null;
            platformList = null;
            platformUserList = null;
            messageList = null;
            transactionThere = false;
        } catch (Exception ex) {
            
        }
    }
    
}
