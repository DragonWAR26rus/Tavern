package ru.sfedu.tavern;

import org.apache.log4j.Logger;
import ru.sfedu.tavern.utils.CliUtil;

/**
 *
 * @author entropy
 */
public class Tavern {
    
    private static Logger log = Logger.getLogger(Tavern.class);
    
    public Tavern(){
        log.debug("Tavern[0]: starting application............");
        logBasicSystemInfo();
    }
    
    private void logBasicSystemInfo() {
        log.info("Launching the application...");
        log.info(
                "Operating system: " + System.getProperty("os.name") + " "
                + System.getProperty("os.version")
        );
        log.info("JRE: " + System.getProperty("java.version"));
        log.info("Java Launched From: " + System.getProperty("java.home"));
        log.info("Class Path: " + System.getProperty("java.class.path"));
        log.info("Library Path: " + System.getProperty("java.library.path"));
        log.info("User Home Directory: " + System.getProperty("user.home"));
        log.info("User Working Directory: " + System.getProperty("user.dir"));
        log.info("Test INFO logging.");
    }
    
    public static void main(String []args) {
       if(CliUtil.authorize(args)){
           CliUtil.remoteControl();
       }
    }
    
}
