module com.example.typeracerbootcamp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.media;

    opens com.example.typeracerbootcamp to javafx.fxml;
    exports com.example.typeracerbootcamp;
}