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
public class XmlAPITest {
    
    public XmlAPITest() {
    }
    
    /**
     * Test of CRUD methods for OurUser entity, of class XmlAPI
     */
    @Test
    public void testOurUserCRUD() {
        XmlAPI xml = new XmlAPI();
        OurUser user = EntityGenerator.generateOurUser(xml);
        assertNotNull(user);
        Optional<Entity> optUser = Optional.ofNullable(user);
        try {
            // CREATE
            xml.insert(optUser);
            assertEquals(optUser, xml.getObjectByID(user.getId(), ClassType.OURUSER));
            
            // UPDATE
            user.setLogin(user.getLogin() + "_TEST_UPDATE");
            optUser = Optional.ofNullable(user);
            xml.update(optUser);
            assertEquals(optUser, xml.getObjectByID(user.getId(), ClassType.OURUSER));
            
            // READ
            assertEquals(user, xml.select("id", String.valueOf(user.getId()), ClassType.OURUSER).get(0));
            
            // DELETE
            xml.delete(optUser);
            assertFalse(xml.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
        } catch (Exception ex) {}   
    }
    
    /**
     * Test of CRUD methods for Platform entity, of class XmlAPI
     */
    @Test
    public void testPlatformCRUD() {
        XmlAPI xml = new XmlAPI();
        OurUser user = EntityGenerator.generateOurUser(xml);
        assertNotNull(user);
        Platform pl = EntityGenerator.generatePlatform(xml, user);
        assertNotNull(pl);
        Optional<Entity> optPl = Optional.ofNullable(pl);
        try {
            
            xml.insert(Optional.ofNullable(user));
            assertEquals(Optional.ofNullable(user), xml.getObjectByID(user.getId(), ClassType.OURUSER));
            
            // CREATE
            xml.insert(optPl);
            assertEquals(optPl, xml.getObjectByID(pl.getId(), ClassType.PLATFORM));
            
            // UPDATE
            pl.setDomain(pl.getDomain()+ "_TEST_UPDATE");
            optPl = Optional.ofNullable(pl);
            xml.update(optPl);
            assertEquals(optPl, xml.getObjectByID(pl.getId(), ClassType.PLATFORM));
            
            // READ
            assertEquals(pl, xml.select("id", String.valueOf(pl.getId()), ClassType.PLATFORM).get(0));
            
            // DELETE
            xml.delete(optPl);
            assertFalse(xml.getObjectByID(pl.getId(), ClassType.PLATFORM).isPresent());
            
            
            xml.delete(Optional.ofNullable(user));
            assertFalse(xml.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
        } catch (Exception ex) {}   
    }

    /**
     * Test of CRUD methods for PlatformUser entity, of class XmlAPI
     */
    @Test
    public void testPlatformUserCRUD() {
        XmlAPI xml = new XmlAPI();
        OurUser user = EntityGenerator.generateOurUser(xml);
        assertNotNull(user);
        Platform pl = EntityGenerator.generatePlatform(xml, user);
        assertNotNull(pl);
        PlatformUser plUser = EntityGenerator.generatePlatformUser(xml, pl);
        assertNotNull(pl);
        Optional<Entity> optPlUser = Optional.ofNullable(plUser);
        try {
            
            xml.insert(Optional.ofNullable(user));
            assertEquals(Optional.ofNullable(user), xml.getObjectByID(user.getId(), ClassType.OURUSER));
            xml.insert(Optional.ofNullable(pl));
            assertEquals(Optional.ofNullable(pl), xml.getObjectByID(pl.getId(), ClassType.PLATFORM));
            
            // CREATE
            xml.insert(optPlUser);
            assertEquals(optPlUser, xml.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER));
            
            // UPDATE
            plUser.setLogin(plUser.getLogin()+ "_TEST_UPDATE");
            optPlUser = Optional.ofNullable(plUser);
            xml.update(optPlUser);
            assertEquals(optPlUser, xml.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER));
            
            // READ
            assertEquals(plUser, xml.select("id", String.valueOf(plUser.getId()), ClassType.PLATFORMUSER).get(0));
            
            // DELETE
            xml.delete(optPlUser);
            assertFalse(xml.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER).isPresent());
            
            
            xml.delete(Optional.ofNullable(user));
            assertFalse(xml.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
            assertFalse(xml.getObjectByID(pl.getId(), ClassType.PLATFORM).isPresent());
        } catch (Exception ex) {}   
    }
    
    /**
     * Test of CRUD methods for Message entity, of class XmlAPI
     */
    @Test
    public void testMessageCRUD() {
        XmlAPI xml = new XmlAPI();
        OurUser user = EntityGenerator.generateOurUser(xml);
        assertNotNull(user);
        Platform pl = EntityGenerator.generatePlatform(xml, user);
        assertNotNull(pl);
        PlatformUser plUser = EntityGenerator.generatePlatformUser(xml, pl);
        assertNotNull(pl);
        Message message = EntityGenerator.generateMessage(xml, plUser, pl);
        assertNotNull(message);
        Optional<Entity> optMessage = Optional.ofNullable(message);
        try {  
            xml.insert(Optional.ofNullable(user));
            assertEquals(Optional.ofNullable(user), xml.getObjectByID(user.getId(), ClassType.OURUSER));
            xml.insert(Optional.ofNullable(pl));
            assertEquals(Optional.ofNullable(pl), xml.getObjectByID(pl.getId(), ClassType.PLATFORM));
            xml.insert(Optional.ofNullable(plUser));
            assertEquals(Optional.ofNullable(plUser), xml.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER));
            
            // CREATE
            xml.insert(optMessage);
            assertEquals(optMessage, xml.getObjectByID(message.getId(), ClassType.MESSAGE));
            
            // UPDATE
            message.setText(message.getText()+ "_TEST_UPDATE");
            optMessage = Optional.ofNullable(message);
            xml.update(optMessage);
            assertEquals(optMessage, xml.getObjectByID(message.getId(), ClassType.MESSAGE));
            
            // READ
            assertEquals(message, xml.select("id", String.valueOf(message.getId()), ClassType.MESSAGE).get(0));
            
            // DELETE
            xml.delete(optMessage);
            assertFalse(xml.getObjectByID(message.getId(), ClassType.MESSAGE).isPresent());
            
            
            xml.delete(Optional.ofNullable(user));
            assertFalse(xml.getObjectByID(user.getId(), ClassType.OURUSER).isPresent());
            assertFalse(xml.getObjectByID(pl.getId(), ClassType.PLATFORM).isPresent());
            assertFalse(xml.getObjectByID(plUser.getId(), ClassType.PLATFORMUSER).isPresent());
        } catch (Exception ex) {}   
    }
    
}
