package ua.com.alevel;

import java.io.IOException;

public class FBIagentMain {
    public static void main(String[] args) throws IOException {

        FBIagentInterface fbi = new FBIagentInterface();
        fbi.start();

    }
}
