package com.imcalledtech.mcwd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;

public class Constants {

    // constants
    
    public static final String APP_VER = "1.1.2.1";

    public static final ObservableList<Controller.DirectoryInfo> EMPTY_LIST = FXCollections.emptyObservableList();

    public static final String OPERATING_SYSTEM = System.getProperty("os.name").toLowerCase();

    public static final String USER_HOME = System.getProperty("user.home");

    public static final String WINDOWS_MINECRAFT_FOLDER = USER_HOME+"\\AppData\\Roaming\\.minecraft";

    public static final String MACOS_MINECRAFT_FOLDER = USER_HOME+"/Library/Application Support/minecraft";

    public static final String LINUX_MINECRAFT_FOLDER = USER_HOME+"/.minecraft";

    public static final File OPTIONS_FILE_PATH = new File(USER_HOME+File.separator+".imcalledtech"+File.separator+"MCWD"+File.separator+"options.json");

    public static final String[] OPTIONS_KEYS = {"default_minecraft_folder", "search_prefix", "search_on_application_start"};

    public static final Class<?>[] OPTIONS_TYPES = {String.class, String.class, Boolean.class};

    public static final Object[] OPTIONS_DEFAULTS = {App.getOsMinecraftFolder(), "Random Speedrun #", true};

}