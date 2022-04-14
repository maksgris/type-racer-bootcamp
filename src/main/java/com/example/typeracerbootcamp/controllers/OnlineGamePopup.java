package com.example.typeracerbootcamp.controllers;

import com.example.typeracerbootcamp.Links.FileLink;
import com.example.typeracerbootcamp.Links.ServerLink;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class OnlineGamePopup implements Initializable {

    private ServerLink link = SceneController.link;
    public void Login(ActionEvent e) throws IOException {
        FileLink file = new FileLink("temp.txt",true);
        try{if(file.out()[0].equals(null) && file.out()[1].equals(null)){
            System.out.println("[DEBUG]Initializing login screen...");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/typeracerbootcamp/Login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            String css = Objects.requireNonNull(this.getClass().getResource("/com/example/typeracerbootcamp/application.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("Competitive Type Racing");
            Image icon = new Image("file:src/main/java/images/image.png");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        else{
            link.logUser(file.out()[0],file.out()[1]);
        }}catch (RuntimeException ex){
            System.out.println("[DEBUG]Initializing login screen...");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/typeracerbootcamp/Login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            String css = Objects.requireNonNull(this.getClass().getResource("/com/example/typeracerbootcamp/application.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("Competitive Type Racing");
            Image icon = new Image("file:src/main/java/images/image.png");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void Register(ActionEvent e) throws IOException{
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            link = new ServerLink();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
