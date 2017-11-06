/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.entities;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author entropy
 */
public class OurUserTest {
    
    public OurUserTest() {
    }

    /**
     * Test of getLogin method, of class OurUser.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        OurUser instance = new OurUser();
        String expResult = "";
        String result = instance.getLogin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLogin method, of class OurUser.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        String login = "";
        OurUser instance = new OurUser();
        instance.setLogin(login);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPasswordHash method, of class OurUser.
     */
    @Test
    public void testGetPasswordHash() {
        System.out.println("getPasswordHash");
        OurUser instance = new OurUser();
        String expResult = "";
        String result = instance.getPasswordHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPasswordHash method, of class OurUser.
     */
    @Test
    public void testSetPasswordHash() {
        System.out.println("setPasswordHash");
        String passwordHash = "";
        OurUser instance = new OurUser();
        instance.setPasswordHash(passwordHash);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastAct method, of class OurUser.
     */
    @Test
    public void testGetLastAct() {
        System.out.println("getLastAct");
        OurUser instance = new OurUser();
        String expResult = "";
        String result = instance.getLastAct();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastAct method, of class OurUser.
     */
    @Test
    public void testSetLastAct() {
        System.out.println("setLastAct");
        String lastAct = "";
        OurUser instance = new OurUser();
        instance.setLastAct(lastAct);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class OurUser.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        OurUser instance = new OurUser();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class OurUser.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        OurUser instance = new OurUser();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlatforms method, of class OurUser.
     */
    @Test
    public void testGetPlatforms() {
        System.out.println("getPlatforms");
        OurUser instance = new OurUser();
        ArrayList<Platform> expResult = null;
        ArrayList<Platform> result = instance.getPlatforms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlatforms method, of class OurUser.
     */
    @Test
    public void testSetPlatforms_ArrayList() {
        System.out.println("setPlatforms");
        ArrayList<Platform> platforms = null;
        OurUser instance = new OurUser();
        instance.setPlatforms(platforms);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlatforms method, of class OurUser.
     */
    @Test
    public void testSetPlatforms_Platform() {
        System.out.println("setPlatforms");
        Platform platform = null;
        OurUser instance = new OurUser();
        instance.setPlatforms(platform);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valuesToString method, of class OurUser.
     */
    @Test
    public void testValuesToString() {
        System.out.println("valuesToString");
        boolean needId = false;
        OurUser instance = new OurUser();
        String expResult = "";
        String result = instance.valuesToString(needId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class OurUser.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        OurUser instance = new OurUser();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class OurUser.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        OurUser instance = new OurUser();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
