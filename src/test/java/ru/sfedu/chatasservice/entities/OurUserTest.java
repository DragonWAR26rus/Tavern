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
public class OurUserTest {
    
    public OurUserTest() {
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
     * Test of getId method, of class OurUser.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        OurUser instance = new OurUser();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class OurUser.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        OurUser instance = new OurUser();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of getPassword method, of class OurUser.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        OurUser instance = new OurUser();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class OurUser.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        OurUser instance = new OurUser();
        instance.setPassword(password);
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
    
}
