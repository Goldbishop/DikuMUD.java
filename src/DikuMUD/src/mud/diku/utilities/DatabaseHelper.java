/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mud.diku.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import static mud.diku.objects.ALoggingObject.log;

/**
 *
 * @author goldbishop
 */
public class DatabaseHelper {

    public static void BootDatabase() {
        log.LogInfo("### Booting Database ###");
        var datadir = CheckDirectory();

        // Read Files in Data Directory
        log.LogInfo("*** Capturing Data Files ***");

        var fldr = new File(datadir);
        // Assume one Level folder structure <root>/<level_one>
        var arrFolders = new ArrayList<String>();
        var repeat = true;
        while (repeat) {
            if (arrFolders.size() > 0) {
                fldr = new File(arrFolders.get(0));
                arrFolders.remove(0);
            }

            var arrFiles = fldr.listFiles();

            if (arrFiles == null) {
                log.LogWarning("Files Array has not been filled");
            } else {
                for (var file : arrFiles) {
                    if (file.isDirectory()) {
                        // Assumes @datadir as the root folder and assumes only 1 level down
                        log.LogInfo("Folder Found: " + file.getName());
                        arrFolders.add(datadir + "/" + file.getName());
                    } else {
                        log.LogInfo("File Found: " + file.getName());
                        // Check extensions for processing type
                        var nm = file.getName();
                        String ext = "";
                        if (nm.lastIndexOf(".") != 1 && nm.lastIndexOf(".") != 0) {
                            ext = nm.substring(nm.lastIndexOf(".") + 1);
                            switch (ext.toLowerCase()) {
                                case "idx":
                                    // parse File as an Index file
                                    log.LogInfo("Index File: " + nm);
                                    break;
                                case "info":
                                    // prase File as an Information file
                                    log.LogInfo("Information File: " + nm);
                                    break;
                                case "help":
                                    // parse File as a Help File
                                    log.LogInfo("Help File: " + nm);
                                    break;
                                case "json":
                                    // parse File as a JSON File; acts like a data file
                                    log.LogInfo("JSON File: " + nm);
                                    break;
                                case "map":
                                    // map files referenced in Zone.idx
                                    log.LogInfo("Map file: " + nm);
                                    break;
                                case "md":
                                    // Do nothing...MD files are documentation types
                                    log.LogInfo("Markdown File: " + nm);
                                    break;
                                default:
                                    // Unknown or Ignored extension
                                    log.LogInfo(">>>>>> Unknown File: " + nm + " <<<<<<");
                                    break;
                            }
                        }
                    }
                }

                if (arrFolders.isEmpty()) {
                    // no subfolders to process
                    repeat = false;
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
