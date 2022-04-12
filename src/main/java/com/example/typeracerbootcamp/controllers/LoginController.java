package com.example.typeracerbootcamp.controllers;

import com.example.typeracerbootcamp.ServerLink;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField uname;
    @FXML
    private TextField pass;
    static ServerLink link;
    public static void instantiateLink(ServerLink linkin){
        link = linkin;
    }
    public void Back(ActionEvent e) throws IOException {
        FXMLLoader popup = new FXMLLoader(getClass().getResource("/com/example/typeracerbootcamp/LoginAlert.fxml"));
        OnlineGamePopup control = popup.getController();
        Parent rot = popup.load();
        Stage stg = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scn = new Scene(rot);
        String css = Objects.requireNonNull(this.getClass().getResource("/com/example/typeracerbootcamp/application.css")).toExternalForm();
        scn.getStylesheets().add(css);
        stg.setTitle("Competitive Type Racing");
        Image icon2 = new Image("file:src/main/java/images/image.png");
        stg.getIcons().add(icon2);
        stg.setScene(scn);
        stg.show();
    }
    public void toRegister(ActionEvent e) throws IOException{
        System.out.println("[DEBUG]Initializing register screen...");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/typeracerbootcamp/Register.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void Login(ActionEvent e){
        link.logUser(uname.getText(),pass.getText());
    }
}
