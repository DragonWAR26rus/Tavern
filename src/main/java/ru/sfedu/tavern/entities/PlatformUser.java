/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.entities;

import java.io.Serializable;

/**
 *
 * @author entropy
 */
//Table platform_user
public class PlatformUser implements Serializable {
    
    private int id;
    private String login;
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
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin( String login ) {
        this.login = login;
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
        this.login = name;
        this.avatarLink = avatarLink;
        this.lastAct = lastAct;
        this.banned = banned;
        this.platformId = platformId;
    }
    
    @Override
    public String toString() {
        return  "PlatformUser{" +
                "id=" + id + 
                ", login='" + login + '\'' +
                ", avatarLink='" + avatarLink + '\'' +
                ", lastAct=" + lastAct.toString() +
                ", banned='" + banned + '\'' + 
                ", plaftformId=" + platformId + 
                '}';
    }
    
}
