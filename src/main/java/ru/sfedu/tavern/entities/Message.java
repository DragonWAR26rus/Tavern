/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.entities;

import java.util.ArrayList;

/**
 *
 * @author entropy
 */
// Table message
public class Message extends Entity{
    
    private long senderId;
    private String text;
    private String sendTime;
    private long platformId;
    
    public Message(){
        super(ClassType.MESSAGE, 1l);
    }
    public Message( long id,
                    long senderId, 
                    long platformId,
                    String sendTime,
                    String text){
        super(ClassType.MESSAGE, id);
        this.senderId = senderId;
        this.text = text;
        this.sendTime = sendTime;
        this.platformId = platformId;
    }
    public Message(ArrayList<Object> initList) {
        super(ClassType.MESSAGE, initList.get(0) == null ? 1l : Long.parseLong(initList.get(0).toString()));
        long _senderId    = initList.get(1) == null ? null : Long.parseLong(initList.get(0).toString());
        String _text      = initList.get(2) == null ? null : initList.get(2).toString();
        String _send_time = initList.get(3) == null ? null : initList.get(3).toString();
        long _platformId  = initList.get(4) == null ? null : Long.parseLong(initList.get(0).toString());
        
        this.senderId           = _senderId;
        this.text               = _text;
        this.sendTime           = _send_time;
        this.platformId         = _platformId;
    }
    
    public long getSenderId() {
        return senderId;
    }
    
    public void setSenderId( long senderId ) {
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
    
    public long getPlatformId() {
        return platformId;
    }
    
    public void setPlatformId( long platformId ) {
        this.platformId = platformId;
    }
    
    @Override
    public String toString() {
        return  "Message{" +
                "id=" + getId() +
                ", senderId=" + senderId + 
                ", text='" + text + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", plaftformId=" + platformId + 
                '}';
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
        
        final Message other = (Message) obj;
        if (getId() != other.getId()) {
            return false;
        }
        
        return true;
    }
    
}
