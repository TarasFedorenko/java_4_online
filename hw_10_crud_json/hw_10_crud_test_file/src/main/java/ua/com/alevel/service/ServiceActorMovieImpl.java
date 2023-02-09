package ua.com.alevel.service;

import ua.com.alevel.dao.DAOActorMovie;
import ua.com.alevel.dao.DAOActorMovieImpl;
import ua.com.alevel.dto.ActorDTO;
import ua.com.alevel.dto.MovieDTO;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceActorMovieImpl implements ServiceActorMovie {

    private final DAOActorMovie daoActorMovie = new DAOActorMovieImpl();

    @Override
    public void addActor(ActorDTO actorDTO) {
        Actor actor = new Actor();
        actor.setName(actorDTO.name());
        actor.setSurname(actorDTO.surname());
        actor.setAge(actorDTO.age());
        if (!isActorExist(actor)) {
            if (actor.getName() != null && actor.getSurName() != null) {
                daoActorMovie.addActor(actor);
            }
        } else {
            System.out.println("This actor already exist");
        }
    }

    @Override
    public void addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.title());
        movie.setGenre(movieDTO.genre());
        movie.setReleaseYear(movieDTO.releaseYear());
        if (!isMovieExist(movie)) {
            if (movie.getGenre() != null) {
                daoActorMovie.addMovie(movie);
            }
        } else {
            System.out.println("This movie already exist");
        }
    }

    @Override
    public List<Actor> getAllActors() {
        return daoActorMovie.getAllActors();
    }

    @Override
    public List<Movie> getAllMovies() {
        return daoActorMovie.getAllMovies();
    }

    @Override
    public Actor getActor(String id) {
        return daoActorMovie.getActor(id).get();
    }

    @Override
    public Movie getMovie(String id) {
        return daoActorMovie.getMovie(id).get();
    }

    @Override
    public void deleteMovie(String id) {
        daoActorMovie.deleteMovie(id);
    }

    @Override
    public void deleteActor(String id) {
        daoActorMovie.deleteActor(id);
    }

    @Override
    public void addActorToMovie(String actorId, String movieId) {
        daoActorMovie.addActorToMovie(actorId, movieId);
    }

    @Override
    public void addMovieToActor(String actorId, String movieId) {
        daoActorMovie.addMovieToActor(actorId, movieId);
    }

    @Override
    public List<Actor> getAllActorsByMovie(String movieId) {
        List<Optional<Actor>> optList = daoActorMovie.getAllActorsByMovie(movieId);
        List<Actor> actorList = new ArrayList<>();
        for (Optional<Actor> actor : optList) {
            actorList.add(actor.get());
        }
        return actorList;
    }

    @Override
    public List<Movie> getAllMoviesByActor(String actorId) {
        List<Optional<Movie>> optList = daoActorMovie.getAllMoviesByActor(actorId);
        List<Movie> movieList = new ArrayList<>();
        for (Optional<Movie> movie : optList) {
            movieList.add(movie.get());
        }
        return movieList;
    }

    @Override
    public void deleteActorFromMovieList(String actorId, String movieId) {
        daoActorMovie.deleteActorFromMovieList(actorId, movieId);
    }

    @Override
    public void deleteMovieFromActorList(String movieId, String actorId) {
        daoActorMovie.deleteMovieFromActorList(movieId, actorId);
    }

    @Override
    public void updateActor(Actor actor) {
        daoActorMovie.updateActor(actor);
    }

    @Override
    public void updateMovie(Movie movie) {
        daoActorMovie.updateMovie(movie);
    }

    private boolean isMovieExist(Movie movie) {
        return daoActorMovie.isMovieExist(movie);
    }

    private boolean isActorExist(Actor actor) {
        return daoActorMovie.isActorExist(actor);
    }
}