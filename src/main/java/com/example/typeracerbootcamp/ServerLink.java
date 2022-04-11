package com.example.typeracerbootcamp;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerLink {
    private String ip = "localhost";
    private int port = 9999;
    private Socket socket;
    private String str;
    private OutputStreamWriter o;
    private PrintWriter out;
    ServerLink() throws IOException {
        System.out.println("[DEBUG] init serverlink...");
        socket = new Socket(ip,port);
        o = new OutputStreamWriter(socket.getOutputStream());
        out = new PrintWriter(o);
    }
    public void test(){
        System.out.println("[DEBUG] ping test init...");
        out.write("ping");
        out.flush();
        System.out.println("[DEBUG] ping sent!");
    }
}
