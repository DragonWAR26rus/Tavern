/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.entities;

import ru.sfedu.tavern.model.entities.Platform;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author entropy
 */
public class PlatformTest {
    
    public PlatformTest() {
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
        long expResult = 0L;
        long result = instance.getOwnerId();
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

    /**
     * Test of toString method, of class Platform.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Platform instance = new Platform();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Platform.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Platform instance = new Platform();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
