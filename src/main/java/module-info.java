module com.example.typeracerbootcamp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires javafx.media;
    requires com.google.common;

    opens com.example.typeracerbootcamp to javafx.fxml;
    exports com.example.typeracerbootcamp;
    exports com.example.typeracerbootcamp.SERVER;
    opens com.example.typeracerbootcamp.SERVER to javafx.fxml;
    opens com.example.typeracerbootcamp.controllers to javafx.fxml;
    exports com.example.typeracerbootcamp.Links;
    opens com.example.typeracerbootcamp.Links to javafx.fxml;
}