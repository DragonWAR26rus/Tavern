package ru.sfedu.tavern.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.dataproviders.IDataProvider;
import ru.sfedu.tavern.model.StatusType;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.OurUser;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.PlatformUser;

/**
 *
 * @author entropy
 */


/**
 * Class PlatformManager
 */
public class PlatformManager {

  private static final Logger log = Logger.getLogger(PlatformManager.class);
  
  private PlatformManager () {};
    
  /**
   * @param        domain name of domain
   * @param        ownerId user's id
   * 
   * @return       Platform
   */
  public static Platform createPlatform(long ownerId, String domain) {
      IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
      long id = dp.getNextId(ClassType.PLATFORM);
      String key = keyGen();
      Platform newPl = new Platform(id, domain, key, ownerId);
      try {
          if(dp.insert(Optional.ofNullable(newPl)).getStatus().equals(StatusType.OK)){
              log.info("Platform success created");
          } else {
              log.error("Can't create platform");
          }
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
      IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
      try {
          dp.delete(Optional.ofNullable(platform));
      } catch (Exception ex) {
          log.error(ex);
      }
  }
  
   /**
   * @param        ownerId 
   * @param        domain
   */
    public static void deletePlatform(long ownerId, String domain) {
        IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
        try {
            Optional<Entity> optPl = dp.select("domain", domain, ClassType.PLATFORM).stream()
                    .filter(e -> (((Platform)e).getOwnerId() == ownerId)).findAny();
            if(optPl.isPresent()) {
                if(dp.delete(optPl).getStatus().equals(StatusType.OK)) {
                    log.info("Success remvoe");
                } else {
                    log.error("Remove error");
                }
            } else {
                log.warn("There no is platform with that domain");
            }
        } catch (Exception ex) {
            log.error(ex);
        }
  }

  /**
   * @param        platform
   * @param        userId
   * @param        ban
   */
  public static void banUser(Platform platform, long userId, boolean ban) {
      IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
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
   * @param        ownerId 
   * @param        msgId
   */
  public static void removeMessage(long ownerId, long msgId) {
        IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
        try {
            
            Optional<Entity> optMsg = dp.getObjectByID(msgId, ClassType.MESSAGE);
            if(optMsg.isPresent()) {
                Message msg = (Message)optMsg.get();
                OurUser msgOwner = ((OurUser)dp.getObjectByID(((Platform)dp.getObjectByID(msg.getPlatformId(), ClassType.PLATFORM).get()).getOwnerId(), ClassType.OURUSER).get());
                if(msgOwner.getId() != ownerId) {
                    log.error("You can't delete that message");
                } else {
                    dp.delete(optMsg);
                    log.info("Message deleted");
                }
            }
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
  
    /**
     *
     * @param ownerId
     * @return Owner's list of platforms
     */
    public static List<Entity> getPlatformsList(long ownerId) {
      List<Entity> list = new ArrayList();
      IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
      try {
          list.addAll(dp.select("ownerId", String.valueOf(ownerId), ClassType.PLATFORM));
      } catch (Exception ex) {
          log.warn(ex);
      }
      return list;
  }
  
   /**
    * @return       Returns new key
    * @param        ownerId
    * @param        domain 
    */
    public static String resetKey(long ownerId, String domain) {
        IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
        String key = keyGen();

        try {
            Platform platform = (Platform)(dp.select("ownerId", String.valueOf(ownerId), ClassType.PLATFORM).stream()
                    .filter(e -> (((Platform) e).getDomain().equals(domain)))
                    .findAny().get()
            );
            platform.setKey(key);
            try {
                dp.update(Optional.ofNullable(platform));
            } catch (Exception ex) {
                log.error(ex);
            }
        } catch (Exception ex) {
            log.error("There no is platform with this domain name");
        }
        return key;
    }
  
    /**
    * @return       Returns current key
    * @param        ownerId
    * @param        domain 
    */
    public static String getKey(long ownerId, String domain) {
        String res = "";
        IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
        try {
            Platform platform = (Platform)(dp.select("ownerId", String.valueOf(ownerId), ClassType.PLATFORM).stream()
                    .filter(e -> (((Platform) e).getDomain().equals(domain)))
                    .findAny().get()
            );
          
        res = platform.getKey();
//          res = ((dp.select("ownerId", String.valueOf(ownerId), ClassType.PLATFORM)).stream().filter(e -> ((Platform)e.)));
        } catch (Exception ex) {
            log.warn("There no is platform with this domain: " + domain);
        }
      return res;
    }
    
   /**
    * @param        ownerId
    * @param        domain 
    * @param        login
    * @param        avaLink
    */
    public static void appendUser(long ownerId, String domain, String login, String avaLink){
        IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
        long id = dp.getNextId(ClassType.PLATFORMUSER);
        List<Entity> plList = new ArrayList();
        try {
            plList.addAll(dp.select("ownerId", String.valueOf(ownerId), ClassType.PLATFORM));
        } catch (Exception ex) {
            
        }
        
        if(!plList.isEmpty()) {
            
            Optional<Entity> targetPlatform = plList.stream().filter(e -> ((Platform)e).getDomain().equals(domain)).findAny();
            if(targetPlatform.isPresent()) {
                try {
                    PlatformUser newUser = new PlatformUser(id, login, avaLink, System.currentTimeMillis(), false, targetPlatform.get().getId());
                    if(dp.insert(Optional.ofNullable(newUser)).getStatus() == StatusType.OK) {
                        log.info("User appended");
                    } else {
                        log.error("Can't append user");
                    };
                } catch (Exception ex) {
                    log.warn(ex);
                }

                
            } else {
                log.error("There no is that platform");
            }
        } else {
            log.error("There no is that platform");
        }
    }
    
   /**
    * @param        ownerId
    * @param        domain 
    * @param        senderName
    * @param        messageText
    */
    public static void appendMessage(long ownerId, String domain, String senderName, String messageText) {
        IDataProvider dp = DataProviderSessionUtil.getInstance().getDataProvider();
        long id = dp.getNextId(ClassType.MESSAGE);
        List<Entity> tmpList = new ArrayList();
        try {
            tmpList.addAll(dp.select("ownerId", String.valueOf(ownerId), ClassType.PLATFORM));
        } catch (Exception ex) {
            
        }
        
        if(!tmpList.isEmpty()) {
            
            Optional<Entity> targetPlatform = tmpList.stream().filter(e -> ((Platform)e).getDomain().equals(domain)).findAny();
            if(targetPlatform.isPresent()) {
                try {
                    tmpList.clear();
                    tmpList.addAll(dp.select("platformId", String.valueOf(ownerId), ClassType.PLATFORMUSER));
                    if(!tmpList.isEmpty()) {
                        Optional<Entity> targetPlatformUser = tmpList.stream().filter(e -> ((PlatformUser)e).getLogin().equals(senderName)).findAny();;
                        if(targetPlatformUser.isPresent() && !((PlatformUser)targetPlatformUser.get()).getBanned()) {
                            Message newMessage = new Message(
                                    id, 
                                    targetPlatformUser.get().getId(), 
                                    System.currentTimeMillis(), 
                                    messageText, 
                                    targetPlatform.get().getId()
                            );
                            if(dp.insert(Optional.ofNullable(newMessage)).getStatus() == StatusType.OK) {
                                log.info("Message appended");
                            } else {
                                log.error("Can't append message");
                            };
                        } else {
                            log.error("There no is that user or he banned");
                        }     
                    } else {
                        log.error("There no is that user");
                    }
                    
                } catch (Exception ex) {
                    log.warn(ex);
                }
            } else {
                log.error("There no is that platform");
            }
        } else {
            log.error("There no is that platform");
        }
    }

}
