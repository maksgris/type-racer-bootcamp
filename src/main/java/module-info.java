module com.example.typeracerbootcamp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;

    opens com.example.typeracerbootcamp to javafx.fxml;
    exports com.example.typeracerbootcamp;
    exports com.example.typeracerbootcamp.SERVER;
    opens com.example.typeracerbootcamp.SERVER to javafx.fxml;
    exports com.example.typeracerbootcamp.controllers;
    opens com.example.typeracerbootcamp.controllers to javafx.fxml;
}