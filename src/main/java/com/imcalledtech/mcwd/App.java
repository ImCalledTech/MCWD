package com.imcalledtech.mcwd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //initialise the stage and scenes
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(App.class.getResource("Main.fxml")));
        Parent root = loader.load();
        Controller controller = loader.getController();
        String title = "MCWD v"+Constants.APP_VER;
        controller.getTitle().setText(title);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}