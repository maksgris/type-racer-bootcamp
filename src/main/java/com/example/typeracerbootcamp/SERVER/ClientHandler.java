package com.example.typeracerbootcamp.SERVER;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientHandler extends Thread{
    static MatchMaker matchMaker = new MatchMaker();
    static SQLController sql = new SQLController();
    static ArrayList<ConnectedUser> users = new ArrayList<>();
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String action;
    boolean clientLoggedIn=false;
    String uname;
    int uid;
    ClientHandler(Socket userSocket){
        try{
            socket = userSocket;
            bufferedReader  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (Exception e){
            System.out.println("[DEBUG CLIENTHANDLER] failed to add client!\n" + e.getMessage());
        }
    }
    @Override
    public void run() {
        while(!clientLoggedIn && socket.isConnected()){
        System.out.println("[DEBUG SERVER] init unlogged switch! " + clientLoggedIn);
        try{
            String email;
            String pass;
            String rpass;
            System.out.println("[DEBUG SERVER] Listening for action...");
            action = bufferedReader.readLine();
            System.out.println("[DEBUG SERVER] action: " + action);
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
                        System.out.println("[DEBUG SERVER] Passing \"Success\" to client...");
                        bufferedWriter.write("Success");
                    }else{
                        System.out.println("[DEBUG SERVER] Registration process failed!");
                        bufferedWriter.write("Fail");
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    System.out.println("[DEBUG SERVER] Registration process finished!");
                    break;
                case "LOGIN":
                    System.out.println("[DEBUG SERVER] case LOGIN...");
                    uname = bufferedReader.readLine();
                    System.out.println("[DEBUG SERVER] uname: " + uname);
                    pass = bufferedReader.readLine();
                    System.out.println("[DEBUG SERVER] pass: " + pass);
                    uid=sql.loginUser(uname,pass);
                    if(uid>0){
                        System.out.println("[DEBUG SERVER] Login succesful... user id: " + uid);
                        System.out.println("[DEBUG SERVER] Passing \"Success\" to client...");
                        users.add(new ConnectedUser(socket,uid,uname));
                        bufferedWriter.write("Success");
                    }else{
                        System.out.println("[DEBUG SERVER] Login process failed!");
                        bufferedWriter.write("Fail");
                    }
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    System.out.println("[DEBUG SERVER] Login process finished!");
                    clientLoggedIn=true;
                    break;
                case "":
                    System.out.println("[DEBUG SERVER] case empty! skip step...");
                    break;
                default:
                    System.out.println("[DEBUG SERVER] INVALID ACTION<DEFAULT>: " + action);
                    break;
            }
        }catch (SocketException e){
            System.out.println("[DEBUG SERVER] socket disconnected");
            break;
        }catch (IOException e){
            System.out.println("[DEBUG SERVER] IOException: "+e.getMessage());
        }
        System.out.println("[DEBUG SERVER] while unlogged reset");
        }
        while(clientLoggedIn){
            try{
                System.out.println("[DEBUG SERVER] Listening for action from " + uname);
                action = bufferedReader.readLine();
                System.out.println("[DEBUG SERVER] action: " + action);
                switch (action) {
                    case "FINDMATCH":
                        System.out.println("[DEBUG SERVER] case FINDMATCH...");
                        uname = bufferedReader.readLine();
                        for (ConnectedUser user : users) {
                            try {
                                if (sql.finduserbyid(user.getUid()).equals(uname) && !MatchMaker.inSearch.contains(user)) {
                                    System.out.println("[DEBUG SERVER] Matchmaking uid hit: " + user.getUid() + " with name: " + uname);
                                    if(matchMaker.isAlive()) {
                                        matchMaker.wait(100);
                                        System.out.println("[DEBUG SERVER] stopping matchmaking");
                                    }
                                    matchMaker.newSearch(user);
                                    matchMaker.start();
                                    System.out.println("[DEBUG SERVER] Matchmaker made.");
                                    bufferedWriter.write("WAIT");bufferedWriter.newLine();
                                    bufferedWriter.flush();
                                    System.out.println("[DEBUG SERVER] wait sent!");
                                }else if(MatchMaker.inSearch.contains(user)){
                                    System.out.println("[DEBUG SERVER] Matchmaking uid already in queue: " + user.getUid() + " with name: " + uname);
                                    bufferedWriter.write("I SAID WAIT BLET");bufferedWriter.newLine();
                                    bufferedWriter.flush();
                                }
                                else {
                                    System.out.println("[DEBUG SERVER] Matchmaking uid miss: " + user.getUid() + " with name: " + uname);
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                        }
                        break;
                }
            }catch (Exception e){
                System.out.println("[DEBUG CLIENTHANDLER] FATAL ERROR.");
                try{
                    socket.close();
                    bufferedWriter.close();
                    bufferedReader.close();
                }catch (Exception ex){
                    System.out.println("[DEBUG CLIENTHANDLER] destructor error! " + ex.getMessage());
                }
                clientLoggedIn = false;
                break;
            }
            System.out.println("[DEBUG SERVER] while logged reset");
        }
    }
}
