package com.example.typeracerbootcamp.SERVER;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) throws IOException {
        String str = "";
        System.out.println("[DEBUG SERVER] init server...");
        ServerSocket serverSocket = new ServerSocket(1337);
        System.out.println("[DEBUG SERVER] waiting for client...");
        Socket socket = serverSocket.accept();

        System.out.println("[DEBUG SERVER] client connected!");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        System.out.println("[DEBUG SERVER] buffered reader initialised...");
    while(socket.isConnected()){
        System.out.println("[DEBUG SERVER] init switch!");
        String action;
        try{
            String uname;
            String email;
            String pass;
            String rpass;
            action = bufferedReader.readLine();
            System.out.println("[DEBUG SERVER] action: " + action);
            SQLController sql = new SQLController();
            switch (action){
                case "REGISTER":
                    uname = bufferedReader.readLine();
                    System.out.println("[DEBUG SERVER] uname: " + uname);
                    email = bufferedReader.readLine();
                    System.out.println("[DEBUG SERVER] email: " + email);
                    pass = bufferedReader.readLine();
                    System.out.println("[DEBUG SERVER] pass: " + pass);
                    rpass = bufferedReader.readLine();
                    System.out.println("[DEBUG SERVER] rpass: " + rpass);
                    if(sql.registerUser(uname,email,pass,rpass)){
                        bufferedWriter.write("Success");
                    }else{
                        bufferedWriter.write("Fail");
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    break;
                case "LOGIN":
                    System.out.println("[DEBUG SERVER] case LOGIN...");
                    uname = bufferedReader.readLine();
                    System.out.println("[DEBUG SERVER] uname: " + uname);
                    pass = bufferedReader.readLine();
                    System.out.println("[DEBUG SERVER] pass: " + pass);
                    int uid=sql.loginUser(uname,pass);
                    if(uid>0){
                        System.out.println("[DEBUG SERVER] Login succesful... user id: " + uid);
                        bufferedWriter.write("Success");
                    }else{
                        bufferedWriter.write("Fail");
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    break;
                default:
                    System.out.println("[DEBUG SERVER] INVALID ACTION: " + action);
                    break;
            }
        }catch (SocketException e){
            System.out.println("[DEBUG SERVER] socket disconnected");
            break;
        }
        }
    }
}
