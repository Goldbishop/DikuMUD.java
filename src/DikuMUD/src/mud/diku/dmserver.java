/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mud.diku;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import mud.diku.objects.APropertiesObject;
import mud.diku.utilities.SettingsHelper;

/**
 *
 * @author goldbishop
 */
@SuppressWarnings("static-access")
public class dmserver extends APropertiesObject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {// Pre-Run Setup
            log.LogInfo("Entering OnStartup method....");
            OnStartup(args);

    }

    /**
     * **
     * Handles the Startup phase of the server. Any actions can result in a
     * failed server start
     *
     * @param args
     */
    private static void OnStartup(String[] args) throws IOException {
        // Check args length
        if (args.length < 1) {
            log.LogInfo(String.format("args is empty!"));
        } else {
            log.LogInfo("Entering ParseArguments method....");
            ParseArguments(args);
        }
    }

    private static void ParseArguments(String[] args) {
        // Log args passed
        log.LogInfo(Arrays.toString(args));

        // Parse Arguments into Settings Application-Global
        //    DO NOT PERSIST AFTER REBOOT/SHUTDOWN
        for (var arg : args) {
            log.LogConfig(String.format("%s", arg));

            switch (arg) {
                case "DEBUG":
                    // perform some action to put Server in DEBUG MODE

                    break;
                case "LAWFUL":
                    // Set Lawful Mode
                    SettingsHelper.SetLawful(true);
                    break;
                case "SUPPRESS":
                    // Suppress assignment of special routines
                    SettingsHelper.SetSuppressRoutines(true);
                    break;
                default:
                    //Assume argument is a Key-Value pair
                    var idx = arg.indexOf(":");
                    var key = arg.substring(0, idx);

                    switch (key.toLowerCase()) {
                        case "d":
                            // Data directory defined
                            key = "data";
                            break;
                        case "p":
                            key = "port";
                            break;
                        default:
                            // UNKNOWN key
                            // Log Warning & DO NOTHING
                            log.LogConfig("Unknown Argument Passed: " + arg);
                            break;
                    }
                    var val = (String) arg.substring(idx + 1);
                    SettingsHelper.SetKeyValue(key, val);
                    break;
            }
        }
    }
}
