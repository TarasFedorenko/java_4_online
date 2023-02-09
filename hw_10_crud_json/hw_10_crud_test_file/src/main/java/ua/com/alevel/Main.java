package ua.com.alevel;

import ua.com.alevel.controller.ControllerActorMovie;
import ua.com.alevel.utils.CreateFileAndInit;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CreateFileAndInit cf = new CreateFileAndInit();
        cf.createInitActor();
        cf.createInitMovie();
        ControllerActorMovie uf = new ControllerActorMovie();
        uf.start();
    }
}