package com.example.typeracerbootcamp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    public void Back(ActionEvent e) throws IOException {
        FXMLLoader popup = new FXMLLoader(getClass().getResource("LoginAlert.fxml"));
        OnlineGamePopup control = popup.getController();
        Parent rot = popup.load();
        Stage stg = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scn = new Scene(rot);
        stg.setScene(scn);
        stg.show();
    }
}
