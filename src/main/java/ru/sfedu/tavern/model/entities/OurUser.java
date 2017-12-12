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

// Table our_user
@Root
public class OurUser extends Entity{
    
    @Element
    @CsvBindByPosition(position = 1)
    private long id = 1l;
    @Element
    @CsvBindByPosition(position = 2)
    private String login;
    @Element
    @CsvBindByPosition(position = 3)
    private String passwordHash;
    @Element
    @CsvBindByPosition(position = 4)
    private String email;
    @Element
    @CsvBindByPosition(position = 5)
    private String lastAct;
//    @CsvBindByPosition(position = 6)
//    private ArrayList<Integer> platformsId;
    
    public OurUser(){
        super(ClassType.OURUSER);
    }
    public OurUser(long id, String login, String passwordHash, String lastAct, String email) {
        super(ClassType.OURUSER);
        this.id             = id;
        this.login          = login;
        this.passwordHash   = passwordHash;
        this.lastAct        = lastAct;
        this.email          = email;
    }
    public OurUser(ArrayList<Object> initList) {
        super(ClassType.OURUSER);
        this.id = initList.get(0) == null ? 1l : Long.parseLong(initList.get(0).toString());
        String _login        = initList.get(1) == null ? null : initList.get(1).toString();
        String _passwordHash = initList.get(2) == null ? null : initList.get(2).toString();
        String _lastAct      = initList.get(3) == null ? null : initList.get(3).toString();
        String _email        = initList.get(4) == null ? null : initList.get(4).toString();
        
        this.login          = _login;
        this.passwordHash   = _passwordHash;
        this.lastAct        = _lastAct;
        this.email          = _email;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin( String login ) {
        this.login = login;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }
    
    public void setPasswordHash( String passwordHash ) {
        this.passwordHash = passwordHash;
    }
    
    public String getLastAct() {
        return lastAct;
    }
    
    public void setLastAct( String lastAct ) {
        this.lastAct = lastAct;
    }
    
    public String getEmail() {
        return email;
    }
     
    public void setEmail( String email ) {
        this.email = email;
    }
    
    /*
    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }
    
    public void setPlatforms( ArrayList<Platform> platforms ){
        this.platforms = new ArrayList<Platform>();
        platforms.forEach((el)->{if(el.getOwnerId() == id) this.platforms.add(el); });
        this.platforms = platforms;
    }
    
    public void setPlatforms( Platform platform ){
        if(platform.getOwnerId() != id) return;
        this.platforms = new ArrayList<Platform>();
        this.platforms.add(platform);
    }
    */
    
    public String valuesToString(boolean needId) {
        String row = needId ? Long.toString(getId()) + ", '" : "'";
        row += login + "', '" + passwordHash + "', '" + lastAct + "', '" + email + '\'';
        return row;
    }
    
    @Override
    public String toString() {
        return  "OurUser{" +
                "id=" + getId() + 
                ", login='" + login + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", lastAct='" + lastAct + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    
    @Override
    public String toString(boolean comma) {
        if(comma) {
            return getId() + 
                ", '" + login + '\'' +
                ", '" + passwordHash + '\'' +
                ", '" + lastAct + '\'' +
                ", '" + email + '\'';
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
        
        final OurUser other = (OurUser) obj;
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
