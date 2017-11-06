/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.entities;

import ru.sfedu.tavern.Constants;

/**
 *
 * @author entropy
 */
public enum ClassType {
    
    OURUSER      (Constants.HEADERS_OURUSER,        OurUser.class),
    PLATFORM     (Constants.HEADERS_PLATFORM,       Platform.class),
    PLATFORMUSER (Constants.HEADERS_PLATFORMUSER,   PlatformUser.class),
    MESSAGE      (Constants.HEADERS_MESSAGE,        Message.class);
    
    

    private Class _class;
    private String[] headers;
    private ClassType(){};
    
    private ClassType(String[] headers, Class _class) {
        this.headers = headers;
        this._class = _class;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public Class getCl() {
        return _class;
    }
    
    public String[] getHeaders() {
        return headers;
    }


}
