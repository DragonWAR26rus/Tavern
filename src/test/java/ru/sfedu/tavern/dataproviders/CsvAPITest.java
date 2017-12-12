/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.OurUser;

/**
 *
 * @author entropy
 */
public class CsvAPITest {
    
    public CsvAPITest() {
    }

    /**
     * Test of insert method, of class CsvAPI.
     */
    @Test
    public void testInsert_ArrayList() {
        System.out.println("insert");
        ArrayList<Entity> objectList = null;
        CsvAPI instance = new CsvAPI();
        instance.insert(objectList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class CsvAPI.
     */
    @Test
    public void testInsert_Entity() {
        System.out.println("insert");
        Entity object = null;
        CsvAPI instance = new CsvAPI();
        instance.insert(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class CsvAPI.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        OurUser updateableObject = new OurUser(2, "Entropy", "NewPassHash1", "1509574941841", "DragonWAR26russ@gmail.com");
        CsvAPI instance = new CsvAPI();
        instance.update(updateableObject);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class CsvAPI.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        CsvAPI instance = new CsvAPI();
        Entity removeableObject = instance.getObjectByID(2, ClassType.OURUSER);
        instance.delete(removeableObject);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectByID method, of class CsvAPI.
     */
    @Test
    public void testGetObjectByID() {
        System.out.println("getObjectByID");
        long id = 1L;
        ClassType type = ClassType.PLATFORM;
        CsvAPI instance = new CsvAPI();
        Entity expResult = null;
        Entity result = instance.getObjectByID(id, type);
        System.out.println(result.toString());
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilePath method, of class CsvAPI.
     */
    @Test
    public void testGetFilePath() throws Exception {
        System.out.println("getFilePath");
        ClassType classType = null;
        CsvAPI instance = new CsvAPI();
        String expResult = "";
        String result = instance.getFilePath(classType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class CsvAPI.
     */
    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        String col = "passwordHash";
        String val = "e3afed0047b08059d0fada10f400c1e5";
        ClassType type = ClassType.OURUSER;
        CsvAPI instance = new CsvAPI();
        Optional<List<Entity>> expResult = null;
        Optional<List<Entity>> result = instance.select(col, val, type);
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
