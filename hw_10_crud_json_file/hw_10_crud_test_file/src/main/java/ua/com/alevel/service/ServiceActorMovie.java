package ua.com.alevel.service;

import ua.com.alevel.dto.ActorDTO;
import ua.com.alevel.dto.MovieDTO;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Movie;

import java.util.List;


public interface ServiceActorMovie {
    void addActor(ActorDTO actorDTO);

    void addMovie(MovieDTO movieDTO);

    List<Actor> getAllActors();

    List<Movie> getAllMovies();

    Actor getActor(String id);

    Movie getMovie(String id);

    void deleteMovie(String id);

    void deleteActor(String id);

    void addActorToMovie(String actorId, String movieId);

    void addMovieToActor(String actorId, String movieId);

    List<Actor> getAllActorsByMovie(String movieId);

    List<Movie> getAllMoviesByActor(String actorId);

    void deleteActorFromMovieList(String actorId, String movieId);

    void deleteMovieFromActorList(String actorId, String movieId);

    void updateActor(Actor actor);

    void updateMovie(Movie movie);

}
