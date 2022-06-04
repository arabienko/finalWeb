package by.arabienko.onlineSchool.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;

public class NotNullFunction extends TagSupport {
    private static final Logger LOGGER =
            LogManager.getLogger(NotNullFunction.class);
    public static String notNull(Object object){
        String res;
        if(object == null || object.toString().isEmpty()){
            res = "attribute is ampty or null...";
        }else {
            res = object.toString();
        }
        return res;
    }
}
