/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.chatasservice.dataprovider;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author entropy
 */
public class IDataProviderTest {
    
    public IDataProviderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveRecord method, of class IDataProvider.
     */
    @Test
    public void testSaveRecord() {
        System.out.println("saveRecord");
        Object o = null;
        IDataProvider instance = new IDataProviderImpl();
        instance.saveRecord(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRecord method, of class IDataProvider.
     */
    @Test
    public void testDeleteRecord() {
        System.out.println("deleteRecord");
        Object o = null;
        IDataProvider instance = new IDataProviderImpl();
        instance.deleteRecord(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecordById method, of class IDataProvider.
     */
    @Test
    public void testGetRecordById() {
        System.out.println("getRecordById");
        long id = 0L;
        IDataProvider instance = new IDataProviderImpl();
        Object expResult = null;
        Object result = instance.getRecordById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initDataSource method, of class IDataProvider.
     */
    @Test
    public void testInitDataSource() {
        System.out.println("initDataSource");
        IDataProvider instance = new IDataProviderImpl();
        instance.initDataSource();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IDataProviderImpl implements IDataProvider {

        public void saveRecord(T o) {
        }

        public void deleteRecord(T o) {
        }

        public T getRecordById(long id) {
            return null;
        }

        public void initDataSource() {
        }
    }
    
}
