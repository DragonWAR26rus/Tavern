/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.OurUser;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.PlatformUser;
import ru.sfedu.tavern.utils.EntityGenerator;

/**
 *
 * @author entropy
 */
public class JdbcAPITest {
    
    public JdbcAPITest() {
    }
    
    
    /**
     * Test of CRUD methods for OurUser entity, of class JdbcAPI
     */
    @Test
    public void testOurUserCRUD() {
        JdbcAPI jdbc = new JdbcAPI();
        OurUser user = EntityGenerator.generateOurUser(jdbc);
        assertNotNull(user);
        Optional<Entity> optUser = Optional.ofNullable(user);
        try {
            // CREATE
            jdbc.insert(optUser);
            assertEquals(optUser, jdbc.getObjectByID(user.getId(), ClassType.OURUSER));
            
            // UPDATE
            user.setLogin(user.getLogin() + "_TEST_UPDATE");
            optUser = Optional.ofNullable(user);
            jdbc.update(optUser);
            assertEquals(optUser, jdbc.getObjectByID(user.getId(), ClassType.OURUSER));
            
            // READ
            assertEquals(user, jdbc.select("id", String.valueOf(user.getId()), ClassType.OURUSER).get(0));
            
            // DELETE
            jdbc.delete(optUser);
            assertFalse(jdbc.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
        } catch (Exception ex) {}   
    }
    
    /**
     * Test of CRUD methods for Platform entity, of class JdbcAPI
     */
    @Test
    public void testPlatformCRUD() {
        JdbcAPI jdbc = new JdbcAPI();
        OurUser user = EntityGenerator.generateOurUser(jdbc);
        assertNotNull(user);
        Platform pl = EntityGenerator.generatePlatform(jdbc, user);
        assertNotNull(pl);
        Optional<Entity> optPl = Optional.ofNullable(pl);
        try {
            
            jdbc.insert(Optional.ofNullable(user));
            assertEquals(Optional.ofNullable(user), jdbc.getObjectByID(user.getId(), ClassType.OURUSER));
            
            // CREATE
            jdbc.insert(optPl);
            assertEquals(optPl, jdbc.getObjectByID(pl.getId(), ClassType.PLATFORM));
            
            // UPDATE
            pl.setDomain(pl.getDomain()+ "_TEST_UPDATE");
            optPl = Optional.ofNullable(pl);
            jdbc.update(optPl);
            assertEquals(optPl, jdbc.getObjectByID(pl.getId(), ClassType.PLATFORM));
            
            // READ
            assertEquals(pl, jdbc.select("id", String.valueOf(pl.getId()), ClassType.PLATFORM).get(0));
            
            // DELETE
            jdbc.delete(optPl);
            assertFalse(jdbc.getObjectByID(pl.getId(), ClassType.PLATFORM).isPresent());
            
            
            jdbc.delete(Optional.ofNullable(user));
            assertFalse(jdbc.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
        } catch (Exception ex) {}   
    }

    /**
     * Test of CRUD methods for PlatformUser entity, of class JdbcAPI
     */
    @Test
    public void testPlatformUserCRUD() {
        JdbcAPI jdbc = new JdbcAPI();
        OurUser user = EntityGenerator.generateOurUser(jdbc);
        assertNotNull(user);
        Platform pl = EntityGenerator.generatePlatform(jdbc, user);
        assertNotNull(pl);
        PlatformUser plUser = EntityGenerator.generatePlatformUser(jdbc, pl);
        assertNotNull(pl);
        Optional<Entity> optPlUser = Optional.ofNullable(plUser);
        try {
            
            jdbc.insert(Optional.ofNullable(user));
            assertEquals(Optional.ofNullable(user), jdbc.getObjectByID(user.getId(), ClassType.OURUSER));
            jdbc.insert(Optional.ofNullable(pl));
            assertEquals(Optional.ofNullable(pl), jdbc.getObjectByID(pl.getId(), ClassType.PLATFORM));
            
            // CREATE
            jdbc.insert(optPlUser);
            assertEquals(optPlUser, jdbc.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER));
            
            // UPDATE
            plUser.setLogin(plUser.getLogin()+ "_TEST_UPDATE");
            optPlUser = Optional.ofNullable(plUser);
            jdbc.update(optPlUser);
            assertEquals(optPlUser, jdbc.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER));
            
            // READ
            assertEquals(plUser, jdbc.select("id", String.valueOf(plUser.getId()), ClassType.PLATFORMUSER).get(0));
            
            // DELETE
            jdbc.delete(optPlUser);
            assertFalse(jdbc.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER).isPresent());
            
            
            jdbc.delete(Optional.ofNullable(user));
            assertFalse(jdbc.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
            assertFalse(jdbc.getObjectByID(pl.getId(), ClassType.PLATFORM).isPresent());
        } catch (Exception ex) {}   
    }
    
    /**
     * Test of CRUD methods for Message entity, of class JdbcAPI
     */
    @Test
    public void testMessageCRUD() {
        JdbcAPI jdbc = new JdbcAPI();
        OurUser user = EntityGenerator.generateOurUser(jdbc);
        assertNotNull(user);
        Platform pl = EntityGenerator.generatePlatform(jdbc, user);
        assertNotNull(pl);
        PlatformUser plUser = EntityGenerator.generatePlatformUser(jdbc, pl);
        assertNotNull(pl);
        Message message = EntityGenerator.generateMessage(jdbc, plUser, pl);
        assertNotNull(message);
        Optional<Entity> optMessage = Optional.ofNullable(message);
        try {  
            jdbc.insert(Optional.ofNullable(user));
            assertEquals(Optional.ofNullable(user), jdbc.getObjectByID(user.getId(), ClassType.OURUSER));
            jdbc.insert(Optional.ofNullable(pl));
            assertEquals(Optional.ofNullable(pl), jdbc.getObjectByID(pl.getId(), ClassType.PLATFORM));
            jdbc.insert(Optional.ofNullable(plUser));
            assertEquals(Optional.ofNullable(plUser), jdbc.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER));
            
            // CREATE
            jdbc.insert(optMessage);
            assertEquals(optMessage, jdbc.getObjectByID(message.getId(), ClassType.MESSAGE));
            
            // UPDATE
            message.setText(message.getText()+ "_TEST_UPDATE");
            optMessage = Optional.ofNullable(message);
            jdbc.update(optMessage);
            assertEquals(optMessage, jdbc.getObjectByID(message.getId(), ClassType.MESSAGE));
            
            // READ
            assertEquals(message, jdbc.select("id", String.valueOf(message.getId()), ClassType.MESSAGE).get(0));
            
            // DELETE
            jdbc.delete(optMessage);
            assertFalse(jdbc.getObjectByID(message.getId(), ClassType.MESSAGE).isPresent());
            
            
            jdbc.delete(Optional.ofNullable(user));
            assertFalse(jdbc.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
            assertFalse(jdbc.getObjectByID(pl.getId(), ClassType.PLATFORM).isPresent());
            assertFalse(jdbc.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER).isPresent());
        } catch (Exception ex) {}   
        
    }

//    /**
//     * Test of insert method, of class JdbcAPI.
//     */
//    @Test
//    public void testInsert_Entity() throws Exception {
//        JdbcAPI instance = new JdbcAPI();
//        int depth = 4;
//        
//        System.out.println("insert");
//        for(int i = 1; i <= depth; i++) {
//            Entity object1 = new OurUser(i, "Test" + i, "Hash" + i, System.currentTimeMillis(), "qwe" + i + "@mail.ru");
//            instance.insert(Optional.ofNullable(object1));
//            for(int j = 1; j <= depth; j++) {
//                int q = (i-1) * depth + j;
//                Entity object2 = new Platform(q, "domain" + q, "key" + q, i);
//                instance.insert(Optional.ofNullable(object2));
//                for(int x = 1; x <= depth; x++) {
//                    int z = (i-1) * depth * depth + (j-1) * depth + x;
//                    Entity object3 = new PlatformUser(z, "login" + z, "link" + z, System.currentTimeMillis(), false, q);
//                    instance.insert(Optional.ofNullable(object3));
//                    for(int d = 1; d <= depth; d++) {
//                        int m = (i-1) * depth * depth * depth + (j-1) * depth * depth + (x-1) * depth + d;
//                        Entity object4 = new Message(m, z, System.currentTimeMillis(), "dsdasd", q);
//                         instance.insert(Optional.ofNullable(object4));
//                    }
//                }
//            }
//        }
//    }
    
}
