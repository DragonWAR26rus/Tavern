
package ru.sfedu.tavern;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author entropy
 */
public class AppConfig {
 
    private static Logger log = Logger.getLogger(Tavern.class);
    private static Properties properties = null;
    
    private AppConfig(){}
    
    public static String getProperty( String key ){
        if(properties == null) {
            try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
                properties = new Properties();
                properties.load(fis);
            } catch( Exception ex ) {
                log.info(ex);
            }
        } 
        log.debug(key + " : " + properties.getProperty(key));
        return properties.getProperty(key);
    }

}
