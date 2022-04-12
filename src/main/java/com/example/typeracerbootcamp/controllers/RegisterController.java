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


public class RegisterController {

    @FXML
    private TextField uname;
    @FXML
    private TextField email;
    @FXML
    private TextField pword;
    @FXML
    private TextField rpword;
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
        Image icon = new Image("file:src/main/java/com/example/typeracerbootcamp/image.png");
        stg.getIcons().add(icon);
        stg.setScene(scn);
        stg.show();
    }
    public void register(ActionEvent e) throws IOException{
        System.out.println("[DEBUG] serverLink established succesfully...");
        link.regUser(uname.getText(), email.getText(), pword.getText(), rpword.getText());
//        System.out.println("[DEBUG] executing SQL link and user registration...");
//        SQLController dblink = new SQLController();
//        if(dblink.registerUser(uname.getText(), email.getText(), pword.getText(), rpword.getText())){
//            System.out.println("[DEBUG] user created succesfully!");
//        }else{
//            System.out.println("[DEBUG] Error, user failed to create!");
//        }
    }

}
