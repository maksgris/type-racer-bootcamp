package com.example.typeracerbootcamp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameController {

    private int wordsHit;
    private int wordsTotal;
    private float accuracy;
    private float wpm;
    private Random r = new Random();
    private String temp;
    private String[] words = {"lorem", "ipsum", "dolor", "sit", "amet", "consectetur",
            "adipiscing", "elit", "curabitur", "vel", "hendrerit", "libero",
            "eleifend", "blandit", "nunc", "ornare", "odio", "ut",
            "orci", "gravida", "imperdiet", "nullam", "purus", "lacinia",
            "a", "pretium", "quis", "congue", "praesent", "sagittis",
            "laoreet", "auctor", "mauris", "non", "velit", "eros",
            "dictum", "proin", "accumsan", "sapien", "nec", "massa",
            "volutpat", "venenatis", "sed", "eu", "molestie", "lacus",
            "quisque", "porttitor", "ligula", "dui", "mollis", "tempus",
            "at", "magna", "vestibulum", "turpis", "ac", "diam",
            "tincidunt", "id", "condimentum", "enim", "sodales", "in",
            "hac", "habitasse", "platea", "dictumst", "aenean", "neque",
            "fusce", "augue", "leo", "eget", "semper", "mattis",
            "tortor", "scelerisque", "nulla", "interdum", "tellus", "malesuada",
            "rhoncus", "porta", "sem", "aliquet", "et", "nam",
            "suspendisse", "potenti", "vivamus", "luctus", "fringilla", "erat",
            "donec", "justo", "vehicula", "ultricies", "varius", "ante",
            "primis", "faucibus", "ultrices", "posuere", "cubilia", "curae",
            "etiam", "cursus", "aliquam", "quam", "dapibus", "nisl",
            "feugiat", "egestas", "class", "aptent", "taciti", "sociosqu",
            "ad", "litora", "torquent", "per", "conubia", "nostra",
            "inceptos", "himenaeos", "phasellus", "nibh", "pulvinar", "vitae",
            "urna", "iaculis", "lobortis", "nisi", "viverra", "arcu",
            "morbi", "pellentesque", "metus", "commodo", "ut", "facilisis",
            "felis", "tristique", "ullamcorper", "placerat", "aenean", "convallis",
            "sollicitudin", "integer", "rutrum", "duis", "est", "etiam",
            "bibendum", "donec", "pharetra", "vulputate", "maecenas", "mi",
            "fermentum", "consequat", "suscipit", "aliquam", "habitant", "senectus",
            "netus", "fames", "quisque", "euismod", "curabitur", "lectus",
            "elementum", "tempor", "risus", "cras"};
    @FXML
    private Label label1;
    @FXML
    private Label labelinput;
    @FXML
    private Label labelTimer;
    public void load(boolean hit){
        if(hit){
            label1.setText(words[r.nextInt(words.length)]);
            labelinput.setText("");
            wordsTotal++;
        }
        else{
            label1.setText(words[r.nextInt(words.length)]);
            labelinput.setText("");
            wordsTotal++;
        }
    }
    GameController(){
        wordsHit=0;
        wordsTotal=0;
        accuracy=0;
        wpm=0;
    }

    public void onEnter(String e){
        if(labelinput.getText().toString().equals(label1.getText())){
            wordsHit++; accuracy = (float)wordsHit/wordsTotal;
            System.out.println("[DEBUG] Good hit! wordsHit: " + wordsHit + " accuracy: " + accuracy);
            this.load(true);
            }
        else{
            accuracy = (float)wordsHit/wordsTotal;
            System.out.println("[DEBUG] Nope! accuracy: " + accuracy);
            System.out.println("[DEBUG] label = " + label1.getText());
            System.out.println("[DEBUG] answ  = " + labelinput.getText());
            this.load(false);
        }
    }
    public void onLetterPress(String e){
        if(labelinput.getText().length()<=label1.getText().length()){
            temp = labelinput.getText();
            labelinput.setText(temp+e);
        }
        else{
            System.out.println("[DEBUG] too long!");
        }
    }
    public void endGame(){
        Platform.setImplicitExit(false);
        System.out.println("[DEBUG] game successfully ended from controller!");
        System.out.println("[DEBUG] wordsTotal: " + wordsTotal + " wordsHit: " + wordsHit  + " accuracy:" + accuracy + " wpm: " + wpm);
        label1.setText("Game over!\n wordsTotal: " + wordsTotal + " wordsHit: " + wordsHit  + " accuracy:" + accuracy + " wpm: " + wpm);
        labelinput.setText("Press esc to go to Score board!");
    }
    public void endgamelistener() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EndGame.fxml"));
        EndGameController controller = fxmlLoader.getController();
        fxmlLoader.setController(controller = new EndGameController());
        controller.insertScore(wordsHit,accuracy,wpm);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600,400);
        Stage stage = (Stage) label1.getScene().getWindow();
        String css = Objects.requireNonNull(this.getClass().getResource("/com/example/typeracerbootcamp/application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Competitive Type Racing");
        Image icon = new Image("file:src/main/java/com/example/typeracerbootcamp/image.png");
        stage.getIcons().add(icon);
        controller.init(label1);
        stage.setScene(scene);
        stage.show();
    }
    public void delchar(){
        temp = labelinput.getText();
        try{
            temp = temp.substring(0,temp.length()-1);
        }
        catch (StringIndexOutOfBoundsException e){
            temp = "";
        }
        labelinput.setText(temp);
    }
    public void updateTimer(int time){
        System.out.print("[DEBUG] wpm calc: " + wordsHit + "/(" + (15-time) + "/60) = ");
        wpm=(time!=15&&wordsHit>0)?wordsHit/((float)(15-time)/60):0;
        System.out.print(wpm);
        labelTimer.setText(String.valueOf(time));
        System.out.println();
    }
}
