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

    private static ServerSocket listener;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {// Pre-Run Setup
            log.LogInfo("Entering OnStartup method....");
            OnStartup(args);

            log.LogInfo(">>> Opening Socket to accept connection....");

            var inet = listener.getInetAddress();
            var host = inet.getHostName();
            var addr = inet.getHostAddress();
            var chost = inet.getCanonicalHostName();
            log.LogConfig("Host: " + host + "; INet_Addr: " + addr + "; Canonical HostName: " + chost);

            /**
             * ** Run Loop ***
             */
            log.LogInfo("Entering RunServer Logic....");
            RunServer();

            // Post-Run Cleanup
            log.LogInfo("Entering OnShutdown method....");
            OnShutdown();
        } catch (Exception exc) {
            // End all Be-All CATCH'er
            log.LogSevere(exc);
        }
    }

    /**
     * **
     * Handles the Startup phase of the server. Any actions can result in a
     * failed server start
     *
     * @param args
     */
    private static void OnStartup(String[] args) throws IOException {
        // Load Resources
        log.LogInfo("Loading Configuration....");
        LoadConfiguration();

        // Check args length
        if (args.length < 1) {
            log.LogInfo(String.format("args is empty!"));
        } else {
            log.LogInfo("Entering ParseArguments method....");
            ParseArguments(args);
        }

        // Initialize Server
        log.LogInfo("Initializing ServerSocket....");
        listener = InitSocket();

        // Boot Database File(s); Mob, Object, Zone, etc.
        log.LogInfo("Entering BootDatabase method....");
        BootDatabase();
    }

    private static void LoadConfiguration() {
        // Load Default properties (default.properties)

        /**
         * *** Load Custom Properties ****
         */
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

    private static void OnShutdown() throws IOException {
        // Save Custom Properties
        // Save Other File(s)
        // Log Shutdown Information
        log.LogInfo("Server SHUTDOWN!!!");
        listener.close();
        System.exit(0);
    }

    private static ServerSocket InitSocket() throws IOException {
        // Initialize New Socket
        return new ServerSocket(SettingsHelper.Port(), 0, InetAddress.getLoopbackAddress());
    }

    private static void BootDatabase() {
        log.LogInfo("### Booting Database ###");
        var datadir = CheckDirectory();

        // Read Files in Data Directory
        log.LogInfo("*** Capturing Data Files ***");

        var fldr = new File(datadir);
        String[] arrFiles = fldr.list();

        if (arrFiles == null) {
            log.LogWarning("Files Array has not been filled");
        } else {
            for (var file : arrFiles) {
                log.LogInfo("File Found: " + file);
            }
        }
    }

    private static void RunServer() throws IOException, ClassNotFoundException {
        boolean exit = true; // TODO: change to False to test connectivity
        while (!exit) {
            // Create Socket and waiting for client connection
            try (var s = listener.accept()) {
                log.LogInfo("Accepting Socket connections....");
                String msg = "";

                try (var pw = new PrintWriter(s.getOutputStream(), true);
                        var br = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

                    // Write back to the client
                    pw.println("Welcome to ServerSocket! \r\n Text >");

                    // Read from the client
                    log.LogInfo("Creating Input Stream from Client...");
                    msg = br.readLine();

                    log.LogInfo("Message received: " + msg);
                    pw.println("Message received: " + msg);
                }

                if (msg.equalsIgnoreCase("exit")) {
                    log.LogInfo("Closing Socket...");
                    exit = true;
                }
            }
        }
    }

    private static String CheckDirectory() {
        var datadir = SettingsHelper.GetDataDirectory();
        var path = Paths.get(datadir);

        // Check if Directory/path exists
        log.LogInfo("*** Checking Data Directory ***");
        if (!Files.isDirectory(path)) {
            // Directory/Path does NOT exist
            log.LogConfig("Data Directory (" + datadir + ") Does not Exist");
            try {
                Files.createDirectories(path);
                log.LogConfig("Data Directory (" + datadir + ")....Created");
            } catch (IOException ex) {
                log.LogSevere(ex.getMessage());
            }
        }

        return datadir;
    }
}
