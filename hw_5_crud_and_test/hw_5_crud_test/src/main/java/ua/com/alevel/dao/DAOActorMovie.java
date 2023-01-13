package ua.com.alevel.dao;

import ua.com.alevel.db.DBStorage;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Movie;

import java.util.List;
import java.util.Optional;

public class DAOActorMovie {
    DBStorage dbStorage = DBStorage.getInstance();

    public void addActor(Actor actor) {
        dbStorage.addActor(actor);
    }

    public void addMovie(Movie movie) {
        dbStorage.addMovie(movie);
    }

    public List<Actor> getAllActors() {
        return dbStorage.getAllActors();

    }

    public List<Movie> getAllMovies() {
        return dbStorage.getAllMovies();
    }

    public Actor getActor(String id) {
        return dbStorage.getActor(id).get();
    }

    public Movie getMovie(String id) {
        return dbStorage.getMovie(id).get();
    }

    public void deleteMovie(String id) {
        dbStorage.deleteMovie(id);
    }

    public void deleteActor(String id) {
        dbStorage.deleteActor(id);
    }

    public void addActorToMovie(String actorId, String movieId) {
        dbStorage.addActorToMovie(actorId, movieId);
    }

    public List<Optional<Actor>> getAllActorsByMovie(String movieId) {
        return dbStorage.getAllActorsByMovie(movieId);
    }

    public void addMovieToActor(String actorId, String movieId) {
        dbStorage.addMovieToActor(actorId, movieId);
    }

    public List<Optional<Movie>> getAllMoviesByActor(String actorId) {
        return dbStorage.getAllMoviesByActor(actorId);
    }

    public void deleteActorFromMovieList(String actorId, String movieId) {
        dbStorage.deleteActorFromMovieList(actorId, movieId);
    }

    public void deleteMovieFromActorList(String movieId, String actorId) {
        dbStorage.deleteMovieFromActorList(movieId, actorId);
    }

    public void updateActor(Actor actor) {
        dbStorage.updateActor(actor);
    }

    public void updateMovie(Movie movie) {
        dbStorage.updateMovie(movie);
    }

    public String generateActorID() {
        return dbStorage.generateActorID();
    }

    public String generateMovieID() {
        return dbStorage.generateMovieID();
    }

    public boolean isMovieExist(Movie movie) {
        return dbStorage.isMovieExist(movie);
    }

    public boolean isActorExist(Actor actor) {
        return dbStorage.isActorExist(actor);
    }
}
