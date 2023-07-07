package com.imcalledtech.mcwd;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class OptionsController {

    @FXML
    private TextField instanceDir;

    @FXML
    private void instanceDirSubmit() {
        instanceDir.clear();
    }

}
