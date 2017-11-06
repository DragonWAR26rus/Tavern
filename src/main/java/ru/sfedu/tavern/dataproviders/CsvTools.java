
package ru.sfedu.tavern.dataproviders;

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
    CsvToBeanBuilder<Entity> builder;
    FileWriter writer;
    StatefulBeanToCsv beanToCsv;
    
    public void initBuilder(ClassType type) {
        try {
            builder = new CsvToBeanBuilder(new FileReader(getCsvFile(type))).withType(type.getCl());
        } catch ( Exception ex ) {
            log.debug(ex);
        }
    }
    
    public void initBeanToCsv(ClassType type, boolean append) {
        try {
            writer = new FileWriter(getCsvFile(type), append);
            beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        } catch ( IOException ex ) {
            log.error(ex);
        }
    }

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
        
        initBeanToCsv(object.getClassType(), true);
        log.info(object.toString());
        try {
            beanToCsv.write(object);
            writer.close();
        } catch ( Exception ex ) {
            log.error(ex);
        }
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
        initBuilder(type);
        
        try{
            List<Entity> list = builder.build().parse();
            for( Entity item : list ) {
                if( item.getId() == id ) {
                    log.info(item.toString());
                    return item;
                }
            }
        } catch ( Exception ex ) {
            log.error(ex);
        }
        return null;
    }
    
    private void writeToCsv(List<Entity> list) {
        try {
            String path = getCsvFile(list.get(0).getClassType());
            
            FileWriter fw = new FileWriter(path, false);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(fw)
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
            records = new CsvToBeanBuilder(fr).withType(classType.getCl()).build().parse();
            return records;
        } catch (IOException ex) {
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

}
