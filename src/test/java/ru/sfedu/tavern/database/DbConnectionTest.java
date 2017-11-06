/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author entropy
 */
public class DbConnectionTest {
    
    public DbConnectionTest() {
    }

    /**
     * Test of openConnection method, of class DbConnection.
     */
    @Test
    public void testOpenConnection() {
        System.out.println("openConnection");
        DbConnection instance = null;
        instance.openConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeConnection method, of class DbConnection.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        DbConnection instance = null;
        instance.closeConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of execSelectState method, of class DbConnection.
     */
    @Test
    public void testExecSelectState() {
        System.out.println("execSelectState");
        DbMetaTables tableName = null;
        DbConnection instance = null;
        ArrayList<ArrayList<Object>> expResult = null;
        ArrayList<ArrayList<Object>> result = instance.execSelectState(tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of execQuery method, of class DbConnection.
     */
    @Test
    public void testExecQuery() {
        System.out.println("execQuery");
        String query = "";
        DbConnection instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.execQuery(query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class DbConnection.
     */
    @Test
    public void testGetInstance() throws Exception {
        System.out.println("getInstance");
        DbConnection expResult = null;
        DbConnection result = DbConnection.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
