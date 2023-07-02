package com.imcalledtech.mcwd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    public static Options options;

    @Override
    public void start(Stage stage) throws IOException {
        // initialise the options
        options = new Options(Constants.OPTIONS_FILE_PATH);
        //initialise the stage and scenes
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("Main.fxml")));
        Parent root = loader.load();
        Controller controller = loader.getController();
        String title = "MCWD v"+Constants.APP_VER;
        controller.getTitle().setText(title);
        controller.initialize(options.getOptions("default_minecraft_folder")+"\\saves");
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    protected static String getOsMinecraftFolder() {

        // gets user's .minecraft folder, depending on their os
        String os = Constants.OPERATING_SYSTEM;
        if (os.contains("win")) {
            return Constants.WINDOWS_MINECRAFT_FOLDER;
        } else if (os.contains("mac")) {
            return Constants.MACOS_MINECRAFT_FOLDER;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("linux")) {
            return Constants.LINUX_MINECRAFT_FOLDER;
        } else {
            return Constants.USER_HOME;
        }
    }

}