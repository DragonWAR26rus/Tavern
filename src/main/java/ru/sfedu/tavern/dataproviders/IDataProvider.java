/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataproviders;

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
   
    /**
     * 
     * @param iList List of entities to insert
     * @return Result
     * @throws Exception 
     */
    public Result insert(List<Entity> iList) throws Exception; 
    
    /**
     * 
     * @param optObject Optional entity to insert
     * @return
     * @throws Exception 
     */
    public Result insert(Optional<Entity> optObject) throws Exception; 
    
    /**
     * 
     * @param optObject 
     * @return
     * @throws Exception 
     */
    public Result update(Optional<Entity> optObject) throws Exception;
    
    /**
     * 
     * @param optObject
     * @return
     * @throws Exception 
     */
    public Result delete(Optional<Entity> optObject) throws Exception; 
    
    /**
     * 
     * @param dList
     * @return
     * @throws Exception 
     */
    public Result delete (List<Entity> dList) throws Exception;
    
    /**
     * 
     * @param type
     * @return 
     */
    public long getNextId (ClassType type);

    /**
     * 
     * @param id
     * @param type
     * @return 
     */
    public Optional<Entity> getObjectByID(long id, ClassType type);
    
    /**
     * 
     * @param col
     * @param val
     * @param type
     * @return
     * @throws Exception 
     */
    public List<Entity> select(String col, String val, ClassType type) throws Exception;
    
    /**
     *
     * @param type
     * @return
     * @throws Exception
     */
    public List<Entity> select(ClassType type) throws Exception;
    
    /**
     *
     * @param col
     * @param val
     * @param type
     * @param needWarngs
     * @return
     */
    public List<Entity> select(String col, String val, ClassType type, boolean needWarngs);
    
    /**
     * 
     */
    public void checkAndRemoveAllWrongDependecies();

}
