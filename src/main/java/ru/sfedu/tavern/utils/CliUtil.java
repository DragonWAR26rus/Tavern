package ru.sfedu.tavern.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.dataproviders.IDataProvider;
import ru.sfedu.tavern.model.AuthSession;
import ru.sfedu.tavern.model.Constants;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.OurUser;

/**
 *
 * @author entropy
 */
public class CliUtil {

    
    private static Logger log = Logger.getLogger(CliUtil.class);
    private static AuthSession session;
    private static Map<String, String> flagsValues = new HashMap();
    private static Map<String, Boolean> flagsSemaphore = new HashMap();
    private static Map<String, Boolean> modeFlags = new HashMap();
    private static boolean waitingValues = false;
    
    // Mode flags never changes if enabled
    private static final String[] modeFlagsArray = new String[] {
        Constants.CLI_MODE_ARG_REGISTRATION, // Registration flag
        Constants.CLI_MODE_ARG_HELP,
    };
    
    // After these flags follows values 
    private static final String[] otherFlagsArray = new String[] {
        Constants.CLI_ARG_LOGIN,
        Constants.CLI_ARG_PASSWORD,
        Constants.CLI_ARG_DATA_PROVIDER,
        Constants.CLI_ARG_EMAIL,
        Constants.CLI_ARG_METHOD,
        Constants.CLI_ARG_ENTITY_TYPE,
        Constants.CLI_ARG_ENTITY_ID,
        Constants.CLI_ARG_PLATFROM_NAME,
        Constants.CLI_ARG_METHOD_ARG_PRIMARY_ID,
        Constants.CLI_ARG_METHOD_ARG_SECONDARY_ID,
        Constants.CLI_ARG_METHOD_ARG_BAN,
        Constants.CLI_ARG_METHOD_ARG_NAME,
        Constants.CLI_ARG_METHOD_ARG_SECOND_STRING,
        Constants.CLI_ARG_METHOD_ARG_THIRD_STRING,
        Constants.CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES,
    };
    
    private CliUtil() {}
    
    /**
     *
     * @param args
     * @return
     */
    public static boolean authorize(String[] args) { 
        boolean authorized = false;
        Arrays.asList(args).stream().forEach((Consumer<? super String>) arg -> {
            if(!waitingValues){
                if(Arrays.asList(modeFlagsArray).contains(arg)){
                    modeFlags.put(arg, true);
                } else if (Arrays.asList(otherFlagsArray).contains(arg)) {
                    flagsSemaphore.keySet().stream().forEach(e -> flagsSemaphore.put(e, Boolean.FALSE));
                    flagsSemaphore.put(arg, Boolean.TRUE);
                    if(arg.equals(Constants.CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES)) modeFlags.put(arg, true);
                    waitingValues = true;
                } else {
                    log.error("Unknown argument: " + arg);
                }
            } else {
                Optional<String> flag = flagsSemaphore.keySet().stream().filter(e -> flagsSemaphore.get(e)).findAny();
                if(flag.isPresent()) {
                    
                    if(!modeFlags.containsKey(Constants.CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES)) {
                        flagsValues.put(flag.get(), arg);
                        flagsSemaphore.put(flag.get(), false);
                        waitingValues = false;
                    } else {
                        flagsValues.put(flag.get(), flagsValues.get(flag.get()) + " " + arg);
                    }
                } else {
                    log.error("Unknown argument: " + arg);
                }
            }
        });
        flagsValues.keySet().forEach(key -> log.debug(key + ": " + flagsValues.get(key)));
        modeFlags.keySet().forEach(key -> log.debug(key + ": " + modeFlags.get(key)));
        DataProviderSessionUtil dps = DataProviderSessionUtil.getInstance();
        String dataProviderDriver = flagsValues.get(Constants.CLI_ARG_DATA_PROVIDER);
        if(dataProviderDriver != null) dps.setDataProvider(dataProviderDriver);
        if(!flagsValues.containsKey(Constants.CLI_ARG_LOGIN) || !flagsValues.containsKey(Constants.CLI_ARG_PASSWORD)) {
            log.error("Use params -l <login> -p <password> for enter");
            System.exit(1);
        } else {
            String login = flagsValues.get(Constants.CLI_ARG_LOGIN);
            String password = flagsValues.get(Constants.CLI_ARG_PASSWORD);
            // If need registration need
            if(modeFlags.containsKey(Constants.CLI_MODE_ARG_REGISTRATION)) {
                if(!flagsValues.containsKey(Constants.CLI_ARG_EMAIL)) {
                    log.error("Param " + Constants.CLI_ARG_EMAIL + " <email> is required");
                    System.exit(1);
                } else {
                    String email = flagsValues.get(Constants.CLI_ARG_EMAIL);
                    AuthUtils.registration(login, password, email, false);
                    session = AuthSession.getInstance();
                    authorized = true;
                }
            } else {
                String passHash = AuthUtils.getHash(password);
                IDataProvider dp = dps.getDataProvider();
                try {
                    OurUser u = (OurUser)(dp.select("login", login, ClassType.OURUSER).get(0));
                    if(passHash.equals(u.getPasswordHash())) {
                        u.setLastAct(System.currentTimeMillis());
                        session = AuthSession.getInstance(u);
                        authorized = true;
                    } else {
                        log.error("Bad login:password.");
                        System.exit(2);
                    }
                } catch (Exception ex) {
                    log.error("There is no user. Use -r to registration.");
                }
            }
        }
        flagsValues.clear();
        modeFlags.clear();
        flagsSemaphore.clear();
        waitingValues = false;
        return authorized;
    }
    
    /**
     *
     */
    public static void remoteControl() {
        Scanner scanner = new Scanner(System.in);
        log.info("Tavern~" + session.getAuthorisedUser().getLogin() + 
                ":" + DataProviderSessionUtil.getDataProviderName() + "> ");
        String[] args = scanner.nextLine().trim().split(" ");
        execCommand(args);
        remoteControl();
    }
    
    private static void execCommand(String[] args) {
        Arrays.asList(args).stream().forEach((Consumer<? super String>) arg -> {
            // Skip spaces
            if(arg.equals(" ")) return;
            if(arg.toLowerCase().equals(Constants.CLI_ARG_EXIT)){
                log.info("Bye!");
                System.exit(0);
            };
            if(!waitingValues){
                if(Arrays.asList(modeFlagsArray).contains(arg)){
                    modeFlags.put(arg, true);
                } else if (Arrays.asList(otherFlagsArray).contains(arg)) {
                    flagsSemaphore.keySet().stream().forEach(e -> flagsSemaphore.put(e, Boolean.FALSE));
                    flagsSemaphore.put(arg, Boolean.TRUE);
                    if(arg.equals(Constants.CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES)) modeFlags.put(arg, true);
                    waitingValues = true;
                } else {
                    log.error("Unknown argument: " + arg);
                }
            } else {
                Optional<String> flag = flagsSemaphore.keySet().stream().filter(e -> flagsSemaphore.get(e)).findAny();
                
                if(flag.isPresent()) {
                    if(flag.get().equals(Constants.CLI_ARG_METHOD)) {
                        if(Arrays.asList(Constants.CLI_ARG_PLATFORMS_METHODS).contains(arg)) {
                           flagsValues.put(flag.get(), arg);
                            flagsSemaphore.put(flag.get(), false);
                            waitingValues = false;
                        } else {
                            log.error("Unknown method \"" + flag.get() + "\" for type platform");
                        }
                    } else {
                        if(!modeFlags.containsKey(Constants.CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES)) {
                            flagsValues.put(flag.get(), arg);
                            flagsSemaphore.put(flag.get(), false);
                            waitingValues = false;
                        } else {
                            flagsValues.put(flag.get(), flagsValues.get(flag.get()) + " " + arg);
                        }
                    }
                } else {
                    log.error("Unknown argument: " + arg);
                }
            }
        });
        flagsValues.keySet().forEach(key -> log.debug(key + ": " + flagsValues.get(key)));
        modeFlags.keySet().forEach(key -> log.debug(key + ": " + modeFlags.get(key)));
        if(flagsValues.containsKey(Constants.CLI_ARG_DATA_PROVIDER)) {
            OurUser cUser = session.getAuthorisedUser();
            DataProviderSessionUtil dps = DataProviderSessionUtil.getInstance();
            dps.setDataProvider(flagsValues.get(Constants.CLI_ARG_DATA_PROVIDER));
            AuthUtils.registration(cUser.getLogin(), cUser.getPasswordHash(), cUser.getEmail(), true);
        }
        if(!flagsValues.containsKey(Constants.CLI_ARG_METHOD)) {
            if(modeFlags.containsKey(Constants.CLI_MODE_ARG_HELP)){
                showHelp();
            } else {
                if(!flagsValues.containsKey(Constants.CLI_ARG_DATA_PROVIDER)){
                    log.error("Should enter -m <method> <methods args>"); 
                }
            }
        } else {
            if(modeFlags.containsKey(Constants.CLI_MODE_ARG_HELP)){
                showHelp(flagsValues.get(Constants.CLI_ARG_METHOD).toLowerCase());
            } else {
                    String pId = flagsValues.getOrDefault(Constants.CLI_ARG_METHOD_ARG_PRIMARY_ID, "");
                    String sId = flagsValues.getOrDefault(Constants.CLI_ARG_METHOD_ARG_SECONDARY_ID, "");
                    String domain = flagsValues.getOrDefault(Constants.CLI_ARG_METHOD_ARG_NAME, "");
                    String ban = flagsValues.getOrDefault(Constants.CLI_ARG_METHOD_ARG_BAN, "");
                    String ss = flagsValues.getOrDefault(Constants.CLI_ARG_METHOD_ARG_SECOND_STRING, "");
                    String ts = flagsValues.getOrDefault(Constants.CLI_ARG_METHOD_ARG_THIRD_STRING, "");
                    String tws = flagsValues.getOrDefault(Constants.CLI_ARG_METHOD_ARG_TEXT_WITH_SPACES, "");
                    String methodArg = flagsValues.get(Constants.CLI_ARG_METHOD).toLowerCase();
                    String key;

                    switch(flagsValues.get(Constants.CLI_ARG_METHOD).toLowerCase()){
                        // Remove platform
                        case Constants.CLI_ARG_PLATFROM_METHOD_REMOVE:
                            if(domain.isEmpty()) {
                                log.error("Wrong params! Try: " + 
                                        Constants.CLI_ARG_METHOD + " " + methodArg + " " +
                                        Constants.CLI_ARG_METHOD_ARG_NAME + " <domain>");
                            } else {
                                PlatformManager.deletePlatform(session.getAuthorisedUser().getId(), domain);
                            }
                            break;

                        // Remove message
                        case Constants.CLI_ARG_PLATFROM_METHOD_REMOVE_MESSAGE:
                             if(pId.isEmpty()) {
                                log.error("Wrong params! Try: " + 
                                        Constants.CLI_ARG_METHOD + " " + methodArg + " " +
                                        Constants.CLI_ARG_METHOD_ARG_PRIMARY_ID + " <id>");
                            } else {
                                PlatformManager.removeMessage(session.getAuthorisedUser().getId(), Long.parseLong(pId));
                            }
                            break;

                        // Get platforms list
                        case Constants.CLI_ARG_PLATFROM_METHOD_GET_LIST:
                            PlatformManager.getPlatformsList(session.getAuthorisedUser().getId()).forEach(log::info);
                            break;

                        // Reset platform's key
                        case Constants.CLI_ARG_PLATFROM_METHOD_RESET_KEY:
                            if(domain.isEmpty()) {
                                log.error("Wrong params! Try: " + 
                                        Constants.CLI_ARG_METHOD + " " + methodArg + " " +
                                        Constants.CLI_ARG_METHOD_ARG_NAME + " <domain>");
                            } else {
                                key = PlatformManager.resetKey(session.getAuthorisedUser().getId(), domain);
                                if(!key.isEmpty()){
                                    log.info("Platform:" + domain + "\tNew key: " + key);    
                                }
                            }
                            break;

                        // Get platform's key
                        case Constants.CLI_ARG_PLATFROM_METHOD_GET_KEY:
                            if(domain.isEmpty()) {
                                log.error("Wrong params! Try: " + 
                                        Constants.CLI_ARG_METHOD + " " + methodArg + " " +
                                        Constants.CLI_ARG_METHOD_ARG_NAME + " <domain>");
                            } else {
                                key = PlatformManager.getKey(session.getAuthorisedUser().getId(), domain);
                                if(!key.isEmpty()){
                                    log.info("Platform:" + domain + "\tKey: " + key);    
                                }
                            }

                            break;
                        // Create platform
                        case Constants.CLI_ARG_PLATFROM_METHOD_CREATE:
                            if(domain.isEmpty()) {
                                log.error("Wrong params! Try: " + 
                                        Constants.CLI_ARG_METHOD + " " + methodArg + " " +
                                        Constants.CLI_ARG_METHOD_ARG_NAME + " <domain>");
                            } else {
                                PlatformManager.createPlatform(session.getAuthorisedUser().getId(), domain);  
                            }
                            break;
                            
                        // Append platform's user
                        case Constants.CLI_ARG_PLATFROM_METHOD_APPEND_USER:
                            if(domain.isEmpty() || ss.isEmpty() || ts.isEmpty()) {
                                log.error("Wrong params! Use: help " + 
                                        Constants.CLI_ARG_METHOD + " " + methodArg);
                            } else {
                                PlatformManager.appendUser(session.getAuthorisedUser().getId(), domain, ss, ts);
                            }
                            break;
                            
                        case Constants.CLI_ARG_PLATFROM_METHOD_APPEND_MESSAGE:
                            if(domain.isEmpty() || ss.isEmpty() || tws.isEmpty()) {
                                log.error("Wrong params! Use: help " + 
                                        Constants.CLI_ARG_METHOD + " " + methodArg);
                            } else {
                                PlatformManager.appendMessage(session.getAuthorisedUser().getId(), domain, ss, tws);
                            }
                            break;
                            
                    }
                }
            }
            flagsValues.clear();
            modeFlags.clear();
            flagsSemaphore.clear();
            waitingValues = false;
        }
    
    
    private static void showHelp() {
        log.info("=========================================================");
        log.info("Params for option " + Constants.CLI_ARG_METHOD + ":");
        Arrays.asList(Constants.CLI_ARG_PLATFORMS_METHODS).forEach(log::info);
        log.info("_________________________________________________________");
        log.info("You can change dataprovider by option " + Constants.CLI_ARG_DATA_PROVIDER + " <dataprovider driver>");
        log.info("Enter " + Constants.CLI_MODE_ARG_HELP + " " + Constants.CLI_ARG_METHOD + " <method> to get more info");
        log.info("=========================================================");
    }
    
    private static void showHelp(String method) {
        String output = "Usage: " + Constants.CLI_ARG_METHOD + " " + method + " ";

            switch(method){
                case Constants.CLI_ARG_PLATFROM_METHOD_CREATE:
                    output += String.join(" ", Constants.CLI_ARG_PLATFROM_METHOD_CREATE_REQUIRED_PARAMS);
                    break;
                case Constants.CLI_ARG_PLATFROM_METHOD_REMOVE:
                    output += String.join(" ", Constants.CLI_ARG_PLATFROM_METHOD_REMOVE_REQUIRED_PARAMS);
                    break;
                case Constants.CLI_ARG_PLATFROM_METHOD_REMOVE_MESSAGE:
                    output += String.join(" ", Constants.CLI_ARG_PLATFROM_METHOD_REMOVE_MESSAGE_REQUIRED_PARAMS);
                    break;
                case Constants.CLI_ARG_PLATFROM_METHOD_RESET_KEY:
                    output += String.join(" ", Constants.CLI_ARG_PLATFROM_METHOD_RESET_KEY_REQUIRED_PARAMS);
                    break;
                case Constants.CLI_ARG_PLATFROM_METHOD_GET_KEY:
                    output += String.join(" ", Constants.CLI_ARG_PLATFROM_METHOD_GET_KEY_REQUIRED_PARAMS);
                    break;
                case Constants.CLI_ARG_PLATFROM_METHOD_BAN:
                    output += String.join(" ", Constants.CLI_ARG_PLATFROM_METHOD_BAN_REQUIRED_PARAMS);
                    break;
                case Constants.CLI_ARG_PLATFROM_METHOD_GET_LIST:
                    output += String.join(" ", Constants.CLI_ARG_PLATFROM_METHOD_GET_LIST_REQUIRED_PARAMS);
                    break;
                case Constants.CLI_ARG_PLATFROM_METHOD_APPEND_USER:
                    output += String.join(" ", Constants.CLI_ARG_PLATFROM_METHOD_APPEND_USER_REQUIRED_PARAMS);
                    break;
                case Constants.CLI_ARG_PLATFROM_METHOD_APPEND_MESSAGE:
                    output += String.join(" ", Constants.CLI_ARG_PLATFROM_METHOD_APPEND_MESSAGE_REQUIRED_PARAMS);
                    break;
                default:
                    log.error("There no is method");
                    break;
        }
        log.info(output);
    }
}
