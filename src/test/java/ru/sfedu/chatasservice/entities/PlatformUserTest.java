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
public class PlatformUserTest {
    
    public PlatformUserTest() {
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
     * Test of getId method, of class PlatformUser.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        PlatformUser instance = new PlatformUser();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class PlatformUser.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        PlatformUser instance = new PlatformUser();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class PlatformUser.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        PlatformUser instance = new PlatformUser();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class PlatformUser.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        PlatformUser instance = new PlatformUser();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvatarLink method, of class PlatformUser.
     */
    @Test
    public void testGetAvatarLink() {
        System.out.println("getAvatarLink");
        PlatformUser instance = new PlatformUser();
        String expResult = "";
        String result = instance.getAvatarLink();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAvatarLink method, of class PlatformUser.
     */
    @Test
    public void testSetAvatarLink() {
        System.out.println("setAvatarLink");
        String avatarLink = "";
        PlatformUser instance = new PlatformUser();
        instance.setAvatarLink(avatarLink);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastAct method, of class PlatformUser.
     */
    @Test
    public void testGetLastAct() {
        System.out.println("getLastAct");
        PlatformUser instance = new PlatformUser();
        String expResult = "";
        String result = instance.getLastAct();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastAct method, of class PlatformUser.
     */
    @Test
    public void testSetLastAct() {
        System.out.println("setLastAct");
        String lastAct = "";
        PlatformUser instance = new PlatformUser();
        instance.setLastAct(lastAct);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBanned method, of class PlatformUser.
     */
    @Test
    public void testGetBanned_0args() {
        System.out.println("getBanned");
        PlatformUser instance = new PlatformUser();
        boolean expResult = false;
        boolean result = instance.getBanned();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBanned method, of class PlatformUser.
     */
    @Test
    public void testGetBanned_boolean() {
        System.out.println("getBanned");
        boolean banned = false;
        PlatformUser instance = new PlatformUser();
        instance.getBanned(banned);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlatformId method, of class PlatformUser.
     */
    @Test
    public void testGetPlatformId() {
        System.out.println("getPlatformId");
        PlatformUser instance = new PlatformUser();
        int expResult = 0;
        int result = instance.getPlatformId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlatformId method, of class PlatformUser.
     */
    @Test
    public void testSetPlatformId() {
        System.out.println("setPlatformId");
        int platformId = 0;
        PlatformUser instance = new PlatformUser();
        instance.setPlatformId(platformId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}