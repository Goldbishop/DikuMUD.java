/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mud.diku.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.*;
import static mud.diku.objects.ALoggingObject.log;

/**
 * A factory object used to consolidate logging mechanisms
 *
 * @author goldbishop
 */
public class LoggerFactory {

    protected final static Logger _log = SetupLog();

    /**
     * Standardized approach to setting up Logging framework
     *
     * @return Logger
     */
    protected static Logger SetupLog() {
        var log = Logger.getLogger(LoggerFactory.class.getName());

        try {
            InputStream is = LoggerFactory.class.getResourceAsStream("/resources/logging.properties");
            LogManager.getLogManager().readConfiguration(is);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(LoggerFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return log;
    }

    /**
     * *
     * Central Logging method
     *
     * @param lvl Level type
     * @param msg String type; Message to capture
     */
    private static void Log(Level lvl, String msg) {
        _log.log(lvl, msg);
    }

    /**
     * *
     * Log Information Level messages
     *
     * @param msg String value; Message to capture
     */
    public static void LogInfo(String msg) {
        Log(Level.INFO, msg);
    }

    /**
     * *
     * Log Information Level messages
     *
     * @param msg String value; Message to capture
     */
    public static void LogWarning(String msg) {
        Log(Level.WARNING, msg);
    }

    /**
     * *
     * Log Information Level messages
     *
     * @param msg String value; Message to capture
     */
    public static void LogSevere(String msg) {
        Log(Level.SEVERE, msg);
    }

    public static void LogSevere(Exception exc) {
        log.LogSevere(exc.getMessage());
        log.LogSevere(Arrays.toString(exc.getStackTrace()));
    }

    /**
     * *
     * Log Information Level messages
     *
     * @param msg String value; Message to capture
     */
    public static void LogConfig(String msg) {
        Log(Level.CONFIG, msg);
    }
}
