/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mud.diku.objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author goldbishop
 */
public class ServerProperties extends ALoggingObject {

    // _default is only used for internal acquisition of default settings
    //    and as a basis for modification for the Run-Time Custom Settings
    private final static Properties _default = LoadDefaultProperties();
    /**
     * *
     * URL:
     * https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
     * URL:
     * https://stackoverflow.com/questions/22748484/checking-if-properties-file-exists-and-has-required-properties
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static Properties LoadDefaultProperties() {
        // Load Default Settings
        var props = new Properties();
        try {
            props.load(ServerProperties.class.getResourceAsStream("/resources/default.properties"));

            for (var key : props.stringPropertyNames()) {
                var val = props.getProperty(key);
                log.LogInfo("Key: " + key + "; Value: " + val);
            }
        } catch (Exception exc) {

        }

        return props;
    }

}
