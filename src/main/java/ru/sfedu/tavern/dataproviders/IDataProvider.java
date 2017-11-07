/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

import java.util.ArrayList;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;

/**
 *
 * @author entropy
 */
public interface IDataProvider {
   
    public void insert(ArrayList<Entity> object) throws Exception; 
    
    public void insert(Entity object) throws Exception; 
    
    public void update(Entity object) throws Exception;
    
    public void delete(Entity object) throws Exception; 

    public Entity getObjectByID(long id, ClassType type) throws Exception;
    
}
