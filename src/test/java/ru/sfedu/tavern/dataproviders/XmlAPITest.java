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
public class XmlAPITest {
    
    public XmlAPITest() {
    }

    /**
     * Test of insert method, of class XmlAPI.
     */
    @Test
    public void testInsert_List() throws Exception {
    }

    /**
     * Test of insert method, of class XmlAPI.
     */
    @Test
    public void testInsert_Entity() throws Exception {
        XmlAPI instance = new XmlAPI();
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
        
    }

    /**
     * Test of update method, of class XmlAPI.
     */
    @Test
    public void testUpdate() throws Exception {
    }

    /**
     * Test of delete method, of class XmlAPI.
     */
    @Test
    public void testDelete() throws Exception {
    }

    /**
     * Test of getObjectByID method, of class XmlAPI.
     */
    @Test
    public void testGetObjectByID() {
    }

    /**
     * Test of select method, of class XmlAPI.
     */
    @Test
    public void testSelect() throws Exception {
        XmlAPI instance = new XmlAPI();
        List<Entity> list = new ArrayList();
        list.addAll(instance.select("ownerId", "2", ClassType.PLATFORM));
        list.forEach(System.out::println);
    }
    
}
