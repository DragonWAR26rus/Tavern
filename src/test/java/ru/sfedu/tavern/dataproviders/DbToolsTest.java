/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.tavern.entities.ClassType;
import ru.sfedu.tavern.entities.Entity;

/**
 *
 * @author entropy
 */
public class DbToolsTest {
    
    public DbToolsTest() {
    }

    /**
     * Test of insert method, of class DbTools.
     */
    @Test
    public void testInsert_ArrayList() throws Exception {
        System.out.println("insert");
        ArrayList<Entity> object = null;
        DbTools instance = new DbTools();
        instance.insert(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class DbTools.
     */
    @Test
    public void testInsert_Entity() throws Exception {
        System.out.println("insert");
        Entity object = null;
        DbTools instance = new DbTools();
        instance.insert(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class DbTools.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Entity object = null;
        DbTools instance = new DbTools();
        instance.update(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class DbTools.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Entity object = null;
        DbTools instance = new DbTools();
        instance.delete(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectByID method, of class DbTools.
     */
    @Test
    public void testGetObjectByID() throws Exception {
        System.out.println("getObjectByID");
        long id = 0L;
        ClassType type = null;
        DbTools instance = new DbTools();
        Entity expResult = null;
        Entity result = instance.getObjectByID(id, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
