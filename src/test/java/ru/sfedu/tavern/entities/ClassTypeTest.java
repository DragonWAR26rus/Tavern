/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.entities;

import ru.sfedu.tavern.model.entities.ClassType;
import static java.lang.System.console;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author entropy
 */
public class ClassTypeTest {
    
    public ClassTypeTest() {
    }

    /**
     * Test of values method, of class ClassType.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        ClassType[] expResult = null;
        ClassType[] result = ClassType.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class ClassType.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        ClassType expResult = null;
        ClassType result = ClassType.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ClassType.
     */
    @Test
    public void testToString() {
        ClassType test = ClassType.MESSAGE;
        System.out.println(test.toString());
    }

    /**
     * Test of getCl method, of class ClassType.
     */
    @Test
    public void testGetCl() {
        System.out.println("getCl");
        ClassType instance = null;
        Class expResult = null;
        Class result = instance.getCl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
