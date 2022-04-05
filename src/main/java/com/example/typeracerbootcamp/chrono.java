package com.example.typeracerbootcamp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class chrono{

    private int timer;
    private GameController gameroot;
    private SceneController sceneroot;

    @FXML
    private Label labelTimer;

    chrono(ActionEvent event){
        timer=10;
        System.out.println("[DEBUG] timer constructed! time is " + timer);
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
