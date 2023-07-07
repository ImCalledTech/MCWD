package com.imcalledtech.mcwd;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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
        defaultMinecraftFolder.clear();
    }

    @FXML
    private void searchPrefixSubmit() throws IOException {
        App.options.setOptions("search_prefix", searchPrefix.getText());
        searchPrefix.clear();
    }

    @FXML
    private void searchOnAppStartSubmit() throws IOException {
        App.options.setOptions("search_on_application_start", searchOnAppStart.isSelected());
    }

}
