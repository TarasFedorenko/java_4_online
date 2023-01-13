package ua.com.alevel.service;

import ua.com.alevel.dao.DAOActorMovie;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Movie;

import java.util.List;
import java.util.Optional;

public class ServiceActorMovie {

    DAOActorMovie daoActorMovie = new DAOActorMovie();

    public void addActor(Actor actor) {
        if (!isActorExist(actor)) {
            if (actor.getName() != null && actor.getSurName() != null) {
                daoActorMovie.addActor(actor);
            }
        } else {
            System.out.println("This actor already exist");
        }
    }

    public void addMovie(Movie movie) {
        if (!isMovieExist(movie)) {
            if (movie.getGenre() != null) {
                daoActorMovie.addMovie(movie);
            }
        } else {
            System.out.println("This movie already exist");
        }
    }

    public List<Actor> getAllActors() {
        return daoActorMovie.getAllActors();

    }

    public List<Movie> getAllMovies() {
        return daoActorMovie.getAllMovies();
    }

    public Actor getActor(String id) {
        return daoActorMovie.getActor(id);
    }

    public Movie getMovie(String id) {
        return daoActorMovie.getMovie(id);
    }

    public void deleteMovie(String id) {
        daoActorMovie.deleteMovie(id);
    }

    public void deleteActor(String id) {
        daoActorMovie.deleteActor(id);
    }

    public void addActorToMovie(String actorId, String movieId) {
        daoActorMovie.addActorToMovie(actorId, movieId);
    }

    public List<Optional<Actor>> getAllActorsByMovie(String movieId) {
        return daoActorMovie.getAllActorsByMovie(movieId);
    }

    public void addMovieToActor(String actorId, String movieId) {
        daoActorMovie.addMovieToActor(actorId, movieId);
    }

    public List<Optional<Movie>> getAllMoviesByActor(String actorId) {
        return daoActorMovie.getAllMoviesByActor(actorId);
    }

    public void deleteActorFromMovieList(String actorId, String movieId) {
        daoActorMovie.deleteActorFromMovieList(actorId, movieId);
    }

    public void deleteMovieFromActorList(String movieId, String actorId) {
        daoActorMovie.deleteMovieFromActorList(movieId, actorId);
    }

    public void updateActor(Actor actor) {
        daoActorMovie.updateActor(actor);
    }

    public void updateMovie(Movie movie) {
        daoActorMovie.updateMovie(movie);
    }

    public String generateActorID() {
        return daoActorMovie.generateActorID();
    }

    public String generateMovieID() {
        return daoActorMovie.generateMovieID();
    }

    public boolean isActorExist(Actor actor) {
        return daoActorMovie.isActorExist(actor);
    }

    public boolean isMovieExist(Movie movie) {
        return daoActorMovie.isMovieExist(movie);
    }
}