package ru.sfedu.tavern.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class XmlFilter {

    /**
     *
     */
    public XmlFilter() {

    }
    
    /**
     *
     * @param col
     * @param val
     * @param list
     * @param type
     * @return
     */
    public static List<Entity> filter(String col, String val, List<Entity> list, ClassType type){
        List<Entity> resultList = new ArrayList();
        if(col == null || val == null) {
            return list;
        }
        switch(type) {
            case OURUSER:
                switch(col){
                    case "id":
                        resultList.addAll(list.stream()
                                .filter(e -> e.getId() == Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                    case "login":
                        resultList.addAll(list.stream()
                                .filter(e -> ((OurUser)e).getLogin().equals(val))
                                .collect(Collectors.toList()));
                        break;
                    case "passwordHash":
                        resultList.addAll(list.stream()
                                .filter(e -> ((OurUser)e).getPasswordHash().equals(val))
                                .collect(Collectors.toList()));
                        break;
                    case "email":
                        resultList.addAll(list.stream()
                                .filter(e -> ((OurUser)e).getEmail().equals(val))
                                .collect(Collectors.toList()));
                        break;
                    case "lastAct":
                        resultList.addAll(list.stream()
                                .filter(e -> ((OurUser)e).getLastAct() == Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                }
                break;
                
            case PLATFORM: 
                switch(col){
                    case "id":
                        resultList.addAll(list.stream()
                                .filter(e -> e.getId() == Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                    case "domain":
                        resultList.addAll(list.stream()
                                .filter(e -> ((Platform)e).getDomain().equals(val))
                                .collect(Collectors.toList()));
                        break;
                    case "key":
                        resultList.addAll(list.stream()
                                .filter(e -> ((Platform)e).getKey().equals(val))
                                .collect(Collectors.toList()));
                        break;
                    case "ownerId":
                        resultList.addAll(list.stream()
                                .filter(e -> ((Platform)e).getOwnerId() == Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                }
                break;
                
            case PLATFORMUSER:
                switch(col){
                    case "id":
                        resultList.addAll(list.stream()
                                .filter(e -> e.getId() == Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                    case "login":
                        resultList.addAll(list.stream()
                                .filter(e -> ((PlatformUser)e).getLogin().equals(val))
                                .collect(Collectors.toList()));
                        break;
                    case "avatarLink":
                        resultList.addAll(list.stream()
                                .filter(e -> ((PlatformUser)e).getAvatarLink().equals(val))
                                .collect(Collectors.toList()));
                        break;
                    case "lastAct":
                        resultList.addAll(list.stream()
                                .filter(e -> ((PlatformUser)e).getLastAct() == Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                    case "banned":
                        resultList.addAll(list.stream()
                                .filter(e -> ((PlatformUser)e).getBanned()== Boolean.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                    case "platformId":
                        resultList.addAll(list.stream()
                                .filter(e -> ((PlatformUser)e).getPlatformId() == Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                }
                break;
                
            case MESSAGE:
                switch(col){
                    case "id":
                        resultList.addAll(list.stream()
                                .filter(e -> e.getId() == Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                    case "senderId":
                        resultList.addAll(list.stream()
                                .filter(e -> ((Message)e).getSenderId() == Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                    case "text":
                        resultList.addAll(list.stream()
                                .filter(e -> ((Message)e).getText().equals(val))
                                .collect(Collectors.toList()));
                        break;
                    case "sendTime":
                        resultList.addAll(list.stream()
                                .filter(e -> ((Message)e).getSendTime() == Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                    case "platformId":
                        resultList.addAll(list.stream()
                                .filter(e -> ((Message)e).getPlatformId()== Long.valueOf(val))
                                .collect(Collectors.toList()));
                        break;
                }
                break;
        }
        
        return resultList;
    }

}
