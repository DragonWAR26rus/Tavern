/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.database;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author entropy
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ru.sfedu.tavern.database.DbMetaTablesTest.class, ru.sfedu.tavern.database.DbConnectionTest.class})
public class DatabaseSuite {
    
}
