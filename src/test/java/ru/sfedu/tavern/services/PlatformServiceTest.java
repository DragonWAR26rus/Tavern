/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.services;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.tavern.dataproviders.CsvAPI;
import ru.sfedu.tavern.dataproviders.IDataProvider;
import ru.sfedu.tavern.dataproviders.JdbcAPI;
import ru.sfedu.tavern.dataproviders.XmlAPI;
import ru.sfedu.tavern.model.Constants;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.OurUser;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.utils.ConfigurationUtil;

/**
 *
 * @author entropy
 */
public class PlatformServiceTest {
    
    public PlatformServiceTest() {
    }

    /**
     * Test of createPlatform method, of class PlatformService.
     */
    @Test
    public void testCreatePlatform() throws IOException {
        IDataProvider dp = new JdbcAPI();
        ConfigurationUtil.setConfigurationEntry(Constants.DP_DRIVER, "jdbc");
        OurUser user = (OurUser) dp.getObjectByID(111, ClassType.OURUSER).get();
        PlatformService.createPlatform("Cool domain", user.getId());
    }

    /**
     * Test of deletePlatform method, of class PlatformService.
     */
    @Test
    public void testDeletePlatform() throws IOException {
        IDataProvider dp = new JdbcAPI();
        ConfigurationUtil.setConfigurationEntry(Constants.DP_DRIVER, "jdbc");
        Platform pl = (Platform) dp.getObjectByID(112, ClassType.PLATFORM).get();
        PlatformService.deletePlatform(pl);
    }

    /**
     * Test of resetKey method, of class PlatformService.
     */
    @Test
    public void testResetKey() throws IOException {
        IDataProvider csv = new CsvAPI();
        IDataProvider xml = new XmlAPI();
        ConfigurationUtil.setConfigurationEntry(Constants.DP_DRIVER, "csv");
        PlatformService.resetKey((Platform)csv.getObjectByID(2, ClassType.PLATFORM).get());
        ConfigurationUtil.setConfigurationEntry(Constants.DP_DRIVER, "xml");
        PlatformService.resetKey((Platform)xml.getObjectByID(2, ClassType.PLATFORM).get());
    }

    /**
     * Test of banUser method, of class PlatformService.
     */
    @Test
    public void testBanUser() {
    }

    /**
     * Test of removeMessage method, of class PlatformService.
     */
    @Test
    public void testRemoveMessage() {
    }

    /**
     * Test of addMessage method, of class PlatformService.
     */
    @Test
    public void testAddMessage() {
    }
    
}
