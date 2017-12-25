package ru.sfedu.tavern.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.dataproviders.IDataProvider;
import ru.sfedu.tavern.dataproviders.JdbcAPI;
import ru.sfedu.tavern.model.AuthSession;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.OurUser;

/**
 *
 * @author entropy
 */
public class AuthUtils {

    
    private static Logger log = Logger.getLogger(AuthUtils.class);
    
    private AuthUtils() {

    }
    
    /**
     *
     * @param word
     * @return
     */
    public static String getHash(String word) {
        String hash = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(word.getBytes());
            byte[] mdbytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(byte bt : mdbytes){
                String s = Integer.toHexString(0xff & bt);
                s = (s.length() == 1) ? "0" + s : s;
                sb.append(s);
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException ex){}
        
        return hash;
    }
    
    /**
     *
     * @param login
     * @param password
     * @param email
     * @param isHash
     * @return
     */
    public static OurUser registration(String login, String password, String email, boolean isHash) {
        IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
        String passwordHash = isHash?password:AuthUtils.getHash(password);
        try {
            String sLogin = DataProviderSessionUtil.getDataProviderName().equals("jdbc") ? "'" + login + "'" : login;
            List<Entity> ul = dp.select("login", sLogin, ClassType.OURUSER);
            if(!ul.isEmpty()) {
                if(ul.stream().anyMatch(e -> ((OurUser)e).getPasswordHash().equals(passwordHash))) {
                    OurUser user = (OurUser)ul.get(0);
                    AuthSession.getInstance(user);
                    return user;
                } else {
                    log.error("Wrong password");
                    System.exit(4);
                }
            }
        } catch (Exception ex) {
            
        }
        
        long id = dp.getNextId(ClassType.OURUSER);
        OurUser user = new OurUser(id, login, passwordHash, System.currentTimeMillis(), email);
        try {  
            dp.insert(Optional.ofNullable(user));
        } catch (Exception ex) {
            log.error(ex);
            System.exit(3);
        }
        AuthSession.getInstance(user);
        return user;
    }

}
