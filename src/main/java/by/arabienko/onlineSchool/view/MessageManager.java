package by.arabienko.onlineSchool.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

/**
 * System messages
 */
public class MessageManager {
    private static final Logger LOGGER =
            LogManager.getLogger(MessageManager.class);
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("locale.pagecontent");

    private MessageManager() {
    }
    public static String getMessages (String key){
        return resourceBundle.getString(key);
    }
}
