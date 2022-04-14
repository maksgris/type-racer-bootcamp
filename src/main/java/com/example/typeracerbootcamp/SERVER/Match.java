package com.example.typeracerbootcamp.SERVER;

public class Match implements Runnable{
    ConnectedUser user1;
    ConnectedUser user2;
    Match(ConnectedUser user1, ConnectedUser user2){
        System.out.println("[DEBUG] Match constructor.");
        this.user1=user1;
        this.user2=user2;
    }

    @Override
    public void run() {
        user1.matchStart();
        user2.matchStart();
    }
}
