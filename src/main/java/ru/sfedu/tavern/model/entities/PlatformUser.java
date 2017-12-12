/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.model.entities;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import java.util.ArrayList;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author entropy
 */
//Table platform_user
@Root
public class PlatformUser extends Entity{
    
    @Element
    @CsvBindByPosition(position = 1)
    private long id = 1l;
    @Element
    @CsvBindByPosition(position = 2)
    private String login;
    @Element
    @CsvBindByPosition(position = 3)
    private String avatarLink;
    @Element
    @CsvBindByPosition(position = 4)
    private String lastAct;
    @Element
    @CsvBindByPosition(position = 5)
    private boolean banned;
    @Element
    @CsvBindByPosition(position = 6)
    private long platformId;
    
    public PlatformUser(){
        super(ClassType.PLATFORMUSER);
    }
    public PlatformUser(long id, String login, String avatarLink, String lastAct, boolean banned, long platformId) {
        super(ClassType.PLATFORMUSER);
        this.id         = id;
        this.login      = login;
        this.avatarLink = avatarLink;
        this.lastAct    = lastAct;
        this.banned     = banned;
        this.platformId = platformId;
    }
    public PlatformUser(ArrayList<Object> initList) {
        super(ClassType.PLATFORMUSER);
        this.id             = initList.get(0) == null ? 1l : Long.parseLong(initList.get(0).toString());
        String  _login      = initList.get(1) == null ? null : initList.get(1).toString();
        String  _avatarLink = initList.get(2) == null ? null : initList.get(2).toString();
        String  _lastAct    = initList.get(3) == null ? null : initList.get(3).toString();
        boolean _banned     = initList.get(4) == null ? null : Boolean.parseBoolean(initList.get(4).toString());
        long    _platformId = initList.get(5) == null ? null : Long.parseLong(initList.get(5).toString());
        
        this.login      = _login;
        this.avatarLink = _avatarLink;
        this.lastAct    = _lastAct;
        this.banned     = _banned;
        this.platformId = _platformId;
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
    
    public long getPlatformId(){
        return platformId;
    }
    
    public void setPlatformId( long platformId ){
        this.platformId = platformId;
    }
    
    @Override
    public String toString() {
        return  "PlatformUser{" +
                "id=" + getId() + 
                ", login='" + login + '\'' +
                ", avatarLink='" + avatarLink + '\'' +
                ", lastAct='" + lastAct + '\'' +
                ", banned='" + banned + '\'' + 
                ", plaftformId=" + platformId + 
                '}';
    }
    
    @Override
    public String toString( boolean comma ) {
        if(comma) {
            return  getId() + 
                ", '" + login + '\'' +
                ", '" + avatarLink + '\'' +
                ", '" + lastAct + '\'' +
                ", " + String.valueOf(banned) + 
                ", " + platformId;
        } else {
            return toString();
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final PlatformUser other = (PlatformUser) obj;
        if (getId() != other.getId()) {
            return false;
        }
        
        return true;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
    
}
