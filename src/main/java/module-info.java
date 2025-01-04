module org.mikel.dein_jasper_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;

    opens org.mikel.dein_jasper_3.controladores to javafx.fxml;
    opens org.mikel.dein_jasper_3 to javafx.fxml;
    exports org.mikel.dein_jasper_3;
}