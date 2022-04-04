package com.example.typeracerbootcamp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private GameController controller;
    private FXMLLoader fxmlLoader;

    public void StartGame() throws IOException {
        Source m = new Source();
        m.ChangeScene("InGame.fxml");

        try{controller = fxmlLoader.getController();}
        catch (Exception ex){ex.printStackTrace();}
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
        stage.setScene(scene);
        stage.show();
    }

    public void ExitGame() {
        Platform.exit();
    }
}
