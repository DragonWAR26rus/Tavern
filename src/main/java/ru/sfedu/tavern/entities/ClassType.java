/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.entities;

/**
 *
 * @author entropy
 */
public enum ClassType {
    
    OURUSER      (OurUser.class),
    PLATFORM     (Platform.class),
    PLATFORMUSER (PlatformUser.class),
    MESSAGE      (Message.class);
    
    

    private Class _class;
    private ClassType(){};
    
    private ClassType(Class _class) {
        this._class = _class;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public Class getCl() {
        return _class;
    }


}
