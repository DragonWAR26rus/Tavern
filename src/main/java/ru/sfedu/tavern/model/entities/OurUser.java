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
    private Long lastAct;
    
    /**
     *
     */
    public OurUser(){
        super(ClassType.OURUSER);
    }

    /**
     *
     * @param id
     * @param login
     * @param passwordHash
     * @param lastAct
     * @param email
     */
    public OurUser(long id, String login, String passwordHash, long lastAct, String email) {
        super(ClassType.OURUSER);
        this.id             = id;
        this.login          = login;
        this.passwordHash   = passwordHash;
        this.lastAct        = lastAct;
        this.email          = email;
    }

    /**
     *
     * @param initList
     */
    public OurUser(ArrayList<Object> initList) {
        super(ClassType.OURUSER);
        this.id = initList.get(0) == null ? 1l : Long.parseLong(initList.get(0).toString());
        String _login        = initList.get(1) == null ? null : initList.get(1).toString();
        String _passwordHash = initList.get(2) == null ? null : initList.get(2).toString();
        long _lastAct        = initList.get(3) == null ? null : Long.parseLong(initList.get(3).toString());
        String _email        = initList.get(4) == null ? null : initList.get(4).toString();
        
        this.login          = _login;
        this.passwordHash   = _passwordHash;
        this.lastAct        = _lastAct;
        this.email          = _email;
    }
    
    /**
     *
     * @return
     */
    public String getLogin() {
        return login;
    }
    
    /**
     *
     * @param login
     */
    public void setLogin( String login ) {
        this.login = login;
    }
    
    /**
     *
     * @return
     */
    public String getPasswordHash() {
        return passwordHash;
    }
    
    /**
     *
     * @param passwordHash
     */
    public void setPasswordHash( String passwordHash ) {
        this.passwordHash = passwordHash;
    }
    
    /**
     *
     * @return
     */
    public long getLastAct() {
        return lastAct;
    }
    
    /**
     *
     * @param lastAct
     */
    public void setLastAct( long lastAct ) {
        this.lastAct = lastAct;
    }
    
    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }
     
    /**
     *
     * @param email
     */
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

    /**
     *
     * @param needId
     * @return
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
    
    /**
     *
     * @param comma
     * @return
     */
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
        if (!getLogin().equals(other.getLogin())) {
            return false;
        }
        if (!getPasswordHash().equals(other.getPasswordHash())) {
            return false;
        }
        if (!getEmail().equals(other.getEmail())) {
            return false;
        }
        if (getLastAct()!= other.getLastAct()) {
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
