package ua.com.alevel.dao;

import ua.com.alevel.dto.ActorDTO;
import ua.com.alevel.dto.MovieDTO;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface DAOActorMovie {
    void addActor(Actor actor);

    void addMovie(Movie movie);

    List<Actor> getAllActors();

    List<Movie> getAllMovies();

    Optional<Actor> getActor(String id);

    Optional<Movie> getMovie(String id);

    void deleteMovie(String id);

    void deleteActor(String id);

    void addActorToMovie(String actorId, String movieId);

    void addMovieToActor(String actorId, String movieId);

    List<Optional<Actor>> getAllActorsByMovie(String movieId);

    List<Optional<Movie>> getAllMoviesByActor(String actorId);

    void deleteActorFromMovieList(String actorId, String movieId);

    void deleteMovieFromActorList(String actorId, String movieId);

    void updateActor(Actor actor);

    void updateMovie(Movie movie);

    boolean isMovieExist(Movie movie);

    boolean isActorExist(Actor actor);

}
