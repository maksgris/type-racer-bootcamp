package com.example.typeracerbootcamp.Links;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileLink {
    File file;
    Scanner scanner;
    String[] words;
    FileWriter fw;
    PrintWriter outpt;

    public FileLink(String path, boolean reads) {
        if (reads) {
            file = new File(path);
            try {
                scanner = new Scanner(file);
                System.out.println("[DEBUG FILE] Scanner successfully established, FileLink is active!");
            } catch (Exception e) {
                System.out.println("[DEBUG FILE] SCANNER FATAL ERROR.");
                System.out.println(e.getMessage());
            }
        } else {
            file = new File(path);
            try {
                fw = new FileWriter(file);
                outpt = new PrintWriter(fw);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void read() {
        words = new String[1024];
        int i = 0;
        try{
            while (scanner.hasNextLine()) {
                words[i] = scanner.nextLine();
                System.out.println("[DEBUG FILE] new word read: " + words[i]);
                i++;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String[] out() {
        System.out.println("[DEBUG FILE] words sent out!");
        return words;
    }

    public void write(String words) {
            outpt.println(words);
            outpt.flush();
            outpt.close();
    }
}
