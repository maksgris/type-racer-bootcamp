package com.example.typeracerbootcamp.SERVER;

import com.example.typeracerbootcamp.Links.FileLink;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
    static SQLController sql;
    static ArrayList<ConnectedUser> connected1 = new ArrayList<>();
    static ArrayList<ClientHandler> connected = new ArrayList<>();
    static MatchMaker matchMaker;
    static void populate(){
        FileLink file = new FileLink("D:/IntelliJ/type-racer-bootcamp-test/src/main/resources/words.txt",true);
        file.read();
        sql.populatedb(file.out());
    }
    public static void main(String[] args) throws IOException {
        matchMaker = new MatchMaker();
        sql = new SQLController();
        //populate();
        int uid;
        String str = "";
        ServerSocket serverSocket = new ServerSocket(1337);
        Socket socket;
        while(true) {
            System.out.println("[DEBUG SERVER] init server...");
            System.out.println("[DEBUG SERVER] waiting for client...");
            socket = serverSocket.accept();
            System.out.println("[DEBUG SERVER] client connected!");
            connected.add(new ClientHandler(socket));
            connected.get(connected.size()-1).start();
            socket=null;
        }
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        System.out.println("[DEBUG SERVER] buffered reader initialised...");
//    while(socket.isConnected()){
//        System.out.println("[DEBUG SERVER] init switch!");
//        try{
//            String uname;
//            String email;
//            String pass;
//            String rpass;
//            System.out.println("[DEBUG SERVER] Listening for action...");
//            action = bufferedReader.readLine();
//            System.out.println("[DEBUG SERVER] action: " + action);
//            switch (action){
//                case "REGISTER":
//                    uname = bufferedReader.readLine();
//                    System.out.println("[DEBUG SERVER] uname: " + uname);
//                    email = bufferedReader.readLine();
//                    System.out.println("[DEBUG SERVER] email: " + email);
//                    pass = bufferedReader.readLine();
//                    System.out.println("[DEBUG SERVER] pass: " + pass);
//                    rpass = bufferedReader.readLine();
//                    System.out.println("[DEBUG SERVER] rpass: " + rpass);
//                    if(sql.registerUser(uname,email,pass,rpass)){
//                        System.out.println("[DEBUG SERVER] Passing \"Success\" to client...");
//                        bufferedWriter.write("Success");
//                    }else{
//                        System.out.println("[DEBUG SERVER] Registration process failed!");
//                        bufferedWriter.write("Fail");
//                    }
//                    bufferedWriter.newLine();
//                    bufferedWriter.flush();
//                    System.out.println("[DEBUG SERVER] Registration process finished!");
//                    break;
//                case "LOGIN":
//                    System.out.println("[DEBUG SERVER] case LOGIN...");
//                    uname = bufferedReader.readLine();
//                    System.out.println("[DEBUG SERVER] uname: " + uname);
//                    pass = bufferedReader.readLine();
//                    System.out.println("[DEBUG SERVER] pass: " + pass);
//                    uid=sql.loginUser(uname,pass);
//                    if(uid>0){
//                        System.out.println("[DEBUG SERVER] Login succesful... user id: " + uid);
//                        System.out.println("[DEBUG SERVER] Passing \"Success\" to client...");
//                        connected.add(new ConnectedUser(uid,socket));
//                        bufferedWriter.write("Success");
//                    }else{
//                        System.out.println("[DEBUG SERVER] Login process failed!");
//                        bufferedWriter.write("Fail");
//                    }
//                    bufferedWriter.newLine();
//                    bufferedWriter.flush();
//                    System.out.println("[DEBUG SERVER] Login process finished!");
//                    break;
//                case "FINDMATCH":
//                    System.out.println("[DEBUG SERVER] case LOGIN...");
//                    uname = bufferedReader.readLine();
//                    for(ConnectedUser user : connected){
//                        try{
//                            if(sql.finduserbyid(user.getUid()).equals(uname)){
//                                System.out.println("[DEBUG SERVER] Matchmaking uid hit: " + user.getUid() + " with name: " + uname);
//                                matchMaker.newSearch(user);
//                                if(!matchMaker.isAlive()){
//                                    matchMaker.start();
//                                }
//                            }
//                            else{
//                                System.out.println("[DEBUG SERVER] Matchmaking uid miss: " + user.getUid() + " with name: " + uname);
//                            }
//                        }catch (Exception e){
//                            System.out.println(e.getMessage());
//                        }
//                    }
//                    break;
//                case "":
//                    System.out.println("[DEBUG SERVER] case empty! skip step...");
//                    break;
//                default:
//                    System.out.println("[DEBUG SERVER] INVALID ACTION<DEFAULT>: " + action);
//                    break;
//            }
//        }catch (SocketException e){
//            System.out.println("[DEBUG SERVER] socket disconnected");
//            break;
//        }
//        System.out.println("[DEBUG SERVER] while reset");
//        }
    }

}