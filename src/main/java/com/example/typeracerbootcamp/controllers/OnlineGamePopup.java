package com.example.typeracerbootcamp.controllers;

import com.example.typeracerbootcamp.Links.ServerLink;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OnlineGamePopup {

    private ServerLink link;
    public void Login(ActionEvent e) throws IOException {
        link = new ServerLink();
        LoginController.instantiateLink(link);
        System.out.println("[DEBUG]Initializing login screen...");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/typeracerbootcamp/Login.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("/com/example/typeracerbootcamp/application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Competitive Type Racing");
        Image icon = new Image("file:src/main/resources/com/example/images/image.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void Register(ActionEvent e) throws IOException{
        link = new ServerLink();
        RegisterController.instantiateLink(link);
        System.out.println("[DEBUG]Initializing register screen...");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/typeracerbootcamp/Register.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("/com/example/typeracerbootcamp/application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Competitive Type Racing");
        Image icon = new Image("file:src/main/resources/com/example/images/image.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void nevermind(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
