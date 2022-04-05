package com.example.typeracerbootcamp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    @FXML
    Button PlayGame;

    public void StartGame(ActionEvent e)  throws IOException  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InGame.fxml"));
        GameController controller = fxmlLoader.getController();
        fxmlLoader.setController(controller = new GameController());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600,400);
        controller.load(true);
        GameController finalController = controller;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    System.out.println("[DEBUG] ENTER HIT");
                    finalController.onEnter(e.getCode().toString());
                }
                else if(e.getCode().toString().length()==1) {
                    System.out.println("[DEBUG] LETTER HIT " + e.getText());
                    finalController.onLetterPress(e.getText());
                }
                else if(e.getCode() == KeyCode.BACK_SPACE)
                    finalController.delchar();
                System.out.println(e.getCode());
            }
        });
        stage.setScene(scene);
        stage.show();
    }
    public void ExitGame() {
        Platform.exit();
    }
}

//        try{
//            controller.load(true);
//        }
//        catch (Exception exception){
//            exception.printStackTrace();
//        }
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent e) {
//                if (e.getCode() == KeyCode.ENTER) {
//                    System.out.println("[DEBUG] ENTER HIT");
//                    controller.onEnter(e.getCode().toString());
//                }
//                else if(e.getCode().toString().length()==1) {
//                    System.out.println("[DEBUG] LETTER HIT " + e.getText());
//                    controller.onLetterPress(e.getText());
//                }
//                else if(e.getCode() == KeyCode.BACK_SPACE)
//                    controller.delchar();
//                System.out.println(e.getCode());
//            }
//        });