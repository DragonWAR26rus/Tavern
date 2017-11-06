/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author entropy
 */
public class ConfigurationUtilTest {
    
    public ConfigurationUtilTest() {
    }

    /**
     * Test of getConfigurationEntry method, of class ConfigurationUtil.
     */
    @Test
    public void testGetConfigurationEntry() throws Exception {
        System.out.println("getConfigurationEntry");
        String key = "";
        String expResult = "";
        String result = ConfigurationUtil.getConfigurationEntry(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
