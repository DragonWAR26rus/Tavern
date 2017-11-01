package ru.sfedu.chatasservice.entities;

/**
 *
 * @author entropy
 */

// Table our_user
public class OurUser {
    
    private int id;
    private String login;
    private String password;
    private String email;
    
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
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword( String password ) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
     
    public void setEmail( String email ) {
        this.email = email;
    }
    
    protected OurUser(){}
    public OurUser(String email, String login, String password) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    
}
