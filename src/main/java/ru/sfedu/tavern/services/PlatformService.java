package ru.sfedu.tavern.services;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.dataproviders.CsvAPI;
import ru.sfedu.tavern.dataproviders.IDataProvider;
import ru.sfedu.tavern.dataproviders.JdbcAPI;
import ru.sfedu.tavern.dataproviders.XmlAPI;
import ru.sfedu.tavern.model.Constants;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.PlatformUser;
import ru.sfedu.tavern.utils.ConfigurationUtil;

/**
 *
 * @author entropy
 */


/**
 * Class PlatformService
 */
public class PlatformService {

  //
  // Fields
  //

  private static IDataProvider dp = null;
  private static final Logger log = Logger.getLogger(PlatformService.class);
  
  //
  // Constructors
  //
  private PlatformService () {};
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  //
  // Other methods
  //
  
  private static IDataProvider checkDataProvider() {
    try {
        switch(ConfigurationUtil.getConfigurationEntry(Constants.DP_DRIVER)) {
            case "xml":
                dp = new XmlAPI();
                log.info("Data provider: XML");
                break;
            case "csv":
                dp = new CsvAPI();
                log.info("Data provider: CSV");
                break;
            case "jdbc":
                dp = new JdbcAPI();
                log.info("Data provider: JDBC");
                break;
        }
        
    } catch (IOException ex) {
        log.error(ex);
    }
    return dp;
  }
  
  /**
   * @param        domain name of domain
   * @param        ownerId user's id
   * 
   * @return       Platform
   */
  public static Platform createPlatform(String domain, long ownerId) {
      checkDataProvider();
      long id = dp.getNextId(ClassType.PLATFORM);
      String key = keyGen();
      Platform newPl = new Platform(id, domain, key, ownerId);
      try {
          dp.insert(Optional.ofNullable(newPl));
      } catch (Exception ex) {
          log.error(ex);
          return null;
      }
      return newPl;
  }


  /**
   * @param        platform
   */
  public static void deletePlatform(Platform platform) {
      checkDataProvider();
      try {
          dp.delete(Optional.ofNullable(platform));
      } catch (Exception ex) {
          log.error(ex);
      }
  }
   /**
   * @param        id platform's id
   */
    public static void deletePlatform(long id) {
      checkDataProvider();
      try {
          dp.delete(dp.getObjectByID(id, ClassType.PLATFORM));
      } catch (Exception ex) {
          log.error(ex);
      }
  }


  /**
   * @return       String
   * @param        platform
   */
  public static String resetKey(Platform platform) {
      checkDataProvider();
      String key = keyGen();
      platform.setKey(key);
      try {
          dp.update(Optional.ofNullable(platform));
      } catch (Exception ex) {
          log.error(ex);
      }
      return key;
  }


  /**
   * @param        platform
   * @param        userId
   * @param        ban
   */
  public static void banUser(Platform platform, long userId, boolean ban) {
      checkDataProvider();
      Optional<Entity> oUser = dp.getObjectByID(userId, ClassType.PLATFORMUSER);
      if(oUser.isPresent()) {
          PlatformUser user = (PlatformUser) oUser.get();
          if(user.getPlatformId() == platform.getId()) {
                user.setBanned(ban);
                try {
                    dp.update(Optional.ofNullable(user));
                } catch (Exception ex) {
                    log.error(ex);
                }
          }
      }
      
  }


  /**
   * @param        msgId
   */
  public static void removeMessage(long msgId) {
        checkDataProvider();
        try {
            dp.delete(dp.getObjectByID(msgId, ClassType.MESSAGE));
        } catch (Exception ex) {
            log.error(ex);
        }
  }


  /**
   * @param        platform
   * @param        message
   */
  public static void addMessage(Platform platform, Message message){
      checkDataProvider();
      message.setPlatformId(platform.getId());
      try {
        dp.insert(Optional.ofNullable(message));
      } catch (Exception ex) {
        log.error(ex);
      }
      
  }

  private static String keyGen() {
      final Random random = new Random();
      final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
      final int keyLenght = 32;
      
      char[] key = new char[keyLenght];
      
      for(int i = 0; i < keyLenght; i++) {
          key[i] = alphabet.charAt(random.nextInt(alphabet.length()));
      }
      
      return String.valueOf(key);
  }

}
