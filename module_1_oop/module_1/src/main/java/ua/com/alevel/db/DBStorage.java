package ua.com.alevel.db;

import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Movie;

import java.util.UUID;

public class DBStorage {

    private static Actor[] actors = new Actor[5];
    private static Movie[] movies = new Movie[5];

    private static Actor[] actorTempArray;
    private static Movie[] movieTempArray;

    private DBStorage() {
    }

    public static void addActor(Actor actor) {
        actor.setId(generateActorID());
        for (int i = 0; i < actors.length; i++) {
            if (actors[i] == null) {
                actors[i] = actor;
                break;
            }
            if (actors[actors.length - 1] != null) {
                actorTempArray = new Actor[actors.length * 2];
                for (int j = 0; j < actors.length; j++) {
                    actorTempArray[j] = actors[j];
                }
                actors = actorTempArray;
            }

        }
    }

    //добавление актеров
    public static String generateActorID() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < actors.length; i++) {
            if (actors[i] != null) {
                if (actors[i].getId().equals(id)) {
                    return generateActorID();
                }
            }
        }
        return id;
    }

    public static void addMovie(Movie movie) {
        movie.setId(generateMovieID());
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] == null) {
                movies[i] = movie;
                break;
            }
            if (movies[movies.length - 1] != null) {
                movieTempArray = new Movie[movies.length * 2];
                for (int j = 0; j < movies.length; j++) {
                    movieTempArray[j] = movies[j];
                }
                movies = movieTempArray;
            }
        }
    }

    // добавление фильмов
    public static String generateMovieID() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                if (movies[i].getId().equals(id)) {
                    return generateMovieID();
                }
            }
        }
        return id;
    }

    public static Actor[] getAllActors() {
        return actors;
    }

    // получение всех актеров
    public static Movie[] getAllMovies() {
        return movies;
    }

    // получение всех фильмов
    public static Actor getActor(String id) {
        for (int i = 0; i < actors.length; i++) {
            if (actors[i] != null) {
                if (actors[i].getId().equals(id)) {
                    return actors[i];
                }
            }
        }
        return null;
    }
    //получение актера

    public static Movie getMovie(String id) {
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                if (movies[i].getId().equals(id)) {
                    return movies[i];
                }
            }
        }
        return null;
    }

    // получение фильма
    public static void deleteMovie(String id) {
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                if (movies[i].getId().equals(id)) {
                    movies[i] = null;
                    return;
                }
            }
        }
        System.out.println("Wrong ID");

    }

    // удаление фильма
    public static void deleteActor(String id) {
        for (int i = 0; i < actors.length; i++) {
            if (actors[i] != null) {
                if (actors[i].getId().equals(id)) {
                    actors[i] = null;
                    return;
                }
            }
        }
        System.out.println("Wrong ID");
    }
    // удаление актера

    public static void addActorToMovie(String actorId, String movieId) {
        Movie movie = getMovie(movieId);
        if (movie == null) {
            System.out.println("Wrong ID");
        } else {
            String[] actorsMovie = movie.getActorIdList();
            for (int i = 0; i < actorsMovie.length; i++) {
                if (actorsMovie[i] == null) {
                    actorsMovie[i] = actorId;
                    return;
                } else if (actorsMovie[i].equals(actorId)) {
                    System.out.println("This actor already in list");
                    return;
                }
            }
        }
    }

    // добавление актеров в фильм
    public static Actor[] getAllActorsByMovie(String movieId) {
        Movie movie = getMovie(movieId);
        if (movie == null) {
            System.out.println("Wrong ID");
        } else {
            String[] actorsMovie = movie.getActorIdList();
            Actor[] actorsList = new Actor[actorsMovie.length];
            for (String actorId : actorsMovie) {
                Actor actor = getActor(actorId);
                if (actor != null) {
                    for (int i = 0; i < actorsList.length; i++) {
                        if (actorsList[i] == null) {
                            actorsList[i] = actor;
                            break;
                        }
                    }
                }
            }
            return actorsList;
        }
        return null;
    }
    //получение актеров по выбраному фильму

    public static void addMovieToActor(String actorId, String movieId) {
        Actor actor = getActor(actorId);
        if (actor == null) {
            System.out.println("Wrong ID");
        } else {
            String[] moviesActor = actor.getMovieIdList();
            for (int i = 0; i < moviesActor.length; i++) {
                if (moviesActor[i] == null) {
                    moviesActor[i] = movieId;
                    return;
                } else if (moviesActor[i].equals(movieId)) {
                    System.out.println("This movie already in list");
                    return;
                }
            }
        }
    }

    // добавление фильма в фильмографию актера
    public static Movie[] getAllMoviesByActor(String actorId) {
        Actor actor = getActor(actorId);
        if (actor == null) {
            System.out.println("Wrong ID");
        } else {
            String[] moviesActor = actor.getMovieIdList();
            Movie[] moviesList = new Movie[moviesActor.length];
            for (String movieId : moviesActor) {
                Movie movie = getMovie(movieId);
                if (movie != null) {
                    for (int i = 0; i < moviesList.length; i++) {
                        if (moviesList[i] == null) {
                            moviesList[i] = movie;
                            break;
                        }
                    }
                }
            }
            return moviesList;
        }
        return null;
    }
    // получение фильмов из фильмографии актера

    public static void deleteActorFromMovieList(String actorId, String movieId) {
        Movie movie = getMovie(movieId);
        if (movie == null) {
            System.out.println("Wrong ID");
        } else {
            String[] actorsMovie = movie.getActorIdList();
            for (int i = 0; i < actorsMovie.length; i++) {
                if (actorsMovie[i] != null) {
                    if (actorsMovie[i].equals(actorId)) {
                        actorsMovie[i] = null;
                        return;
                    }
                }
            }
            System.out.println("Wrong ID");
        }

    }

    //удаление актера из каста фильма
    public static void deleteMovieFromActorList(String movieId, String actorId) {
        Actor actor = getActor(actorId);
        if (actor == null) {
            System.out.println("Wrong ID");
        } else {
            String[] movieActors = actor.getMovieIdList();
            for (int i = 0; i < movieActors.length; i++) {
                if (movieActors[i] != null) {
                    if (movieActors[i].equals(movieId)) {
                        movieActors[i] = null;
                        return;
                    }
                }
            }
            System.out.println("Wrong ID");
        }

    }
    // удаление фильма из фильмографии актера

}