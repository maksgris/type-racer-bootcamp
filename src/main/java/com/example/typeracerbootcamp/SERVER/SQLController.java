package com.example.typeracerbootcamp.SERVER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLController {

    private String url = "jdbc:mysql://192.168.1.252:3306/typeracerdb";
    private String SQLuser = "root";
    private String SQLpass = "";
    private Connection connection;
    private String query;
    Statement statement;

    SQLController() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,SQLuser,SQLpass);
            statement = connection.createStatement();
            System.out.println("[DEBUG SERVER SQL] Connection to sql successful! " + url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean registerUser(String username, String email,String password,String passwordr){
        query="INSERT INTO `useraccounts`( `userName`, `email`, `password`, `ELO`) VALUES (\""+username+"\",\""+email+"\",\""+password+"\",0)";
        if(password.equals(passwordr)) {
            System.out.println("[DEBUG SERVER SQL] passwords match!");
            try {
                System.out.println("[DEBUG SERVER SQL] Creating User...");
                if (statement.executeUpdate(query) == 1) {
                    System.out.println("[DEBUG SERVER SQL] A new client has been added!");
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("[DEBUG SERVER SQL] passwords do not match! " + password.trim() + " != " + passwordr.trim());
            return false;
        }
        return false;
    }
    public int loginUser(String uname,String pass){
        query="SELECT * FROM `useraccounts` WHERE userName = \""+uname+"\" AND password = \""+pass+"\";";
        try{
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                return set.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
