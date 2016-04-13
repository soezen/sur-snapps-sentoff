package sur.snapps.sentoff.rest.util;

import org.springframework.stereotype.Component;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author sur
 * @since 13/04/2016
 */
@Component
public class ErrorMessages {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("error_messages");

    public String translate(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return "???" + key + "???";
        }
    }

    public String translate(String key, String defaultMessage) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return defaultMessage;
        }
    }
}
