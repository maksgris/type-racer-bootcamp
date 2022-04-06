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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SceneController implements Initializable {
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private FXMLLoader fxmlLoader;
    private GameController gameController;
    private GameController controller;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private GameController finalController;
    private int time;

    @FXML
    Label myLabel;


    @FXML
    Button PlayGame;

    public void StartGame(ActionEvent e)  throws IOException  {
        fxmlLoader = new FXMLLoader(getClass().getResource("InGame.fxml"));
        controller = fxmlLoader.getController();
        fxmlLoader.setController(controller = new GameController());
        root = fxmlLoader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, 600,400);
        String css = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        controller.load(true);
        finalController = controller;
        time =5;
        Runnable timer = new Runnable() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    time--;
                    System.out.println("[DEBUG] timer runing... " + time);
                    if(time<=0) {
                        System.out.println("[DEBUG] horay! " + time + " <= 0");
                        EndGame();
                    }
                });
                }
            };
        Thread thread = new Thread(timer);
        thread.setDaemon(true);
        executorService.scheduleAtFixedRate(thread,0,1, TimeUnit.SECONDS);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if(time>0){
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
                if(e.getCode()==KeyCode.ESCAPE){
                    try{
                        executorService.shutdown();
                        finalController.endgamelistener();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
                }

        });
        scene.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                executorService.shutdown();
                System.out.println("[DEBUG] thread closed");
            }
        });
        stage.setScene(scene);
        stage.show();
    }
    public void ExitGame() {
        Platform.exit();
    }
    public void EndGame(){
        System.out.println("[DEBUG] EndGame() in SceneController");
        executorService.shutdown();
        finalController.endGame();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}