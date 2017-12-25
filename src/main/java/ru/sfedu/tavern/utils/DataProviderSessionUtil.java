package ru.sfedu.tavern.utils;

import java.io.IOException;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.dataproviders.CsvAPI;
import ru.sfedu.tavern.dataproviders.IDataProvider;
import ru.sfedu.tavern.dataproviders.JdbcAPI;
import ru.sfedu.tavern.dataproviders.XmlAPI;
import ru.sfedu.tavern.model.Constants;
import ru.sfedu.tavern.utils.ConfigurationUtil;

/**
 *
 * @author entropy
 */
public class DataProviderSessionUtil {

    
    private static Logger log = Logger.getLogger(DataProviderSessionUtil.class);
    private static DataProviderSessionUtil instance;
    private IDataProvider dataProvider;
    private static String dpName;
    
    private DataProviderSessionUtil() {
    }

    /**
     *
     * @return
     */
    public static DataProviderSessionUtil getInstance() {
        if(instance == null) init();
        return instance;
    }
    
    private static void init() {
        instance = new DataProviderSessionUtil();
    }
    
    /**
     *
     * @return
     */
    public IDataProvider getDataProvider() {
        if(dataProvider == null) setDataProvider();
        return dataProvider;
    }
    
    /**
     *
     */
    public void setDataProvider(){
        try {
            setDataProvider(ConfigurationUtil.getConfigurationEntry(Constants.CONFIG_DP_DRIVER));
        } catch (IOException ex) {
            log.warn(ex);
            setDataProvider("jdbc");
        }
    }
    
    /**
     *
     * @param driverName
     */
    public void setDataProvider(String driverName) {
        switch(driverName.toLowerCase()) { 
            case "xml":
                dataProvider = new XmlAPI();
                log.info("Data provider: XML");
                break;
            case "csv":
                dataProvider = new CsvAPI();
                log.info("Data provider: CSV");
                break;
            case "jdbc":
                dataProvider = new JdbcAPI();
                log.info("Data provider: JDBC");
                break;
            default:
                log.warn("There is no such driver");
                return;
        }
        try {
            dpName = driverName.toLowerCase();
            ConfigurationUtil.setConfigurationEntry("dataprovider.driver", driverName.toLowerCase());
        } catch (IOException ex) {
            log.error("Can't write property to config file");
        }
    }
    
    /**
     *
     * @return
     */
    public static String getDataProviderName() {
        return dpName;
    }
    
}
