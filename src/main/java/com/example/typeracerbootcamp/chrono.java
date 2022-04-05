package com.example.typeracerbootcamp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class chrono implements Runnable{

    private int timer;
    private GameController gameroot;
    private SceneController sceneroot;

    @FXML
    private Label labelTimer;

    chrono(ActionEvent event){
//        try{
//            fxmlLoader = new FXMLLoader(getClass().getResource("InGame.fxml"));
//            root = fxmlLoader.load();
//            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            scene = ((Node) event.getSource()).getScene();
//            stage.setScene(scene);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        timer=10;
        System.out.println("[DEBUG] timer constructed! time is " + timer);
    }
    @Override
    public void run() {
        timer--;
        System.out.println("[DEBUG] timer runing... " + timer);
        if(timer<=0){
            gameroot.endGame();
            sceneroot.refresh();
        }
    }
    public boolean canplay(){
        System.out.println("[DEBUG] performing timer check! " + timer);
        if(timer<=0)
            return false;
        else return true;

    }
    public int getTimer(){
        return timer;
    }
    public void setroot(SceneController sceneController,GameController gameController){
        sceneroot=sceneController;
        gameroot=gameController;
    }
}
