
package ru.sfedu.tavern.dataproviders;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.Constants;
import ru.sfedu.tavern.entities.ClassType;
import ru.sfedu.tavern.entities.Entity;
import ru.sfedu.tavern.utils.ConfigurationUtil;

/**
 *
 * @author entropy
 */
public class CsvTools implements IDataProvider{
    
    private static Logger log = Logger.getLogger(CsvTools.class);

    public CsvTools() {}

    @Override
    public void insert(ArrayList<Entity> objectList) {
        log.debug("CSV->INSERT(ARRAYLIST<ENTITY>)....");
        ArrayList<Entity> savedRecords = null;
        ClassType classType = objectList.get(0).getClassType();
        try {
            savedRecords = (ArrayList<Entity>) readFromCsv(classType);
        } catch (Exception ex) {
            log.error(ex);
        }
        
        if(savedRecords == null) savedRecords = new ArrayList();
        for(Entity obj: objectList) {
            log.debug(obj.toString());
            if(getObjectByID(obj.getId(), obj.getClassType()) == null){
                savedRecords.add(obj);
            }
        }
        
        writeToCsv(savedRecords);
    }

    @Override
    public void insert(Entity object) {
        ArrayList<Entity> savedRecords = null;
        ClassType classType = object.getClassType();
        try {
            savedRecords = (ArrayList<Entity>) readFromCsv(classType);
        } catch (Exception ex) {
            log.error(ex);
        }
        
        if(savedRecords == null) savedRecords = new ArrayList();
        if(getObjectByID(object.getId(), object.getClassType()) == null){
            savedRecords.add(object);
        }
        
        writeToCsv(savedRecords);
    }

    @Override
    public void update(Entity updateableObject) {
        ArrayList<Entity> savedRecords = null;
        ClassType classType = updateableObject.getClassType();
        try {
            savedRecords = (ArrayList<Entity>) readFromCsv(classType);
        } catch (Exception ex) {
            log.error(ex);
        }
        
        if(savedRecords == null) savedRecords = new ArrayList();
        for(Entity obj: savedRecords) {
            if(obj.getId() == updateableObject.getId()){
                savedRecords.remove(obj);
                savedRecords.add(updateableObject);
                break;
            }
        }
        
        writeToCsv(savedRecords);
    }

    @Override
    public void delete(Entity removeableObject) {
        ArrayList<Entity> savedRecords = null;
        ClassType classType = removeableObject.getClassType();
        try {
            savedRecords = (ArrayList<Entity>) readFromCsv(classType);
        } catch (Exception ex) {
            log.error(ex);
        }
        
        if(savedRecords == null) savedRecords = new ArrayList();
        for(Entity obj: savedRecords) {
            if(obj.getId() == removeableObject.getId()){
                savedRecords.remove(obj);
                break;
            }
        }
        
        writeToCsv(savedRecords);
    }

    @Override
    public Entity getObjectByID(long id, ClassType type) {
        Entity target = null;
        try {
            List<Entity> records;
            records = readFromCsv(type);
            for(Entity obj: records) {
                if( obj.getId() == id ) {
                    target = obj;
                    break;
                };
            }
        } catch ( Exception ex ) {
            log.error(ex);
        }
        
        return target;
    }
    
    private void writeToCsv(List<Entity> list) {
        try {
            String path = getCsvFile(list.get(0).getClassType());
            
            FileWriter fw = new FileWriter(path, false);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(fw)
                    .withMappingStrategy(getStrategy(list.get(0).getClassType()))
                    .build();
            
            beanToCsv.write(list);
            fw.close();
        } catch ( CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex ) {
            log.error("Ошибка записи: " + ex.getMessage());
        }
    }
    
    private List<Entity> readFromCsv(ClassType classType){
        
        try {
            String path = getCsvFile(classType);
            FileReader fr = new FileReader(path);
            List<Entity> records; 
            records = new CsvToBeanBuilder(fr)
                    .withMappingStrategy(getStrategy(classType))
                    .withType(classType.getCl())
                    .build()
                    .parse();
            
            fr.close();
            return records;
        } catch (Exception ex) {
            log.error("Ошибка чтения: " + ex.getMessage());
            return null;
        }        
    }
    
    private String getCsvFile(ClassType classType) throws IOException {
        log.debug("getCsvFile(" + classType.toString() + ")......");
        String dirPath = ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_SCV);
        String filePath = dirPath.concat(classType.toString()).concat(".csv");
        log.debug(filePath);
        return filePath;
    }
    
    private ColumnPositionMappingStrategy getStrategy( ClassType type ) {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(type.getCl());
        strategy.setColumnMapping(type.getHeaders());
        return strategy;
    }

}
