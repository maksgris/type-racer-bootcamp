package com.example.typeracerbootcamp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Source extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Source.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        Controller controller = fxmlLoader.getController();
        controller.load(true);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    System.out.println("[DEBUG] ENTER HIT");
                    controller.onEnter(e.getCode().toString());
                }
                else if(e.getCode().toString().length()==1) {
                    System.out.println("[DEBUG] LETTER HIT " + e.getCharacter());
                    controller.onLetterPress(e.getCode().toString());
                }
                else
                    System.out.println(e.getCode());
                }
        });

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}