/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.entities;

import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author entropy
 */
public class EntityTest {
    
    public EntityTest() {
    }

    /**
     * Test of getId method, of class Entity.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Entity instance = null;
        long expResult = 0L;
        long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Entity.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 0L;
        Entity instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassType method, of class Entity.
     */
    @Test
    public void testGetClassType() {
        System.out.println("getClassType");
        Entity instance = null;
        ClassType expResult = null;
        ClassType result = instance.getClassType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassType method, of class Entity.
     */
    @Test
    public void testSetClassType() {
        System.out.println("setClassType");
        ClassType classType = null;
        Entity instance = null;
        instance.setClassType(classType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class EntityImpl extends Entity {

        public EntityImpl() {
            super(null, 0L);
        }
    }
    
}
