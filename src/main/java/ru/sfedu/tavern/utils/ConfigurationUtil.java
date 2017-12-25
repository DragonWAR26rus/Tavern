package ru.sfedu.tavern.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Configuration utility. Allows to get configuration properties from the
 * default configuration file
 *
 * @author Boris Jmailov
 */
public class ConfigurationUtil {

//    private static final String DEFAULT_CONFIG_PATH = "./src/main/resources/config.properties";
    private static final String DEFAULT_CONFIG_PATH = "config.properties";
    private static final String SYSTEM_PROP_PATH = "tavern.file.path";
    private static final Properties configuration = new Properties();
    /**
     * Hides default constructor
     */
    private ConfigurationUtil() {
    }

    
    private static Properties getConfiguration() throws IOException {
        if(configuration.isEmpty()){
            loadConfiguration();
        }
        return configuration;
    }

    /**
     * Loads configuration from <code>DEFAULT_CONFIG_PATH</code>
     * @throws IOException In case of the configuration file read failure
     */
    private static void loadConfiguration() throws IOException{
        try{
            if(System.getProperty(SYSTEM_PROP_PATH) == null) {
                ClassLoader CL = ConfigurationUtil.class.getClassLoader();
                configuration.load(CL.getResourceAsStream(DEFAULT_CONFIG_PATH));
            } else {
                File nf = new File(System.getProperty(SYSTEM_PROP_PATH));
                InputStream in = new FileInputStream(nf);
                configuration.load(in);
            }
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }
    /**
     * Gets configuration entry value
     * @param key Entry key
     * @return Entry value by key
     * @throws IOException In case of the configuration file read failure
     */
    public static String getConfigurationEntry(String key) throws IOException{
        return getConfiguration().getProperty(key);
    }
    
    /**
     * Sets configuration entry value
     * @param key Entry key
     * @param value Entry value
     * @throws IOException In case of the configuration file read failure
     */
    public static void setConfigurationEntry(String key, String value) throws IOException{
        getConfiguration().setProperty(key, value);
    }
    
}
