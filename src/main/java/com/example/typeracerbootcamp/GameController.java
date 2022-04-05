package com.example.typeracerbootcamp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {

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
    public void load(boolean hit){
        if(hit){
            label1.setText(words[r.nextInt(words.length)]);
            labelinput.setText("");
        }
        else
            labelinput.setText("");
    }

    public void onEnter(String e){
        if(labelinput.getText().toString().equals(label1.getText())){
            System.out.println("[DEBUG] Good hit!");
            this.load(true);
            }
        else{
            System.out.println("[DEBUG] Nope!");
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
//    public static void aquireStage(Stage s){
//        stg = s;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("[DEBUG] initialize");
    }
}
