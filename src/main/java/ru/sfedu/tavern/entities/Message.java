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
// Table message
public class Message implements Serializable {
    
    private int id;
    private int senderId;
    private String text;
    private String sendTime;
    private int platformId;
    
    public int getId() {
        return id;
    }
    
    public void setId( int id ) {
        this.id = id;
    }
    
    public int getSenderId() {
        return senderId;
    }
    
    public void setSenderId( int senderId ) {
        this.senderId = senderId;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText( String text ) {
        this.text = text;
    }
    
    public String getSendTime() {
        return sendTime;
    }
    
    public void setSendTime( String sendTime ) {
        this.sendTime = sendTime;
    }
    
    public int getPlatformId() {
        return platformId;
    }
    
    public void setPlatformId( int platformId ) {
        this.platformId = platformId;
    }
    
    protected Message(){}
    public Message( int senderId, 
                    String text, 
                    String sendTime, 
                    int platformId ){
        this.senderId = senderId;
        this.text = text;
        this.sendTime = sendTime;
        this.platformId = platformId;
    }
    
    @Override
    public String toString() {
        return  "Message{" +
                "senderId=" + senderId + 
                ", text='" + text + '\'' +
                ", sendTime=" + sendTime.toString() +
                ", plaftformId=" + platformId + 
                '}';
    }
    
}
