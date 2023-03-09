package ua.com.alevel;

import ua.com.alevel.controller.ActorMovieController;
import ua.com.alevel.controller.ActorMovieControllerImpl;

public class Main {
    public static void main(String[] args) {
        ActorMovieController actorMovieController = new ActorMovieControllerImpl();
        actorMovieController.start();
    }
}