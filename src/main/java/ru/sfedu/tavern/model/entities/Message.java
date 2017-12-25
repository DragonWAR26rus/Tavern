/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.model.entities;

import com.opencsv.bean.CsvBindByPosition;
import java.util.ArrayList;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author entropy
 */
// Table message
@Root
public class Message extends Entity{

    @Element
    @CsvBindByPosition(position = 1)
    private long id = 1l;
    @Element
    @CsvBindByPosition(position = 2)
    private long senderId;
    @Element
    @CsvBindByPosition(position = 3)
    private String text;
    @Element
    @CsvBindByPosition(position = 4)
    private long sendTime;
    @Element
    @CsvBindByPosition(position = 5)
    private long platformId;
    
    /**
     *
     */
    public Message(){
        super(ClassType.MESSAGE);
    }

    /**
     *
     * @param id
     * @param senderId
     * @param sendTime
     * @param text
     * @param platformId
     */
    public Message( long id,
                    long senderId,
                    long sendTime,
                    String text,
                    long platformId){
        super(ClassType.MESSAGE);
        this.id = id;
        this.senderId = senderId;
        this.text = text;
        this.sendTime = sendTime;
        this.platformId = platformId;
    }

    /**
     *
     * @param initList
     */
    public Message(ArrayList<Object> initList) {
        super(ClassType.MESSAGE);
        this.id = initList.get(0) == null ? 1l : Long.parseLong(initList.get(0).toString());
        long _senderId    = initList.get(1) == null ? null : Long.parseLong(initList.get(1).toString());
        String _text      = initList.get(2) == null ? null : initList.get(2).toString();
        long _sendTime    = initList.get(3) == null ? null : Long.parseLong(initList.get(3).toString());
        long _platformId  = initList.get(4) == null ? null : Long.parseLong(initList.get(4).toString());
        
        this.senderId           = _senderId;
        this.text               = _text;
        this.sendTime           = _sendTime;
        this.platformId         = _platformId;
    }
    
    /**
     *
     * @return
     */
    public long getSenderId() {
        return senderId;
    }
    
    /**
     *
     * @param senderId
     */
    public void setSenderId( long senderId ) {
        this.senderId = senderId;
    }
    
    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }
    
    /**
     *
     * @param text
     */
    public void setText( String text ) {
        this.text = text;
    }
    
    /**
     *
     * @return
     */
    public long getSendTime() {
        return sendTime;
    }
    
    /**
     *
     * @param sendTime
     */
    public void setSendTime( long sendTime ) {
        this.sendTime = sendTime;
    }
    
    /**
     *
     * @return
     */
    public long getPlatformId() {
        return platformId;
    }
    
    /**
     *
     * @param platformId
     */
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
    
    /**
     *
     * @param comma
     * @return
     */
    @Override
    public String toString(boolean comma) {
        if(comma) {
            return  getId() +
                ", " + senderId + 
                ", '" + text + '\'' +
                ", '" + sendTime + '\'' +
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
        
        final Message other = (Message) obj;
        if (getId() != other.getId()) {
            return false;
        }
        if (getPlatformId()!= other.getPlatformId()) {
            return false;
        }
        if (!getText().equals(other.getText())) {
            return false;
        }
        if (getSenderId()!= other.getSenderId()) {
            return false;
        }
        if (getSendTime()!= other.getSendTime()) {
            return false;
        }
        
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(long id) {
        this.id = id;
    }    
}
