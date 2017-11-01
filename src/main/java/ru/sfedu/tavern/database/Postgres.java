package ru.sfedu.tavern.database;

import java.sql.*;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.AppConfig;

/**
 *
 * @author entropy
 */
public class Postgres {  
    
    private static Postgres instance;
    private static final Logger log = Logger.getLogger(Postgres.class);
    
    private Connection con  = null;
    private String url      = AppConfig.getProperty("database.url");
    private String login    = AppConfig.getProperty("database.login");
    private String password = AppConfig.getProperty("database.password");
    private String driver   = AppConfig.getProperty("database.driver");
    
    private Postgres(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, login, password);
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
    
    public void createStatement(String query){
        if(con != null){
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)){
                while(rs.next()){
                    String str = rs.getString("name");
                    log.info("String in row: " + str);
                }
            } catch (Exception ex){
                log.info("Exception: " + ex);
            }
        }
    }
    
    // Thread safe singleton
    public static Postgres getInstance(){
        if(instance == null) {
            synchronized (Postgres.class){
                if(instance == null){
                    instance = new Postgres();       
                }
            }
        }
        return instance;
    }
    
    
}
