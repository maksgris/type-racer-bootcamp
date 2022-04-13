package com.example.typeracerbootcamp.SERVER;

import java.io.*;
import java.net.Socket;

public class ConnectedUser{
    private int uid;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String uname;

    ConnectedUser(Socket socket, int uid, String uname) throws IOException {
        this.socket = socket;
        this.uid = uid;
        this.uname = uname;
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("[DEBUG SERVER] Connected user " + uid + " " + uname + " is added to the pool!");
    }

    public int getUid() {
        return uid;
    }
    public Socket getSocket() {
        return socket;
    }
    public void setName(String uname){
        this.uname = uname;
    }

    public void matchStart() {
        try {
            bufferedWriter.write("START");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
