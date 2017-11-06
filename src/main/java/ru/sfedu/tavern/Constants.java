
package ru.sfedu.tavern;

/**
 *
 * @author entropy
 */
public class Constants {

    public static final String PATH_TO_SCV  = "csv.path";
    public static final String DB_LOGIN     = "database.login";
    public static final String DB_PASSWORD  = "database.password";
    public static final String DB_URL       = "database.url";
    public static final String DB_DRIVER    = "database.driver";
    
    public static final String[] HEADERS_OURUSER = new String[] {
        "id", 
        "login", 
        "passwordHash", 
        "email", 
        "lastAct"
    };
    
    public static final String[] HEADERS_PLATFORM  = new String[] {
        "id", 
        "domain", 
        "key", 
        "ownerId"
    };
    
    public static final String[] HEADERS_MESSAGE = new String[] {
        "id", 
        "senderId", 
        "text", 
        "sendTime", 
        "platformId"
    };
    
    public static final String[] HEADERS_PLATFORMUSER = new String[] {
        "id", 
        "login",
        "avatarLink", 
        "lastAct", 
        "banned", 
        "platformId"
    };
    
    
    private Constants() {}

}
