/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;

/**
 *
 * @author entropy
 */
public class IDataProviderTest {
    
    public IDataProviderTest() {
    }

    /**
     * Test of insert method, of class IDataProvider.
     */
    @Test
    public void testInsert_ArrayList() throws Exception {
        System.out.println("insert");
        ArrayList<Entity> object = null;
        IDataProvider instance = new IDataProviderImpl();
        instance.insert(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class IDataProvider.
     */
    @Test
    public void testInsert_Entity() throws Exception {
        System.out.println("insert");
        Entity object = null;
        IDataProvider instance = new IDataProviderImpl();
        instance.insert(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class IDataProvider.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Entity object = null;
        IDataProvider instance = new IDataProviderImpl();
        instance.update(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class IDataProvider.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Entity object = null;
        IDataProvider instance = new IDataProviderImpl();
        instance.delete(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectByID method, of class IDataProvider.
     */
    @Test
    public void testGetObjectByID() throws Exception {
        System.out.println("getObjectByID");
        long id = 0L;
        ClassType type = null;
        IDataProvider instance = new IDataProviderImpl();
        Entity expResult = null;
        Entity result = instance.getObjectByID(id, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IDataProviderImpl implements IDataProvider {

        public void insert(ArrayList<Entity> object) throws Exception {
        }

        public void insert(Entity object) throws Exception {
        }

        public void update(Entity object) throws Exception {
        }

        public void delete(Entity object) throws Exception {
        }

        public Entity getObjectByID(long id, ClassType type) throws Exception {
            return null;
        }
    }
    
}
