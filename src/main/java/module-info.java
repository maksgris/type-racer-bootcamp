module com.example.typeracerbootcamp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.typeracerbootcamp to javafx.fxml;
    exports com.example.typeracerbootcamp;
}