package ru.sfedu.tavern.model.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import java.util.ArrayList;

/**
 *
 * @author entropy
 */

//Table platform
public class Platform extends Entity{

    @CsvBindByName
    private String domain;
    @CsvBindByName
    private String key;
    @CsvBindByName
    private long ownerId;
    
    public Platform(){
        super(ClassType.PLATFORM, 1l);
    }
    public Platform(long id, String domain, String key, long ownerId){
        super(ClassType.PLATFORM, id);
        this.domain = domain;
        this.key = key;
        this.ownerId = ownerId;
    }
    public Platform(ArrayList<Object> initList) {
        super(ClassType.PLATFORM, initList.get(0) == null ? 1l : Long.parseLong(initList.get(0).toString()));
        String _domain = initList.get(1) == null ? null : initList.get(1).toString();
        String _key    = initList.get(2) == null ? null : initList.get(2).toString();
        long _ownerId  = initList.get(3) == null ? null : Long.parseLong(initList.get(3).toString());
        
        this.domain  = _domain;
        this.key     = _key;
        this.ownerId = _ownerId;
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
    
    public long getOwnerId(){
        return ownerId;
    }
    
    public void setOwnerId( int ownerId ){
        this.ownerId = ownerId;
    }
    
    @Override
    public String toString() {
        return  "Platform{" +
                "id=" + getId() + 
                ", domain=" + domain +
                ", key='" + key + '\'' +
                ", ownerId=" + ownerId +
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
        
        final Platform other = (Platform) obj;
        if (getId() != other.getId()) {
            return false;
        }
        
        return true;
    }
    
}
