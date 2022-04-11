package com.example.typeracerbootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        System.out.println("[DEBUG SERVER] init server...");
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("[DEBUG SERVER] waiting for client...");
        Socket socket = serverSocket.accept();

        System.out.println("[DEBUG SERVER] client connected!");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("[DEBUG SERVER] buffered reader initialised...");
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        doSomething();
//        stopwatch.stop(); // optional
//
//        long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
    while(true){
        System.out.println("[DEBUG SERVER] listening for messages!");
        String str = bufferedReader.readLine();
        System.out.println("[DEBUG SERVER] message received: " + str);
        }
    }
}
