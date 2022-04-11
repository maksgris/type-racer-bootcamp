package com.example.typeracerbootcamp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLController {

    private String url = "jdbc:mysql://localhost:3306/typeracerdb";
    private String SQLuser = "root";
    private String SQLpass = "";
    private Connection connection;
    private String query;

    SQLController() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,SQLuser,SQLpass);
            System.out.println("[DEBUG] Connection successful! " + url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean registerUser(String username, String email,String password,String passwordr){
        query="INSERT INTO `useraccounts`( `userName`, `email`, `password`, `ELO`) VALUES (\""+username+"\",\""+email+"\",\""+password+"\",0)";
        if(password.equals(passwordr)) {
            System.out.println("[DEBUG] passwords match!");
            try {
                System.out.println("[DEBUG] Creating User...");
                Statement statement = connection.createStatement();
                if (statement.executeUpdate(query) == 1) {
                    System.out.println("[DEBUG] A new client has been added!");
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("[DEBUG] passwords do not match! " + password.trim() + " != " + passwordr.trim());
            return false;
        }
        return false;
    }
}
