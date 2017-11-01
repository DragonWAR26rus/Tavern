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
public class MessageTest {
    
    public MessageTest() {
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
     * Test of getId method, of class Message.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Message instance = new Message();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Message.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Message instance = new Message();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSenderId method, of class Message.
     */
    @Test
    public void testGetSenderId() {
        System.out.println("getSenderId");
        Message instance = new Message();
        int expResult = 0;
        int result = instance.getSenderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSenderId method, of class Message.
     */
    @Test
    public void testSetSenderId() {
        System.out.println("setSenderId");
        int senderId = 0;
        Message instance = new Message();
        instance.setSenderId(senderId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getText method, of class Message.
     */
    @Test
    public void testGetText() {
        System.out.println("getText");
        Message instance = new Message();
        String expResult = "";
        String result = instance.getText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setText method, of class Message.
     */
    @Test
    public void testSetText() {
        System.out.println("setText");
        String text = "";
        Message instance = new Message();
        instance.setText(text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSendTime method, of class Message.
     */
    @Test
    public void testGetSendTime() {
        System.out.println("getSendTime");
        Message instance = new Message();
        String expResult = "";
        String result = instance.getSendTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSendTime method, of class Message.
     */
    @Test
    public void testSetSendTime() {
        System.out.println("setSendTime");
        String sendTime = "";
        Message instance = new Message();
        instance.setSendTime(sendTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlatformId method, of class Message.
     */
    @Test
    public void testGetPlatformId() {
        System.out.println("getPlatformId");
        Message instance = new Message();
        int expResult = 0;
        int result = instance.getPlatformId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlatformId method, of class Message.
     */
    @Test
    public void testSetPlatformId() {
        System.out.println("setPlatformId");
        int platformId = 0;
        Message instance = new Message();
        instance.setPlatformId(platformId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
