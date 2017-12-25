package ru.sfedu.tavern.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.dataproviders.IDataProvider;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Entity;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.PlatformUser;

/**
 *
 * @author entropy
 */
public class ValidateUtil {

    
    private static final Logger log = Logger.getLogger(ValidateUtil.class);
    
    /**
     *
     */
    public ValidateUtil() {

    }

    /**
     *
     * @param dp
     * @param optObject
     * @return
     */
    public static boolean isUnique(IDataProvider dp, Optional<Entity> optObject) {
        if(!optObject.isPresent()) return false;
        Entity obj = optObject.get();
        switch (obj.getClassType()) {
            case OURUSER:
                try {
                    return dp.select("id", String.valueOf((obj).getId()), ClassType.OURUSER, false).size() <= 1;
                } catch (Exception ex) {
                    return false;
                }
            case PLATFORM:
                try{
                    return  dp.select("id", String.valueOf(obj.getId()), ClassType.PLATFORM, false).size() <= 1;
                } catch(Exception ex) {
                    return false;
                }
            case PLATFORMUSER:
                try{
                    return dp.select("id", String.valueOf(obj.getId()), ClassType.PLATFORMUSER, false).size() <= 1;
                } catch(Exception ex) {
                    return false;
                }
            case MESSAGE:
                try {
                    return dp.select("id", String.valueOf(obj.getId()), ClassType.MESSAGE, false).size() <= 1;
                } catch(Exception ex) {
                    return false;
                }
        }
        return true;
    }
    
    /**
     *
     * @param dp
     * @param optObject
     * @return
     */
    public static boolean findDependencyUp(IDataProvider dp, Optional<Entity> optObject) {
        if(!optObject.isPresent()) return false;
        Entity obj = optObject.get();
        switch (obj.getClassType()) {
            case OURUSER:
                try {
                    return true;
                } catch (Exception ex) {
                    return false;
                }
            case PLATFORM:
                try{
                    return !dp.select("id", String.valueOf(((Platform)obj).getOwnerId()), ClassType.OURUSER, false).isEmpty();
                } catch(Exception ex) {
                    return false;
                }
            case PLATFORMUSER:
                try{
                    return !dp.select("id", String.valueOf(((PlatformUser)obj).getPlatformId()), ClassType.PLATFORM, false).isEmpty();
                } catch(Exception ex) {
                    return false;
                }
            case MESSAGE:
                try {
                    return !dp.select("id", String.valueOf(((Message)obj).getSenderId()), ClassType.PLATFORMUSER, false).isEmpty();
                } catch(Exception ex) {
                    return false;
                }
        }
        return true;
    }
    
    /**
     *
     * @param dp
     * @param optObject
     * @return
     */
    public static List<Entity> findDependencyDown(IDataProvider dp, Optional<Entity> optObject) {
        List<Entity> findedEls = new ArrayList();
        if(!optObject.isPresent()) return findedEls;
        Entity obj = optObject.get();
        switch (obj.getClassType()) {
            case OURUSER:
                try {
                    findedEls.addAll(dp.select("ownerId", String.valueOf(obj.getId()), ClassType.PLATFORM, false));
                } catch(Exception ex) {
                    log.warn(ex);
                }
                break;
            case PLATFORM:
                try{
                    findedEls.addAll(dp.select("platformId", String.valueOf(obj.getId()), ClassType.PLATFORMUSER, false));
                } catch(Exception ex) {
                    log.warn(ex);
                }
                break;
            case PLATFORMUSER:
                try{
                    findedEls.addAll(dp.select("senderId", String.valueOf(obj.getId()), ClassType.MESSAGE, false));    
                } catch(Exception ex) {
                    log.warn(ex);
                }
               
                break;
        }
        return findedEls;
    }
    
    
    
    /**
     *
     * @param dp
     * @param type
     * @return 
     */
    public static List<Entity> findWrongDependecies(IDataProvider dp, ClassType type) {
        List<Entity> globListToDel = new ArrayList();
        List<Entity> list = new ArrayList();
        
        try {
            list.addAll(dp.select(type));
            list.stream().forEach(e -> {
                boolean isUnique = isUnique(dp, Optional.ofNullable(e));
                if(!globListToDel.contains(e) && (!findDependencyUp(dp, Optional.ofNullable(e)) || !isUnique)) {
                    List<Entity> listToDel = new ArrayList();
                    try {
                        
                        listToDel.addAll(dp.select("id", String.valueOf(e.getId()), type));
                        if(!isUnique){
                            Scanner scanner = new Scanner(System.in);
                            listToDel.forEach(log::info);
                            log.info("What record should we leave?");
                            int chose = scanner.nextInt();
                            listToDel.remove(listToDel.get(chose - 1));
                        }
                        globListToDel.addAll(listToDel);
                    } catch (Exception ex) {
                        
                    }
                }
            });
        } catch (Exception ex) {
            log.warn(ex);
        }
        
        return globListToDel; 
    }
}
