module com.imcalledtech.mcwd {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.io;
    requires com.google.gson;
    requires java.desktop;


    opens com.imcalledtech.mcwd to javafx.fxml;
    exports com.imcalledtech.mcwd;
}