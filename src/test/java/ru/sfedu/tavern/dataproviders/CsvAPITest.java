/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CsvAPITest {
    
    public CsvAPITest() {
    }
    
    /**
     * Test of CRUD methods for OurUser entity, of class CsvAPI
     */
    @Test
    public void testOurUserCRUD() {
        CsvAPI csv = new CsvAPI();
        OurUser user = EntityGenerator.generateOurUser(csv);
        assertNotNull(user);
        Optional<Entity> optUser = Optional.ofNullable(user);
        try {
            // CREATE
            csv.insert(optUser);
            assertEquals(optUser, csv.getObjectByID(user.getId(), ClassType.OURUSER));
            
            // UPDATE
            user.setLogin(user.getLogin() + "_TEST_UPDATE");
            optUser = Optional.ofNullable(user);
            csv.update(optUser);
            assertEquals(optUser, csv.getObjectByID(user.getId(), ClassType.OURUSER));
            
            // READ
            assertEquals(user, csv.select("id", String.valueOf(user.getId()), ClassType.OURUSER).get(0));
            
            // DELETE
            csv.delete(optUser);
            assertFalse(csv.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
        } catch (Exception ex) {}   
    }
    
    /**
     * Test of CRUD methods for Platform entity, of class CsvAPI
     */
    @Test
    public void testPlatformCRUD() {
        CsvAPI csv = new CsvAPI();
        OurUser user = EntityGenerator.generateOurUser(csv);
        assertNotNull(user);
        Platform pl = EntityGenerator.generatePlatform(csv, user);
        assertNotNull(pl);
        Optional<Entity> optPl = Optional.ofNullable(pl);
        try {
            
            csv.insert(Optional.ofNullable(user));
            assertEquals(Optional.ofNullable(user), csv.getObjectByID(user.getId(), ClassType.OURUSER));
            
            // CREATE
            csv.insert(optPl);
            assertEquals(optPl, csv.getObjectByID(pl.getId(), ClassType.PLATFORM));
            
            // UPDATE
            pl.setDomain(pl.getDomain()+ "_TEST_UPDATE");
            optPl = Optional.ofNullable(pl);
            csv.update(optPl);
            assertEquals(optPl, csv.getObjectByID(pl.getId(), ClassType.PLATFORM));
            
            // READ
            assertEquals(pl, csv.select("id", String.valueOf(pl.getId()), ClassType.PLATFORM).get(0));
            
            // DELETE
            csv.delete(optPl);
            assertFalse(csv.getObjectByID(pl.getId(), ClassType.PLATFORM).isPresent());
            
            
            csv.delete(Optional.ofNullable(user));
            assertFalse(csv.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
        } catch (Exception ex) {}   
    }

    /**
     * Test of CRUD methods for PlatformUser entity, of class CsvAPI
     */
    @Test
    public void testPlatformUserCRUD() {
        CsvAPI csv = new CsvAPI();
        OurUser user = EntityGenerator.generateOurUser(csv);
        assertNotNull(user);
        Platform pl = EntityGenerator.generatePlatform(csv, user);
        assertNotNull(pl);
        PlatformUser plUser = EntityGenerator.generatePlatformUser(csv, pl);
        assertNotNull(pl);
        Optional<Entity> optPlUser = Optional.ofNullable(plUser);
        try {
            
            csv.insert(Optional.ofNullable(user));
            assertEquals(Optional.ofNullable(user), csv.getObjectByID(user.getId(), ClassType.OURUSER));
            csv.insert(Optional.ofNullable(pl));
            assertEquals(Optional.ofNullable(pl), csv.getObjectByID(pl.getId(), ClassType.PLATFORM));
            
            // CREATE
            csv.insert(optPlUser);
            assertEquals(optPlUser, csv.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER));
            
            // UPDATE
            plUser.setLogin(plUser.getLogin()+ "_TEST_UPDATE");
            optPlUser = Optional.ofNullable(plUser);
            csv.update(optPlUser);
            assertEquals(optPlUser, csv.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER));
            
            // READ
            assertEquals(plUser, csv.select("id", String.valueOf(plUser.getId()), ClassType.PLATFORMUSER).get(0));
            
            // DELETE
            csv.delete(optPlUser);
            assertFalse(csv.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER).isPresent());
            
            
            csv.delete(Optional.ofNullable(user));
            assertFalse(csv.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
            assertFalse(csv.getObjectByID(pl.getId(), ClassType.PLATFORM).isPresent());
        } catch (Exception ex) {}   
    }
    
    /**
     * Test of CRUD methods for Message entity, of class CsvAPI
     */
    @Test
    public void testMessageCRUD() {
        CsvAPI csv = new CsvAPI();
        OurUser user = EntityGenerator.generateOurUser(csv);
        assertNotNull(user);
        Platform pl = EntityGenerator.generatePlatform(csv, user);
        assertNotNull(pl);
        PlatformUser plUser = EntityGenerator.generatePlatformUser(csv, pl);
        assertNotNull(pl);
        Message message = EntityGenerator.generateMessage(csv, plUser, pl);
        assertNotNull(message);
        Optional<Entity> optMessage = Optional.ofNullable(message);
        try {  
            csv.insert(Optional.ofNullable(user));
            assertEquals(Optional.ofNullable(user), csv.getObjectByID(user.getId(), ClassType.OURUSER));
            csv.insert(Optional.ofNullable(pl));
            assertEquals(Optional.ofNullable(pl), csv.getObjectByID(pl.getId(), ClassType.PLATFORM));
            csv.insert(Optional.ofNullable(plUser));
            assertEquals(Optional.ofNullable(plUser), csv.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER));
            
            // CREATE
            csv.insert(optMessage);
            assertEquals(optMessage, csv.getObjectByID(message.getId(), ClassType.MESSAGE));
            
            // UPDATE
            message.setText(message.getText()+ "_TEST_UPDATE");
            optMessage = Optional.ofNullable(message);
            csv.update(optMessage);
            assertEquals(optMessage, csv.getObjectByID(message.getId(), ClassType.MESSAGE));
            
            // READ
            assertEquals(message, csv.select("id", String.valueOf(message.getId()), ClassType.MESSAGE).get(0));
            
            // DELETE
            csv.delete(optMessage);
            assertFalse(csv.getObjectByID(message.getId(), ClassType.MESSAGE).isPresent());
            
            
            csv.delete(Optional.ofNullable(user));
            assertFalse(csv.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
            assertFalse(csv.getObjectByID(pl.getId(), ClassType.PLATFORM).isPresent());
            assertFalse(csv.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER).isPresent());
        } catch (Exception ex) {}   
    }
}
