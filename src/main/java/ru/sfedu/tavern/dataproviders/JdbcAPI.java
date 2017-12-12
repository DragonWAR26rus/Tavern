package ru.sfedu.tavern.dataproviders;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.animation.Animation;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import ru.sfedu.tavern.database.DbConnection;
import ru.sfedu.tavern.model.Result;
import ru.sfedu.tavern.model.StatusType;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;

/**
 *
 * @author entropy
 */
public class JdbcAPI implements IDataProvider{

    
    private static Logger logger = Logger.getLogger(CsvAPI.class);
    private DbConnection instance;
        
    public JdbcAPI() {
        try {
            instance = DbConnection.getInstance();
        } catch (Exception ex) {
            logger.error(ex);
        }
        
    }

    @Override
    public Result insert(ArrayList<Entity> object) throws Exception {
        ClassType classType = object.get(0).getClassType();
        try {
            object.stream().forEach(iterator -> {
                String query = "INSERT INTO " + classType.getTableName() + "(" + classType.getHeaderString() + ") VALUES (" + iterator.toString(true) + ");";
                instance.execQuery(query, classType);
            });
        } catch (Exception ex) {
            return new Result(StatusType.ERROR);
        }
        return new Result(StatusType.OK);
    }

    @Override
    public Result insert(Entity object) throws Exception {
        ArrayList<Entity> objList = new ArrayList();
        objList.add(object);
        return insert(objList);
    }

    @Override
    public Result update(Entity object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result delete(Entity object) throws Exception {
        ClassType classType = object.getClassType();
        try {
            instance.execQuery("DELETE FROM " + classType.getTableName() + " WHERE id=" + object.getId() + ";", classType);   
        } catch (Exception ex) {
            return new Result(StatusType.ERROR);
        }
        return new Result(StatusType.OK);
    }

    @Override
    public Optional<Entity> getObjectByID(long id, ClassType type) {
        Optional<Entity> result;
        List<Entity> list = new ArrayList();
        try {
            list = select("id", String.valueOf(id), type);
        }catch(Exception ex) {
            logger.error(ex);
        }
        
        result = Optional.ofNullable(list.get(0));
        return result;
    }

    public List<Entity> select(ClassType type) throws Exception {
        return select(null, null, type);
    }
        
    @Override
    public List<Entity> select(String col, String val, ClassType type) throws Exception {
        String whereCond = "";
        if(col != null && val != null) {
            whereCond = " WHERE " + col + "=" + val;
        }
        
        List<Entity> result = new ArrayList();
        result = instance.execQuery("SELECT * FROM " + type.getTableName() + whereCond + ";", type);
        return result;
    }

}
