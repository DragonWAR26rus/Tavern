/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author entropy
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ru.sfedu.tavern.entities.EntitiesSuite.class, ru.sfedu.tavern.utils.UtilsSuite.class, ru.sfedu.tavern.TavernTest.class, ru.sfedu.tavern.ConstantsTest.class, ru.sfedu.tavern.dataproviders.DataprovidersSuite.class, ru.sfedu.tavern.database.DatabaseSuite.class})
public class TavernSuite {
    
}
