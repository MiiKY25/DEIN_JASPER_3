module org.mikel.dein_jasper_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.mikel.dein_jasper_3 to javafx.fxml;
    exports org.mikel.dein_jasper_3;
}