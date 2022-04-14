package com.example.typeracerbootcamp.SERVER;

import java.sql.*;

public class SQLController {

    private String url = "jdbc:mysql://localhost:3306/typeracerdb";
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
    public void populatedb(String[] words){
        System.out.println("[DEBUG SERVER SQL] populating server...");
        query="";
        for(String i : words) {
            System.out.println("[DEBUG SERVER SQL] unique word: " + i);
            try {
                if (!"null".equals(i)){
                    query = "INSERT INTO `matchcontent`(`word`, `difficulty`) VALUES (\"" + i + "\"," + i.length() + ")";
                    try {
                        System.out.println("[DEBUG SERVER SQL] database populated by " +statement.executeUpdate(query)+" new words.");
                    } catch (SQLException e) {
                        System.out.println("[DEBUG SERVER SQL] Population failed! + " + query);
                        e.printStackTrace();
                    }
                }
            } catch (NullPointerException e) {
                break;
            }
        }
        System.out.println("[DEBUG SERVER SQL] querry final result: \n" + query);
    }
    public String finduserbyid(int id) throws SQLException{
        String found;
        query="SELECT * FROM `useraccounts` WHERE id = " + id;
        ResultSet set = statement.executeQuery(query);
        while(set.next()){
            found = set.getString(2);
            System.out.println("[DEBUG SERVER SQL] found user " + found + " with id: " + id);
            return found;
        }
        return null;
    }
}
