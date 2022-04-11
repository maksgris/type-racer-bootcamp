package com.example.typeracerbootcamp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {
    public void Back(ActionEvent e) throws IOException {
        FXMLLoader popup = new FXMLLoader(getClass().getResource("LoginAlert.fxml"));
        OnlineGamePopup control = popup.getController();
        Parent rot = popup.load();
        Stage stg = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scn = new Scene(rot);
        String css = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();
        scn.getStylesheets().add(css);
        stg.setTitle("Competitive Type Racing");
        Image icon = new Image("file:src/main/java/com/example/typeracerbootcamp/image.png");
        stg.getIcons().add(icon);
        stg.setScene(scn);
        stg.show();
    }
}
