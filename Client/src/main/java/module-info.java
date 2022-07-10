module com.example.graphiscord {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens com.example.graphiscord to javafx.fxml;
    exports com.example.graphiscord;
}