/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.dataprovider;

/**
 *
 * @author entropy
 */
public interface IDataProvider<T> {
    
    void saveRecord(T o);
    
    void deleteRecord(T o);
    
    T getRecordById(long id);
    
    void initDataSource();
    
}
