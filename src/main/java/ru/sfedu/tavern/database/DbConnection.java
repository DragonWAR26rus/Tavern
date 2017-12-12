package ru.sfedu.tavern.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
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
    
    public ArrayList<ArrayList<Object>> execSelectState( DbMetaTables tableName ){
        if(con == null){
            openConnection();
        }
        ArrayList<ArrayList<Object>> resMatrix = new ArrayList<ArrayList<Object>>();
        try( Statement stmt = con.createStatement() ) {
            String query = String.format(selectPreStatement, tableName.getTableName());
            log.debug(query);
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            log.debug("TABLE: " + tableName.getTableName());

            while(rs.next()){
                ArrayList<Object> resRow = new ArrayList();
                String row = "";
                for( int i = 0; i < rsmd.getColumnCount(); i++){
                    Object value = rs.getObject(i + 1);
                    String strValue = value == null ? "null" : value.toString();
                    log.debug(
                            "Row number: " + rs.getRow() + 
                            " Column: " + (i + 1) + 
                            " Column name: " + rsmd.getColumnName(i + 1) +
                            "\tColumn type: " + rsmd.getColumnTypeName(i + 1) + 
                            " Value: " + strValue
                    );
                    resRow.add(value);

                    row += strValue;
                    row += "\t";                    
                }
                log.debug(row);
                resMatrix.add(resRow);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex){
            log.info("Exception: " + ex);
        } finally {
            return resMatrix;
        }        
    }
    
    public List<Entity> execQuery (String query, ClassType classType){
        List<Entity> result = new ArrayList();
        if(con != null){
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)){
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
    
    
}
