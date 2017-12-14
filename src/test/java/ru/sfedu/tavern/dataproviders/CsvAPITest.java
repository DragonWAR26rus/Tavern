/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.OurUser;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.PlatformUser;

/**
 *
 * @author entropy
 */
public class CsvAPITest {
    
    public CsvAPITest() {
    }

    /**
     * Test of insert method, of class CsvAPI.
     */
    @Test
    public void testInsert_ArrayList() {
        System.out.println("insert");
        ArrayList<Entity> objectList = null;
        CsvAPI instance = new CsvAPI();
        instance.insert(objectList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class CsvAPI.
     */
    @Test
    public void testInsert_Entity() {
        
        CsvAPI instance = new CsvAPI();
        System.out.println("insert");
        for(int i = 1; i < 4; i++) {
            Entity object1 = new OurUser(i, "Test" + i, "Hash" + i, "123" + i, "qwe" + i + "@mail.ru");
            instance.insert(Optional.ofNullable(object1));
            for(int j = 1; j < 4; j++) {
                int q = (i-1) * 4 + j;
                Entity object2 = new Platform(q, "domain" + q, "key" + q, i);
                instance.insert(Optional.ofNullable(object2));
                for(int x = 1; x < 4; x++) {
                    int z = (i-1) * 4 + (q-1) * 4 + x;
                    Entity object3 = new PlatformUser(z, "login" + z, "link" + z, "321" + z, false, q);
                    instance.insert(Optional.ofNullable(object3));
                    for(int d = 1; d < 4; d++) {
                        int m = (i-1) * 4 + (q-1) * 4 + (z-1) * 4 + d;
                        Entity object4 = new Message(m, z, "555"+m, "dsdasd", q);
                         instance.insert(Optional.ofNullable(object4));
                    }
                }
            }
        }
        
        // Test wrong dependency
        Entity wrongObj = new Platform(112312, "domain", "key", 1233212);
        instance.insert(Optional.ofNullable(wrongObj));
//        instance.insert(object1);
    }

    /**
     * Test of update method, of class CsvAPI.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        OurUser updateableObject = new OurUser(2, "Entropy", "NewPassHash1", "1509574941841", "DragonWAR26russ@gmail.com");
        CsvAPI instance = new CsvAPI();
        try {
            instance.update(Optional.ofNullable(updateableObject));
        } catch (Exception ex) {
            
        }
    }

    /**
     * Test of delete method, of class CsvAPI.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        CsvAPI instance = new CsvAPI();
        Entity removeableObject = instance.getObjectByID(1, ClassType.OURUSER).get();
        Entity removeableObject2 = instance.getObjectByID(3, ClassType.OURUSER).get();
        List<Entity> l = new ArrayList();
        l.add(removeableObject);
        l.add(removeableObject2);
        instance.delete(l);
    }

    /**
     * Test of getObjectByID method, of class CsvAPI.
     */
    @Test
    public void testGetObjectByID() {
        System.out.println("getObjectByID");
        long id = 1L;
        ClassType type = ClassType.PLATFORM;
        CsvAPI instance = new CsvAPI();
        Optional<Entity> expResult = null;
        Optional<Entity> result = instance.getObjectByID(id, type);
        System.out.println(result.toString());
    }

    /**
     * Test of select method, of class CsvAPI.
     */
    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        String col = "id";
        String val = "111";
        ClassType type = ClassType.OURUSER;
        CsvAPI instance = new CsvAPI();
        List<Entity> expResult = null;
        List<Entity> result = instance.select(col, val, type);
        result.stream().forEach(iterator -> {
            System.out.println(iterator.toString());
        });
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
