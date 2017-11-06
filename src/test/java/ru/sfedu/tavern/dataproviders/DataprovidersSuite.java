/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author entropy
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ru.sfedu.tavern.dataproviders.IDataProviderTest.class, ru.sfedu.tavern.dataproviders.CsvToolsTest.class, ru.sfedu.tavern.dataproviders.DbToolsTest.class})
public class DataprovidersSuite {
    
}
