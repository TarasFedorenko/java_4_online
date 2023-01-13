package ua.com.alevel;

import ua.com.alevel.controller.ControllerActorMovie;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ControllerActorMovie uf = new ControllerActorMovie();
        uf.start();
    }
}