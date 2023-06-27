package com.imcalledtech.mcwd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Constants {

    // constants
    
    public static final String APP_VER = "1.0";

    public static final ObservableList<Controller.DirectoryInfo> EMPTY_LIST = FXCollections.emptyObservableList();

    public static final String OPERATING_SYSTEM = System.getProperty("os.name").toLowerCase();

    public static final String USER_HOME = System.getProperty("user.home");

    public static final String WINDOWS_MINECRAFT_FOLDER = System.getenv("APPDATA")+"/.minecraft";

    public static final String MACOS_MINECRAFT_FOLDER = USER_HOME+"/Library/Application Support/minecraft";

    public static final String LINUX_MINECRAFT_FOLDER = USER_HOME+"/.minecraft";

}
