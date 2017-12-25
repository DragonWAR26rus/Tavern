
package ru.sfedu.tavern.model;

import java.util.HashMap;

/**
 *
 * @author entropy
 */
public class Constants {

    public static final String CONFIG_PATH_TO_SCV  = "csv.path";
    public static final String CONFIG_PATH_TO_XML  = "xml.path";
    public static final String CONFIG_DB_LOGIN     = "database.login";
    public static final String CONFIG_DB_PASSWORD  = "database.password";
    public static final String CONFIG_DB_URL       = "database.url";
    public static final String CONFIG_DB_DRIVER    = "database.driver";
    public static final String CONFIG_DP_DRIVER    = "dataprovider.driver";
    
    public static final String OURUSER_FILENAME      = "ouruser";
    public static final String PLATFORM_FILENAME     = "platform";
    public static final String PLATFORMUSER_FILENAME = "platformuser";
    public static final String MESSAGE_FILENAME      = "message";
    
    public static final String OURUSER_TABLE      = "our_user";
    public static final String PLATFORM_TABLE     = "platform";
    public static final String PLATFORMUSER_TABLE = "platform_user";
    public static final String MESSAGE_TABLE      = "message";
    
    public static final String CLI_MODE_ARG_REGISTRATION    = "-r";
    public static final String CLI_MODE_ARG_HELP            = "help";
    public static final String CLI_ARG_EXIT                 = "exit";
    public static final String CLI_ARG_DATA_PROVIDER        = "-dp";
    public static final String CLI_ARG_LOGIN                = "-l";
    public static final String CLI_ARG_PASSWORD             = "-p";
    public static final String CLI_ARG_EMAIL                = "-e";
    public static final String CLI_ARG_METHOD               = "-m";
    public static final String CLI_ARG_ENTITY_TYPE          = "-t";
    public static final String CLI_ARG_ENTITY_ID            = "-id";
    public static final String CLI_ARG_PLATFROM_NAME        = "-pn";
    
    public static final String CLI_ARG_PLATFROM_METHOD_CREATE           = "create_platform";
    public static final String CLI_ARG_PLATFROM_METHOD_REMOVE           = "remove_platform";
    public static final String CLI_ARG_PLATFROM_METHOD_BAN              = "ban_user";
    public static final String CLI_ARG_PLATFROM_METHOD_RESET_KEY        = "reset_key";
    public static final String CLI_ARG_PLATFROM_METHOD_GET_KEY          = "get_key";
    public static final String CLI_ARG_PLATFROM_METHOD_REMOVE_MESSAGE   = "remove_message";
    public static final String CLI_ARG_PLATFROM_METHOD_GET_LIST         = "get_list";
    public static final String CLI_ARG_PLATFROM_METHOD_APPEND_USER      = "append_user";
    public static final String CLI_ARG_PLATFROM_METHOD_APPEND_MESSAGE   = "append_message";
    
    public static final String CLI_ARG_METHOD_ARG_PRIMARY_ID        = "-id";
    public static final String CLI_ARG_METHOD_ARG_NAME              = "-n";
    public static final String CLI_ARG_METHOD_ARG_SECOND_STRING     = "-s2";
    public static final String CLI_ARG_METHOD_ARG_THIRD_STRING      = "-s3";
    public static final String CLI_ARG_METHOD_ARG_BAN               = "-b";
    public static final String CLI_ARG_METHOD_ARG_SECONDARY_ID      = "-sid";
    public static final String CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES  = "-tws";
    
    
    public static final String CLI_ARG_METHOD_ARG_ID_PLACEHOLDER                = " <id>";
    public static final String CLI_ARG_METHOD_ARG_NAME_PLACEHOLDER              = " <name>";
    public static final String CLI_ARG_METHOD_ARG_DOMAIN_PLACEHOLDER            = " <domain>";
    public static final String CLI_ARG_METHOD_ARG_LOGIN_PLACEHOLDER             = " <login>";
    public static final String CLI_ARG_METHOD_ARG_AVATAR_LINK_PLACEHOLDER       = " <avatar link>";
    public static final String CLI_ARG_METHOD_ARG_TRUE_FALSE_PLACEHOLDER        = " <true|false>";
    public static final String CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES_PLACEHOLDER  = " <text with spaces>";
    
    public static final String[] CLI_ARG_PLATFROM_METHOD_CREATE_REQUIRED_PARAMS = new String[] {
        CLI_ARG_METHOD_ARG_NAME + CLI_ARG_METHOD_ARG_DOMAIN_PLACEHOLDER
    };
    public static final String[] CLI_ARG_PLATFROM_METHOD_REMOVE_REQUIRED_PARAMS = new String[] {
        CLI_ARG_METHOD_ARG_NAME + CLI_ARG_METHOD_ARG_DOMAIN_PLACEHOLDER
    };
    public static final String[] CLI_ARG_PLATFROM_METHOD_BAN_REQUIRED_PARAMS = new String[] {
        CLI_ARG_METHOD_ARG_BAN + CLI_ARG_METHOD_ARG_TRUE_FALSE_PLACEHOLDER
    };
    public static final String[] CLI_ARG_PLATFROM_METHOD_RESET_KEY_REQUIRED_PARAMS = new String[] {
        CLI_ARG_METHOD_ARG_NAME + CLI_ARG_METHOD_ARG_DOMAIN_PLACEHOLDER
    };
    public static final String[] CLI_ARG_PLATFROM_METHOD_GET_KEY_REQUIRED_PARAMS = new String[] {
        CLI_ARG_METHOD_ARG_NAME + CLI_ARG_METHOD_ARG_DOMAIN_PLACEHOLDER
    };
    public static final String[] CLI_ARG_PLATFROM_METHOD_REMOVE_MESSAGE_REQUIRED_PARAMS = new String[] {
        CLI_ARG_METHOD_ARG_PRIMARY_ID + CLI_ARG_METHOD_ARG_ID_PLACEHOLDER
    };
    public static final String[] CLI_ARG_PLATFROM_METHOD_GET_LIST_REQUIRED_PARAMS = new String[] {
    };
    public static final String[] CLI_ARG_PLATFROM_METHOD_APPEND_USER_REQUIRED_PARAMS = new String[] {
        CLI_ARG_METHOD_ARG_NAME + CLI_ARG_METHOD_ARG_DOMAIN_PLACEHOLDER,
        CLI_ARG_METHOD_ARG_SECOND_STRING + CLI_ARG_METHOD_ARG_LOGIN_PLACEHOLDER,
        CLI_ARG_METHOD_ARG_THIRD_STRING + CLI_ARG_METHOD_ARG_AVATAR_LINK_PLACEHOLDER,
    };
    public static final String[] CLI_ARG_PLATFROM_METHOD_APPEND_MESSAGE_REQUIRED_PARAMS = new String[] {
        CLI_ARG_METHOD_ARG_NAME + CLI_ARG_METHOD_ARG_DOMAIN_PLACEHOLDER,
        CLI_ARG_METHOD_ARG_SECOND_STRING + CLI_ARG_METHOD_ARG_LOGIN_PLACEHOLDER,
        CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES + CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES_PLACEHOLDER,
    };

//    public static final String[] CLI_ARG_METHODS_ARGS = new String[] {
//        CLI_ARG_METHOD_ARG_PRIMARY_ID,
//        CLI_ARG_METHOD_ARG_NAME,
//        CLI_ARG_METHOD_ARG_BAN,
//        CLI_ARG_METHOD_ARG_SECONDARY_ID,
//        CLI_ARG_METHOD_ARG_SECOND_STRING,
//        CLI_ARG_METHOD_ARG_THIRD_STRING,
//        CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES
//    };
    
    public static final String[] CLI_ARG_PLATFORMS_METHODS = new String[] {
        CLI_ARG_PLATFROM_METHOD_CREATE,
        CLI_ARG_PLATFROM_METHOD_REMOVE,
        CLI_ARG_PLATFROM_METHOD_BAN,
        CLI_ARG_PLATFROM_METHOD_RESET_KEY,
        CLI_ARG_PLATFROM_METHOD_GET_KEY,
        CLI_ARG_PLATFROM_METHOD_REMOVE_MESSAGE,
        CLI_ARG_PLATFROM_METHOD_GET_LIST,
        CLI_ARG_PLATFROM_METHOD_APPEND_USER,
        CLI_ARG_PLATFROM_METHOD_APPEND_MESSAGE,
    };
    
    public static final String[] HEADERS_OURUSER = new String[] {
        "id", 
        "login", 
        "passwordHash",
        "lastAct",
        "email",
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
