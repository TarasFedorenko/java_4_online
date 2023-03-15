package ua.com.alevel.controller;

import ua.com.alevel.persistence.entity.Actor;
import java.io.BufferedReader;
import java.io.IOException;


public interface ActorController extends BaseController<Actor> {
    void addActorToMovie(BufferedReader buff) throws IOException;

    void deleteActorFromMovie(BufferedReader buff) throws IOException;

    void getAllActorsByMovie(BufferedReader buff) throws IOException;

    void getNumberOfMoviesByActor();
}
