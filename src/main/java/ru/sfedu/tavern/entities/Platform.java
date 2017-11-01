package ru.sfedu.tavern.entities;

import java.io.Serializable;

/**
 *
 * @author entropy
 */

//Table platform
public class Platform implements Serializable {

    private int id;
    private String domain;
    private String key;
    private int ownerId;
    
    public int getId(){
        return id;
    }
    
    public void setId( int id ){
        this.id = id;
    }
    
    public String getDomain(){
        return domain;
    }
    
    public void setDomain( String domain ){
        this.domain = domain;
    }

    public String getKey(){
        return key;
    }
    
    public void setKey( String key ){
        this.key = key;
    }
    
    public int getOwnerId(){
        return ownerId;
    }
    
    public void setOwnerId( int ownerId ){
        this.ownerId = ownerId;
    }
    
    protected Platform(){}
    public Platform(String domain, String key, int ownerId){
        this.domain = domain;
        this.key = key;
        this.ownerId = ownerId;
    }
    
    @Override
    public String toString() {
        return  "Platform{" +
                "id=" + id + 
                ", key='" + key + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
    
}
