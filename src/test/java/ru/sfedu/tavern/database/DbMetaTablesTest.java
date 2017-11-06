/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.database;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author entropy
 */
public class DbMetaTablesTest {
    
    public DbMetaTablesTest() {
    }

    /**
     * Test of values method, of class DbMetaTables.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        DbMetaTables[] expResult = null;
        DbMetaTables[] result = DbMetaTables.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class DbMetaTables.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        DbMetaTables expResult = null;
        DbMetaTables result = DbMetaTables.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableName method, of class DbMetaTables.
     */
    @Test
    public void testGetTableName() {
        System.out.println("getTableName");
        DbMetaTables instance = null;
        String expResult = "";
        String result = instance.getTableName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
