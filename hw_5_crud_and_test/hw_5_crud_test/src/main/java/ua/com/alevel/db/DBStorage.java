package ua.com.alevel.db;

import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Movie;

import java.util.*;


public class DBStorage {

    private List<Actor> actors = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    private static DBStorage instance;

    public static DBStorage getInstance() {
        if (instance == null) {
            instance = new DBStorage();
        }
        return instance;
    }

    private DBStorage() {
    }

    public void addActor(Actor actor) {
        actor.setId(generateActorID());
        actors.add(actor);
    }

    public String generateActorID() {
        String id = UUID.randomUUID().toString();
        if (actors.stream().anyMatch(actor -> actor.getId().equals(id))) {
            return generateActorID();
        }
        return id;
    }

    public void addMovie(Movie movie) {
        movie.setId(generateMovieID());
        movies.add(movie);
    }

    public String generateMovieID() {
        String id = UUID.randomUUID().toString();
        if (movies.stream().anyMatch(movie -> movie.getId().equals(id))) {
            return generateMovieID();
        }
        return id;
    }

    public List<Actor> getAllActors() {             // получение всех актеров
        return actors;
    }


    public List<Movie> getAllMovies() {             // получение всех фильмов
        return movies;
    }

    public Optional<Actor> getActor(String id) {
        return actors
                .stream()
                .filter(actor -> actor.getId().equals(id))
                .findFirst();
    }


    public Optional<Movie> getMovie(String id) {
        return movies
                .stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
    }


    public void deleteMovie(String id) {                          // удаление фильма
        movies.removeIf(movie -> movie.getId().equals(id));
    }


    public void deleteActor(String id) {                            // удаление актера
        actors.removeIf(actor -> actor.getId().equals(id));
    }


    public void addActorToMovie(String actorId, String movieId) {
        Optional<Movie> movie = getMovie(movieId);
        Set<String> actorsMovie = movie.get().getActorIdList();
        actorsMovie.add(actorId);
    }


    public List<Optional<Actor>> getAllActorsByMovie(String movieId) {
        try {
            Optional<Movie> movie = getMovie(movieId);
            Set<String> actorsMovie;
            actorsMovie = movie.get().getActorIdList();
            List<Optional<Actor>> actorsList = new ArrayList<>();
            for (String actorId : actorsMovie) {
                Optional<Actor> actor = getActor(actorId);
                actorsList.add(actor);
            }
            return actorsList;
        } catch (Exception e) {
            System.out.println("Wrong id");
        }
        return null;
    }


    public void addMovieToActor(String actorId, String movieId) {
        Optional<Actor> actor = getActor(actorId);
        Set<String> moviesActor = actor.get().getMovieIdList();
        moviesActor.add(movieId);
    }


    public List<Optional<Movie>> getAllMoviesByActor(String actorId) {
        try {
            Optional<Actor> actor = getActor(actorId);
            Set<String> moviesActor = actor.get().getMovieIdList();
            List<Optional<Movie>> moviesList = new ArrayList<>();
            for (String movieId : moviesActor) {
                Optional<Movie> movie = getMovie(movieId);
                moviesList.add(movie);
            }
            return moviesList;
        } catch (Exception e) {
            System.out.println("Wrong id");
        }
        return null;
    }

    public void deleteActorFromMovieList(String actorId, String movieId) {
        Optional<Movie> movie = getMovie(movieId);
        Set<String> actorsMovie = movie.get().getActorIdList();
        actorsMovie.remove(actorId);

    }


    public void deleteMovieFromActorList(String movieId, String actorId) {
        Optional<Actor> actor = getActor(actorId);
        Set<String> movieActors = actor.get().getMovieIdList();
        movieActors.remove(movieId);
    }

    public void updateActor(Actor actor) {
        Optional<Actor> optionalActor = getActor(actor.getId());
        if (optionalActor.isPresent()) {
            Actor current = optionalActor.get();
            current = actor;
        }
    }

    public void updateMovie(Movie movie) {
        Optional<Movie> optionalMovie = getMovie(movie.getId());
        if (optionalMovie.isPresent()) {
            Movie current = optionalMovie.get();
            current = movie;
        }
    }

    public boolean isNameActorExist(Actor actor) {
        for (Actor actorsName : actors) {
            if (actorsName.getName().equals(actor.getName())) {
                return true;
            }
        }
        return false;

    }

    public boolean isSurnameActorExist(Actor actor) {
        for (Actor actorsSurname : actors) {
            if (actorsSurname.getSurName().equals(actor.getSurName())) {
                return true;
            }
        }
        return false;

    }


    public boolean isActorExist(Actor actor) {
        if (isNameActorExist(actor) && isSurnameActorExist(actor) == true) {
            return true;
        }
        return false;
    }

    public boolean isTitleMovieExist(Movie movie) {
        for (Movie moviesTitle : movies) {
            if (moviesTitle.getTitle().equals(movie.getTitle())) {
                return true;
            }
        }
        return false;

    }

    public boolean isGenreMovieExist(Movie movie) {
        for (Movie moviesGenre : movies) {
            if (moviesGenre.getGenre().equals(movie.getGenre())) {
                return true;
            }
        }
        return false;

    }


    public boolean isMovieExist(Movie movie) {
        if (isTitleMovieExist(movie) && isGenreMovieExist(movie) == true) {
            return true;
        }
        return false;
    }
}