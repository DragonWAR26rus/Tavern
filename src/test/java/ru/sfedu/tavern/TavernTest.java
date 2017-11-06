/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.tavern.database.DbConnection;
import ru.sfedu.tavern.database.DbMetaTables;
import ru.sfedu.tavern.entities.*;

/**
 *
 * @author entropy
 */
public class TavernTest {
    
    public TavernTest() {
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
     * Test of main method, of class Tavern.
     */
    @Test
    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        Tavern.main(args);
            try {
                DbConnection instence = DbConnection.getInstance();
    //            String row = "'Admin', 'e3afed0047b08059d0fada10f400c1e5','test@mail.ru', '" + System.currentTimeMillis() + '\'';
    //            System.out.println(row);
    //            instence.execQuery("Insert into our_user(login, password_hash, email, last_act) values(" + row  + ")");
                // SLECT OUR USER
                ArrayList<ArrayList<Object>> res =  instence.execSelectState(DbMetaTables.OUR_USER);
                ArrayList<OurUser> userList = new ArrayList();
                res.forEach( (args)->userList.add( new OurUser(args) ) );
    
                // SELECT PLATFORMS
                res =  instence.execSelectState(DbMetaTables.PLATFORM);
                ArrayList<Platform> platformList = new ArrayList();
                res.forEach( (args)->platformList.add( new Platform(args) ) );
                
                // SELECT PLATFORMS USERS
                res =  instence.execSelectState(DbMetaTables.PLATFORM_USER);
                ArrayList<PlatformUser> platformUsersList = new ArrayList();
                res.forEach( (args)->platformUsersList.add( new PlatformUser(args) ) );
                
                // SELECT MESSSAGES
                res =  instence.execSelectState(DbMetaTables.MESSAGE);
                ArrayList<Message> messagesList = new ArrayList();
                res.forEach( (args)->messagesList.add( new Message(args) ) );
                
                // SELECT TEST
                instence.execSelectState(DbMetaTables.TEST);
                instence.closeConnection();
                
                userList.forEach( (val)->System.out.println(val.toString()) );
                platformList.forEach( (val)->System.out.println(val.toString()) );
                platformUsersList.forEach( (val)->System.out.println(val.toString()) );
                messagesList.forEach( (val)->System.out.println(val.toString()) );
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
