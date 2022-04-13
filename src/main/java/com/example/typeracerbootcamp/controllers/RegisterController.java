package com.example.typeracerbootcamp.controllers;

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
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.google.common.hash.Hashing.sha256;


public class RegisterController {

    @FXML
    private TextField uname;
    @FXML
    private TextField email;
    @FXML
    private TextField pword;
    @FXML
    private TextField rpword;

    public void Back(ActionEvent e) throws IOException {
        FXMLLoader popup = new FXMLLoader(getClass().getResource("/com/example/typeracerbootcamp/LoginAlert.fxml"));
        OnlineGamePopup control = popup.getController();
        Parent rot = popup.load();
        Stage stg = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scn = new Scene(rot);
        String css = Objects.requireNonNull(this.getClass().getResource("/com/example/typeracerbootcamp/application.css")).toExternalForm();
        scn.getStylesheets().add(css);
        stg.setTitle("Competitive Type Racing");
        Image icon = new Image("file:src/main/java/com/example/typeracerbootcamp/image.png");
        stg.getIcons().add(icon);
        stg.setScene(scn);
        stg.show();
    }
    public void register(ActionEvent e) throws IOException{
        System.out.println("[DEBUG] serverLink established succesfully...");
        SceneController.link.regUser(uname.getText(), email.getText(), sha256().hashString(pword.getText(), StandardCharsets.UTF_8).toString(), sha256().hashString(rpword.getText(), StandardCharsets.UTF_8).toString());
//        System.out.println("[DEBUG] executing SQL link and user registration...");
//        SQLController dblink = new SQLController();
//        if(dblink.registerUser(uname.getText(), email.getText(), pword.getText(), rpword.getText())){
//            System.out.println("[DEBUG] user created succesfully!");
//        }else{
//            System.out.println("[DEBUG] Error, user failed to create!");
//        }
    }

}
