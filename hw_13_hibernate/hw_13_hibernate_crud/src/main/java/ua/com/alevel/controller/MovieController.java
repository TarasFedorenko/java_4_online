package ua.com.alevel.controller;

import ua.com.alevel.persistence.entity.Movie;
import java.io.BufferedReader;
import java.io.IOException;


public interface MovieController extends BaseController<Movie> {
    void getAllMoviesByActor(BufferedReader buff) throws IOException;

    void getNumberOfActorsByMovie();
}
