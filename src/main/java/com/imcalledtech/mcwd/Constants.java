package com.imcalledtech.mcwd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;

public class Constants {

    // constants
    
    public static final String APP_VER = "1.1";

    public static final ObservableList<Controller.DirectoryInfo> EMPTY_LIST = FXCollections.emptyObservableList();

    public static final String OPERATING_SYSTEM = System.getProperty("os.name").toLowerCase();

    public static final String USER_HOME = System.getProperty("user.home");

    public static final String WINDOWS_MINECRAFT_FOLDER = USER_HOME+"\\AppData\\Roaming\\.minecraft";

    public static final String MACOS_MINECRAFT_FOLDER = USER_HOME+"/Library/Application Support/minecraft";

    public static final String LINUX_MINECRAFT_FOLDER = USER_HOME+"/.minecraft";

    public static final File OPTIONS_FILE_PATH = new File(USER_HOME+"\\.imcalledtech\\MCWD\\options.json");

    public static final String[] OPTIONS_KEYS = {"default_minecraft_folder"};

}