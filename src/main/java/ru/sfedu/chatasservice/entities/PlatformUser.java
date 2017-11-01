/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.chatasservice.entities;

/**
 *
 * @author entropy
 */
//Table platform_user
public class PlatformUser {
    
    private int id;
    private String name;
    private String avatarLink;
    private String lastAct;
    private boolean banned;
    private int platformId;
    
    public int getId() {
        return id;
    }
    
    public void setId( int id ) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName( String name ) {
        this.name = name;
    }
    
    public String getAvatarLink() {
        return avatarLink;
    }
    
    public void setAvatarLink( String avatarLink ) {
        this.avatarLink = avatarLink;
    }
    
    public String getLastAct() {
        return lastAct;
    }
     
    public void setLastAct( String lastAct ) {
        this.lastAct = lastAct;
    }
    
    public boolean getBanned() {
        return banned;
    }
     
    public void getBanned( boolean banned ) {
        this.banned = banned;
    }
    
    public int getPlatformId(){
        return platformId;
    }
    
    public void setPlatformId( int platformId ){
        this.platformId = platformId;
    }
    
    protected PlatformUser(){}
    public PlatformUser(String name, String avatarLink, String lastAct, boolean banned, int platformId) {
        this.name = name;
        this.avatarLink = avatarLink;
        this.lastAct = lastAct;
        this.banned = banned;
        this.platformId = platformId;
    }
    
}
