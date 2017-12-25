package ru.sfedu.tavern.utils;

import java.util.Optional;
import java.util.Random;
import org.apache.log4j.Logger;
import ru.sfedu.tavern.dataproviders.CsvAPI;
import ru.sfedu.tavern.dataproviders.IDataProvider;
import ru.sfedu.tavern.model.entities.ClassType;
import ru.sfedu.tavern.model.entities.Message;
import ru.sfedu.tavern.model.entities.OurUser;
import ru.sfedu.tavern.model.entities.Platform;
import ru.sfedu.tavern.model.entities.PlatformUser;

/**
 *
 * @author entropy
 */
public class EntityGenerator {

    private static final Logger log = Logger.getLogger(EntityGenerator.class);
    
    /**
     *
     */
    public EntityGenerator() {

    }
    
    /**
     *
     * @param dp
     * @return
     */
    public static OurUser generateOurUser(IDataProvider dp) {
        long id = dp.getNextId(ClassType.OURUSER);
        OurUser user = new OurUser(id, 
                "TESTUSER_" + id, 
                AuthUtils.getHash("TESTHASH_" + id), 
                System.currentTimeMillis(), 
                "TESTMAIL_" + id + "@TESTMAIL.RU"
        );
        log.debug("Generated entity: " + user);
        return user;
    }
    
    /**
     *
     * @param dp
     * @param owner
     * @return
     */
    public static Platform generatePlatform(IDataProvider dp, OurUser owner) {
        long id = dp.getNextId(ClassType.PLATFORM);
        Platform pl = new Platform(id, "TESTDOMAIN_" + id, "TESTKEY_" + id, owner.getId());
        log.debug("Generated entity: " + pl);
        return pl;
    }
    
    /**
     *
     * @param dp
     * @param pl
     * @return
     */
    public static PlatformUser generatePlatformUser(IDataProvider dp, Platform pl) {
        long id = dp.getNextId(ClassType.PLATFORMUSER);
        PlatformUser plUser = new PlatformUser(id, 
                "TESTUSER_" + id, 
                "TESTAVATARLINK_" + id, 
                System.currentTimeMillis(), 
                false,
                pl.getId()
        );
        log.debug("Generated entity: " + plUser);
        return plUser;
    }
    
    /**
     *
     * @param dp
     * @param plUser
     * @param pl
     * @return
     */
    public static Message generateMessage(IDataProvider dp, PlatformUser plUser, Platform pl) {
        long id = dp.getNextId(ClassType.MESSAGE);
        String alphabet[] = new String[]{"Hi!", "How are u?", "I'm fine.", "Thanks!", "Good bye!", "Lorem ipsum", "...?"};
        Random rnd = new Random();
        int sentenseCount = rnd.nextInt(4) + 1;
        String text = "";
        for(int i = 0; i < sentenseCount; i++) {
            text += alphabet[rnd.nextInt(alphabet.length)] + " ";
        }
        
        Message message = new Message(id, 
                plUser.getId(), 
                System.currentTimeMillis(), 
                text, 
                pl.getId()
        );
        log.debug("Generated entity: " + message);
        return message;
    }

}
