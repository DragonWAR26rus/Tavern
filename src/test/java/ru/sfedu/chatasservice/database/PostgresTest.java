/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.chatasservice.database;

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
public class PostgresTest {
    
    public PostgresTest() {
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
     * Test of closeConnection method, of class Postgres.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        Postgres instance = null;
        instance.closeConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStatement method, of class Postgres.
     */
    @Test
    public void testCreateStatement() {
        System.out.println("createStatement");
        String query = "";
        Postgres instance = null;
        instance.createStatement(query);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class Postgres.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Postgres expResult = null;
        Postgres result = Postgres.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
