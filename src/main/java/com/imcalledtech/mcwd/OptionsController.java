package com.imcalledtech.mcwd;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;

public class OptionsController {

    @FXML
    private TextField defaultMinecraftFolder;

    @FXML
    private TextField searchPrefix;

    @FXML
    private CheckBox searchOnAppStart;

    @FXML
    private void defaultMinecraftFolderSubmit() throws IOException {
        App.options.setOptions("default_minecraft_folder", defaultMinecraftFolder.getText());
    }

    @FXML
    private void searchPrefixSubmit() throws IOException {
        App.options.setOptions("search_prefix", searchPrefix.getText());
    }

    @FXML
    private void searchOnAppStartSubmit() throws IOException {
        App.options.setOptions("search_on_application_start", searchOnAppStart.isSelected());
    }

    @FXML
    private void browse() throws IOException {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setInitialDirectory(new File(Constants.USER_HOME));
        chooser.setTitle("Select minecraft folder");
        File selectedDir = chooser.showDialog(App.optionsStage);
        if (selectedDir != null) {
            String selectedDirString = selectedDir.getAbsolutePath();
            App.options.setOptions("default_minecraft_folder", selectedDirString);
            defaultMinecraftFolder.setText(selectedDirString);
        }
    }

    protected void setToJsonValues() throws IOException {
        defaultMinecraftFolder.setText(App.options.getOptions("default_minecraft_folder"));
        searchPrefix.setText(App.options.getOptions("search_prefix"));
        searchOnAppStart.setSelected(App.options.getOptions("search_on_application_start"));
    }

}
