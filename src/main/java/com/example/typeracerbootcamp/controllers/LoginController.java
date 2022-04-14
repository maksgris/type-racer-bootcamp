package com.example.typeracerbootcamp.controllers;

import com.example.typeracerbootcamp.Links.FileLink;
import com.example.typeracerbootcamp.Source;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.google.common.hash.Hashing.sha256;

public class LoginController {
    @FXML
    private TextField uname;

    @FXML
    private TextField pass;

    @FXML
    private CheckBox Rememberer;
    private String temp;
    public void Back(ActionEvent e) throws IOException {
        FXMLLoader popup = new FXMLLoader(getClass().getResource("/com/example/typeracerbootcamp/LoginAlert.fxml"));
        OnlineGamePopup control = popup.getController();
        Parent rot = popup.load();
        Stage stg = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scn = new Scene(rot);
        String css = Objects.requireNonNull(this.getClass().getResource("/com/example/typeracerbootcamp/application.css")).toExternalForm();
        scn.getStylesheets().add(css);
        stg.setTitle("Competitive Type Racing");
        Image icon2 = new Image("file:src/main/java/com/example/typeracerbootcamp/image.png");
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
        temp=SceneController.link.logUser(uname.getText(), sha256().hashString(pass.getText(),StandardCharsets.UTF_8).toString());
        if(!temp.equals(null)){
            Source.injectNick(temp);
            SceneController.setnick(temp);
            if(Rememberer.isSelected()){
                String sha256hex = sha256().hashString(pass.getText(), StandardCharsets.UTF_8).toString();
                System.out.println("[DEBUG] pass is " + sha256hex);
                System.out.println("[DEBUG] ready to save to file...");
                FileLink file = new FileLink("temp.txt",false);
                file.write(uname.getText() + "\n" + sha256().hashString(pass.getText(), StandardCharsets.UTF_8).toString());
            }
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.close();
            System.out.println("[DEBUG] Login successful! Nick should be set on MainMenu to " + Source.outNick());
        }else{
            System.out.println("[DEBUG] Error!");
        }
    }
}
