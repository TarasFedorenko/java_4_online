package ua.com.alevel.utils;

import java.io.*;


public class CreateFileAndInit {

    public void createInitActor() throws IOException {
        File file = new File("actors.json");
        file.createNewFile();
        FileWriter fileWrite = new FileWriter("actors.json");
        fileWrite.write("{\"actors\":[]}");
        fileWrite.flush();
    }

    public void createInitMovie() throws IOException {
        File file = new File("movies.json");
        file.createNewFile();
        FileWriter fileWrite = new FileWriter("movies.json");
        fileWrite.write("{\"movies\":[]}");
        fileWrite.flush();
    }
}




