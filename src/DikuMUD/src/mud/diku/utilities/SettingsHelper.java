/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mud.diku.utilities;

import java.util.*;
import mud.diku.objects.*;

/**
 *
 * @author goldbishop
 */
public class SettingsHelper extends APropertiesObject {

    public static String Version() {
        return props.Properties("app.version");
    }

    public static String MapsDirectory() {
        return props.Properties("app.maps");
    }

    public static int Port() {
        if (runtime.containsKey("port")) {
            log.LogInfo("(port) value: " + runtime.get("port"));
            return Integer.parseInt(runtime.get("port"));
        } else {
            log.LogInfo("(app.port) value: " + props.Properties("app.port"));
            return Integer.parseInt(props.Properties("app.port"));
        }
    }
    private static HashMap<String, String> runtime = new HashMap<String, String>();

    public static boolean GetLawful() {
        // In the event method is called before Lawful is set
        if (!runtime.containsKey("lawful")) {
            SetLawful(false);
        }

        return Boolean.getBoolean(runtime.get("lawful"));
    }

    public static void SetLawful(boolean val) {
        log.LogInfo("*** LAWFUL Flag being set! ***");
        runtime.put("lawful", String.valueOf(val));
        log.LogInfo("LAWFUL Flag set: " + String.valueOf(val));
    }

    public static String GetDataDirectory() {
        if (!runtime.containsKey("data")) {
            SetDataDirectory("./data");
        }

        return runtime.get("data");
    }

    public static void SetDataDirectory(String val) {
        log.LogInfo("Data Directory being set!");
        runtime.put("data", val);
        log.LogInfo("Data Directory set: " + val);
    }

    public static boolean GetSuppressRoutines() {
        if (!runtime.containsKey("suppress")) {
            SetSuppressRoutines(false);
        }

        return Boolean.getBoolean(runtime.get("suppress"));
    }

    public static void SetSuppressRoutines(boolean bool) {
        var val = String.valueOf(bool);
        log.LogInfo("*** Suppress Routes being set! ***");
        runtime.put("suppress", val);
        log.LogInfo("Suppress Routines set: " + val);
    }

    public static void SetKeyValue(String key, String val) {
        log.LogInfo("*** (" + key + ") Argument being set! ***");
        runtime.put(key, val);
        log.LogInfo("(" + key + ") Argument set: " + val);
    }

}
