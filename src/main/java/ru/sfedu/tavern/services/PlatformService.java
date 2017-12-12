package ru.sfedu.tavern.services;

import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.Platform;

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

  
  //
  // Constructors
  //
  public PlatformService () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  //
  // Other methods
  //

  /**
   * @return       Platform
   * @param        ownerId
   */
  public static Platform createPlatform(long ownerId)
  {
      return null;
  }


  /**
   * @param        platform
   */
  public static void deletePlatform(Platform platform)
  {
  }


  /**
   * @return       String
   * @param        platform
   */
  public static String resetKey(Platform platform)
  {
      return null;
  }


  /**
   * @param        platform
   * @param        userId
   * @param        ban
   */
  public static void banUser(Platform platform, long userId, boolean ban)
  {
  }


  /**
   * @param        platform
   * @param        message
   */
  public static void removeMessage(Platform platform, Message message)
  {
  }


  /**
   * @param        platform
   * @param        message
   */
  public static void addMessage(Platform platform, Message message)
  {
  }


}
