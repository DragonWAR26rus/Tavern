/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.database;

/**
 *
 * @author entropy
 */
public enum DbMetaTables {
    TEST("test"),
    OUR_USER("our_user"),
    PLATFORM("platform"),
    PLATFORM_USER("platform_user"),
    MESSAGE("message");

    private String name;
    
    private DbMetaTables() {}
    private DbMetaTables( String name ) {
        this.name = name;
    }
    
    public String getTableName() {
        return name;
    }
}
