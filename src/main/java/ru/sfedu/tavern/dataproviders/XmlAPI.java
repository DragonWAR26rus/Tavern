package ru.sfedu.tavern.dataproviders;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.tavern.model.Constants;
import ru.sfedu.tavern.model.Result;
import ru.sfedu.tavern.model.StatusType;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.EntityList;
import ru.sfedu.tavern.utils.ConfigurationUtil;

/**
 *
 * @author entropy
 */
public class XmlAPI implements IDataProvider{

    
    private static Logger logger = Logger.getLogger(CsvAPI.class);
    private Serializer serializer = new Persister();
    
    
    public XmlAPI() {

    }

    @Override
    public Result insert(ArrayList<Entity> object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result insert(Entity object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result update(Entity object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result delete(Entity object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Entity> getObjectByID(long id, ClassType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Entity> select(String col, String val, ClassType type) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Result writeToXml(List<Entity> list){
        try {
             ClassType classType = list.get(0).getClassType();
            File fr = new File(getFilePath(classType));
            serializer.write(classType.getCl(), fr);
        } catch (Exception ex) {
            logger.error("Ошибка записи: " + ex.getMessage());
            return new Result(StatusType.ERROR);
        }
        return new Result(StatusType.OK);
       
    }
    
    public List<Entity> readFromXml(ClassType classType) {
        List<Entity> records = new ArrayList();
        try {
                File fr = new File(getFilePath(classType));
                EntityList<Entity> entityList = null;
                entityList = (EntityList)serializer.read(classType.getCl(), fr);
                records = entityList.getList();
        } catch(Exception ex) {

        }
        return records;
    }
    
    public String getFilePath(ClassType classType) throws IOException {
        logger.debug("getFilePath(" + classType.toString() + ")......");
        String dirPath = ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_XML);
        String filePath = dirPath.concat(classType.getFileName()).concat(".xml");
        logger.debug(filePath);
        return filePath;
    }
    
}
