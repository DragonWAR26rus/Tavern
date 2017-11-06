package ru.sfedu.tavern.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import java.util.ArrayList;

/**
 *
 * @author entropy
 */

// Table our_user
public class OurUser extends Entity{
    
    @CsvBindByName
    private String login;
    @CsvBindByName
    private String passwordHash;
    @CsvBindByName
    private String email;
    @CsvBindByName
    private String lastAct;
    //private ArrayList<Platform> platforms;
    
    public OurUser(){
        super(ClassType.OURUSER, 1l);
    }
    public OurUser(long id, String login, String passwordHash, String lastAct, String email) {
        super(ClassType.OURUSER, id);
        this.login          = login;
        this.passwordHash   = passwordHash;
        this.lastAct        = lastAct;
        this.email          = email;
    }
    public OurUser(ArrayList<Object> initList) {
        super(ClassType.OURUSER, initList.get(0) == null ? 1l : Long.parseLong(initList.get(0).toString()));
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
    
}
