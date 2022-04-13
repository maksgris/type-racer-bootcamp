package com.example.typeracerbootcamp.Links;

import com.example.typeracerbootcamp.Source;

import java.io.*;
import java.net.Socket;

public class ServerLink {
    private String ip = "78.56.226.18";
    private int port = 1337;
    private Socket socket;
    private String str="ping";
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    public ServerLink() throws IOException {
        System.out.println("[DEBUG] init serverlink...");
        socket = new Socket(ip,port);
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public void test(){
        try{
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    public void regUser(String uname,String email, String pword,String pwordr){
        try{
            bufferedWriter.write("REGISTER");bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.write(uname);bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.write(email);bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.write(pword);bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.write(pwordr);bufferedWriter.newLine();
            bufferedWriter.flush();
            if(bufferedReader.readLine().equals("Success")){
                System.out.println("[DEBUG] Account created successfully");
            }
            else{
                System.out.println("[DEBUG] Account failed to create");
            }
        }catch (IOException e){
            System.out.println("[DEBUG] failed to register!");
        }
    }
    public String logUser(String uname, String pass){
        String response ="";
        try{
            bufferedWriter.write("LOGIN");bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("[DEBUG SERVERLINK] Action sent!");
            bufferedWriter.write(uname);bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("[DEBUG SERVERLINK] uname sent!");
            bufferedWriter.write(pass);bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("[DEBUG SERVERLINK] pass sent!");
            System.out.println("[DEBUG] LOGIN info submitted");
            response = bufferedReader.readLine();
            System.out.println("[DEBUG SERVERLINK] Server responded: " + response);
            if(response.equals("Success")){
                System.out.println("[DEBUG] Login successful!");
                return uname;
            }
            else{
                System.out.println("[DEBUG] Login failed!");
                return null;
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.println("[DEBUG SERVERLINK] FATAL END.");
        return null;
    }
    public void onlinePlayController(){
        String response;
        try{
        bufferedWriter.write("FINDMATCH");bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.write(Source.outNick());bufferedWriter.newLine();
        bufferedWriter.flush();
        response = bufferedReader.readLine();
            System.out.println("[DEBUG SERVERLINK] server responded: " + response);
        }catch (Exception e){
            System.out.println("[DEBUG SERVERLINK] it no worekrooney boi :(");
        }
    }
}
