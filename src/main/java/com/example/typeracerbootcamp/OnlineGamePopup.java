package com.example.typeracerbootcamp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OnlineGamePopup {

    public void Login(ActionEvent e) throws IOException {
        System.out.println("[DEBUG]Initializing login screen...");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Competitive Type Racing");
        Image icon = new Image("file:src/main/java/images/image.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
    public void Register(ActionEvent e) throws IOException{
        System.out.println("[DEBUG]Initializing register screen...");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Competitive Type Racing");
        Image icon = new Image("file:src/main/java/images/image.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
    public void nevermind(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
