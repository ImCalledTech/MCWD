module com.imcalledtech.mcwd {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.io;


    opens com.imcalledtech.mcwd to javafx.fxml;
    exports com.imcalledtech.mcwd;
}