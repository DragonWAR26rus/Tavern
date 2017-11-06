package ru.sfedu.tavern.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.entities.ClassType;
import ru.sfedu.tavern.entities.Entity;
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
            url      = ConfigurationUtil.getConfigurationEntry("database.url");
            login    = ConfigurationUtil.getConfigurationEntry("database.login");
            password = ConfigurationUtil.getConfigurationEntry("database.password");
            driver   = ConfigurationUtil.getConfigurationEntry("database.driver");
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
    
    public ResultSet execQuery (String query){
        if(con != null){
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)){
                return rs;
            } catch (Exception ex){
                log.info("Exception: " + ex);
                return null;
            }
        } else {
            openConnection();
            return null;
        }
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
