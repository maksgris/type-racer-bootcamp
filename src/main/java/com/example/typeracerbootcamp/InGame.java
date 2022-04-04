package com.example.typeracerbootcamp;

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

public class InGame {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void Gameplay(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Source.class.getResource("InGame.fxml"));
        Parent root = fxmlLoader.load();
        //stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        GameController controller = fxmlLoader.getController();
        controller.load(true);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    System.out.println("[DEBUG] ENTER HIT");
                    controller.onEnter(e.getCode().toString());
                }
                else if(e.getCode().toString().length()==1) {
                    System.out.println("[DEBUG] LETTER HIT " + e.getText());
                    controller.onLetterPress(e.getText());
                }
                else if(e.getCode() == KeyCode.BACK_SPACE)
                    controller.delchar();
                System.out.println(e.getCode());
            }
        });
        stage.setScene(scene);
        stage.show();
    }

}
