/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.model;

import ru.sfedu.tavern.dataproviders.IDataProvider;
import ru.sfedu.tavern.model.entities.OurUser;

/**
 *
 * @author entropy
 */
public class AuthSession {
    
    private final OurUser authorisedUser;
    private static AuthSession instance;
    
    private AuthSession(OurUser user) {
        authorisedUser = user;
    }
    
    /**
     *
     * @return
     */
    public static AuthSession getInstance() {
        return instance;
    }
    
    /**
     *
     * @param user
     * @return
     */
    public static AuthSession getInstance(OurUser user) {
        if(instance == null) init(user);
        return instance;
    }

    private static void init(OurUser user) {
        instance = new AuthSession(user);
    }
    
    /**
     *
     * @return
     */
    public OurUser getAuthorisedUser() {
        return authorisedUser;
    }
    
}
