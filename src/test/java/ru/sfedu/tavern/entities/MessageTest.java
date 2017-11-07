/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.entities;

import ru.sfedu.tavern.model.entities.Message;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author entropy
 */
public class MessageTest {
    
    public MessageTest() {
    }

    /**
     * Test of getSenderId method, of class Message.
     */
    @Test
    public void testGetSenderId() {
        System.out.println("getSenderId");
        Message instance = new Message();
        long expResult = 0L;
        long result = instance.getSenderId();
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
        long senderId = 0L;
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
        long expResult = 0L;
        long result = instance.getPlatformId();
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
        long platformId = 0L;
        Message instance = new Message();
        instance.setPlatformId(platformId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Message.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Message instance = new Message();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Message.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Message instance = new Message();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
