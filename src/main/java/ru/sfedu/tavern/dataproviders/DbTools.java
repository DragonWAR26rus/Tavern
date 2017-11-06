
package ru.sfedu.tavern.dataproviders;

import java.util.ArrayList;
import ru.sfedu.tavern.database.DbConnection;
import ru.sfedu.tavern.entities.ClassType;
import ru.sfedu.tavern.entities.Entity;

/**
 *
 * @author entropy
 */
public class DbTools implements IDataProvider{

    private final DbConnection instence = DbConnection.getInstance();
    private final String OurUserCols = "login, password_hash, last_act, email";
    private final String[] TableNames = {"our_user", "platform", "platform_user", "messages"};
    
    
    
    public DbTools() {

    }

    @Override
    public void insert(ArrayList<Entity> object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Entity object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Entity object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Entity object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity getObjectByID(long id, ClassType type) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
