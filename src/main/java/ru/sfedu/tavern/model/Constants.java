
package ru.sfedu.tavern.model;

/**
 *
 * @author entropy
 */
public class Constants {

    public static final String PATH_TO_SCV  = "csv.path";
    public static final String PATH_TO_XML  = "xml.path";
    public static final String DB_LOGIN     = "database.login";
    public static final String DB_PASSWORD  = "database.password";
    public static final String DB_URL       = "database.url";
    public static final String DB_DRIVER    = "database.driver";
    
    public static final String OURUSER_FILENAME      = "ouruser";
    public static final String PLATFORM_FILENAME     = "platform";
    public static final String PLATFORMUSER_FILENAME = "platformuser";
    public static final String MESSAGE_FILENAME      = "message";
    
    public static final String OURUSER_TABLE      = "our_user";
    public static final String PLATFORM_TABLE     = "platform";
    public static final String PLATFORMUSER_TABLE = "platform_user";
    public static final String MESSAGE_TABLE      = "message";
    
    
    public static final String[] HEADERS_OURUSER = new String[] {
        "id", 
        "login", 
        "passwordHash",
        "lastAct",
        "email",
//        ""
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
