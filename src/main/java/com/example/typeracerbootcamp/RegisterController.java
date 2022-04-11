package com.example.typeracerbootcamp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class RegisterController {

    @FXML
    private TextField uname;
    @FXML
    private TextField email;
    @FXML
    private TextField pword;
    @FXML
    private TextField rpword;

    public void Back(ActionEvent e) throws IOException {
        FXMLLoader popup = new FXMLLoader(getClass().getResource("LoginAlert.fxml"));
        OnlineGamePopup control = popup.getController();
        Parent rot = popup.load();
        Stage stg = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scn = new Scene(rot);
        stg.setScene(scn);
        stg.show();
    }
    public void register(ActionEvent e) throws IOException{
        ServerLink link = new ServerLink();
        System.out.println("[DEBUG] serverLink established succesfully...");
        System.out.println("[DEBUG] calling 3 ping tests...");
        link.test();
        link.test();
        link.test();
        System.out.println("[DEBUG] tests done!");
//        System.out.println("[DEBUG] executing SQL link and user registration...");
//        SQLController dblink = new SQLController();
//        if(dblink.registerUser(uname.getText(), email.getText(), pword.getText(), rpword.getText())){
//            System.out.println("[DEBUG] user created succesfully!");
//        }else{
//            System.out.println("[DEBUG] Error, user failed to create!");
//        }
    }

}
