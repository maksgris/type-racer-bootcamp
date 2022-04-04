package com.example.typeracerbootcamp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.util.Locale;
import java.util.Random;

public class Controller {
    Random r = new Random();
    String temp;
    String[] words = {"lorem", "ipsum", "dolor", "sit", "amet", "consectetur",
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

//    Controller(){
//        labelinput.setText("");
//    }
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
        temp = labelinput.getText();
        labelinput.setText(temp+e);
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

}
