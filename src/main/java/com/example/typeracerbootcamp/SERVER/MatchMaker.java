package com.example.typeracerbootcamp.SERVER;

import java.util.ArrayList;

public class MatchMaker extends Thread {
    ConnectedUser temp;
    public static ArrayList<ConnectedUser> inSearch = new ArrayList<>();
    ArrayList<Match> matches = new ArrayList<>();
    public void newSearch(ConnectedUser user){
        try{
            inSearch.add(user);
            temp = inSearch.get(0);
        }catch (Exception e){
            System.out.println("[DEBUG MATCHMAKER] this is not cool man...\n" + e.getMessage());
        }
    }
    @Override
    public void run(){
        if(inSearch.size()<=1){
            System.out.println("[DEBUG MATCHMAKER] sleeping...");
        }
        else{
            for (ConnectedUser i : inSearch) {
                try {
                    if (temp != i) {
                        matches.add(new Match(temp, i));
                        inSearch.remove(temp);
                        inSearch.remove(i);
                    }
                } catch (Exception e) {
                }
                temp = i;
            }
        }
    }
}
