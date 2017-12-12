/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ru.sfedu.tavern.model.Result;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;

/**
 *
 * @author entropy
 */
public interface IDataProvider {
   
    public Result insert(ArrayList<Entity> object) throws Exception; 
    
    public Result insert(Entity object) throws Exception; 
    
    public Result update(Entity object) throws Exception;
    
    public Result delete(Entity object) throws Exception; 

    public Optional<Entity> getObjectByID(long id, ClassType type);
    
    public List<Entity> select(String col, String val, ClassType type) throws Exception;
}
