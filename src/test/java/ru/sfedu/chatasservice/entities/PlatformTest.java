/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.chatasservice.entities;

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
public class PlatformTest {
    
    public PlatformTest() {
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
     * Test of getId method, of class Platform.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Platform instance = new Platform();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Platform.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Platform instance = new Platform();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDomain method, of class Platform.
     */
    @Test
    public void testGetDomain() {
        System.out.println("getDomain");
        Platform instance = new Platform();
        String expResult = "";
        String result = instance.getDomain();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDomain method, of class Platform.
     */
    @Test
    public void testSetDomain() {
        System.out.println("setDomain");
        String domain = "";
        Platform instance = new Platform();
        instance.setDomain(domain);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKey method, of class Platform.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        Platform instance = new Platform();
        String expResult = "";
        String result = instance.getKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKey method, of class Platform.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        String key = "";
        Platform instance = new Platform();
        instance.setKey(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwnerId method, of class Platform.
     */
    @Test
    public void testGetOwnerId() {
        System.out.println("getOwnerId");
        Platform instance = new Platform();
        int expResult = 0;
        int result = instance.getOwnerId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwnerId method, of class Platform.
     */
    @Test
    public void testSetOwnerId() {
        System.out.println("setOwnerId");
        int ownerId = 0;
        Platform instance = new Platform();
        instance.setOwnerId(ownerId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
