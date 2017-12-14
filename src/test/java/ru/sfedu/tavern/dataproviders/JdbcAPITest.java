/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

import java.util.Optional;
import org.junit.Test;
import ru.sfedu.tavern.database.DbConnection;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Platform;

/**
 *
 * @author entropy
 */
public class JdbcAPITest {
    
    public JdbcAPITest() {
    }

    /**
     * Test of insert method, of class JdbcAPI.
     */
    @Test
    public void testInsert_ArrayList() throws Exception {
    }

    /**
     * Test of insert method, of class JdbcAPI.
     */
    @Test
    public void testInsert_Entity() throws Exception {
        
    }

    /**
     * Test of update method, of class JdbcAPI.
     */
    @Test
    public void testUpdate() throws Exception {
        JdbcAPI jdbc = new JdbcAPI();
        testSelect_ClassType();
        DbConnection.beginTransaction();
        System.out.println("======================= TEST UPDATE =======================");
        jdbc.select("id", "111", ClassType.PLATFORM).stream().forEach(iterator -> {
            try {
                ((Platform) iterator).setKey("MyKEeeeYYyYY");
                jdbc.update(Optional.ofNullable(iterator));
                testSelect_ClassType();
                System.out.println("======================= ROLLBACK =======================");
                DbConnection.rollbackTransaction();
                testSelect_ClassType();                
            } catch ( Exception ex ) {}
        });
    }

    /**
     * Test of delete method, of class JdbcAPI.
     */
    @Test
    public void testDelete() throws Exception {
        JdbcAPI jdbc = new JdbcAPI();
        testSelect_ClassType();
        DbConnection.beginTransaction();
//        List<Entity> ourUserList = jdbc.select(ClassType.OURUSER);
//        List<Entity> platformList = jdbc.select(ClassType.PLATFORM);
//        List<Entity> platformUserList = jdbc.select(ClassType.PLATFORMUSER);
//        List<Entity> messageList = jdbc.select(ClassType.MESSAGE);

        System.out.println("======================= TEST DELETING =======================");
        jdbc.select("id", "111", ClassType.PLATFORM).stream().forEach(iterator -> {
            try {
                jdbc.delete(Optional.ofNullable(iterator));
                testSelect_ClassType();
//                jdbc.insert((ArrayList)ourUserList);
//                jdbc.insert((ArrayList)platformList);
//                jdbc.insert((ArrayList)platformUserList);
//                jdbc.insert((ArrayList)messageList);
                System.out.println("======================= ROLLBACK =======================");
            DbConnection.rollbackTransaction();
                testSelect_ClassType();                
            } catch ( Exception ex ) {}
        });
    }

    /**
     * Test of getObjectByID method, of class JdbcAPI.
     */
    @Test
    public void testGetObjectByID() {
    }

    /**
     * Test of select method, of class JdbcAPI.
     */
    @Test
    public void testSelect_ClassType() throws Exception {
        JdbcAPI jdbc = new JdbcAPI();
        jdbc.select(ClassType.OURUSER).stream().forEach(iterator -> {
            System.out.println(iterator.toString());
        });
        
        jdbc.select(ClassType.PLATFORM).stream().forEach(iterator -> {
            System.out.println(iterator.toString());
        });
        
        jdbc.select(ClassType.PLATFORMUSER).stream().forEach(iterator -> {
            System.out.println(iterator.toString());
        });
        
        jdbc.select(ClassType.MESSAGE).stream().forEach(iterator -> {
            System.out.println(iterator.toString());
        });
    }

    /**
     * Test of select method, of class JdbcAPI.
     */
    @Test
    public void testSelect_3args() throws Exception {
        
    }
    
}
