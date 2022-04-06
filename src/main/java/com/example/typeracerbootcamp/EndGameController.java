package com.example.typeracerbootcamp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EndGameController {
    private int wordsHit;
    private float accuracy;
    private float wpm;

    @FXML
    private Label scoreWordsHit;
    @FXML
    private Label scoreAccuracy;
    @FXML
    private Label scorewpm;
    @FXML
    private Button BackToMeniu;
    @FXML
    private Button exitButton;

    public void init(Label label){
        System.out.println("[DEBUG] initialization...");
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EndGame.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600,400);
            Stage stage = (Stage) label.getScene().getWindow();
            String css = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("Type Racer");
            Image icon = new Image("file:src/main/java/images/image.png");
            stage.getIcons().add(icon);
            onLoad();
            BackToMeniu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try{
                        MainMenu();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            exitButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Exit();
                }
            });
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onLoad(){
        scoreWordsHit.setText(String.valueOf(wordsHit)+" ");
        scoreAccuracy.setText(String.valueOf(accuracy*100)+"% ");
        scorewpm.setText(String.valueOf(wpm) + " ");
    }

    public void insertScore(int WordsHit,float Accuracy,float wpm){
        wordsHit=WordsHit;
        accuracy=Accuracy;
        this.wpm = wpm;
    }
    public void MainMenu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600,400);
        Stage stage = (Stage) scoreAccuracy.getScene().getWindow();
        String css = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Type Racer");
        Image icon = new Image("file:src/main/java/images/image.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
    public void Exit(){
        Platform.exit();
    }
}
