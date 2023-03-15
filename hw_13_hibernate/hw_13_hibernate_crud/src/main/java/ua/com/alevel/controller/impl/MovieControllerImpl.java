package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.MovieController;
import ua.com.alevel.persistence.dto.MovieDTO;
import ua.com.alevel.persistence.entity.Actor;
import ua.com.alevel.persistence.entity.Movie;
import ua.com.alevel.persistence.type.MovieGenre;
import ua.com.alevel.service.ActorService;
import ua.com.alevel.service.MovieService;
import ua.com.alevel.service.impl.ActorServiceImpl;
import ua.com.alevel.service.impl.MovieServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

public class MovieControllerImpl implements MovieController {
    MovieService movieService = new MovieServiceImpl();
    ActorService actorService = new ActorServiceImpl();

    @Override
    public void getAllMoviesByActor(BufferedReader buff) throws IOException {
        System.out.println("You want to find all movies by actor");
        System.out.println("Enter actor`s id ");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        Actor actor = actorService.getById(id);
        Collection<Movie> movies = actor.getMovies();
        for (Movie movieList : movies) {
            System.out.println("Movie -> " + movieList);
        }
    }

    @Override
    public void getNumberOfActorsByMovie() {
        Collection<MovieDTO> movies = movieService.getNumberOfActorsByMovie();
        for (MovieDTO moviesList : movies) {
            System.out.println("Movie -> " + moviesList);
        }
    }

    @Override
    public void create(BufferedReader buff) throws IOException {
        System.out.println("Let`s create movie`s profile");
        System.out.println("Enter movie`s title");
        String title = buff.readLine();
        checkTitle(title);
        System.out.println("Please choose genre of actor");
        MovieGenre movieGenre = chooseGenre(buff);
        System.out.println("Please enter release year of actor");
        int releaseYear = Integer.parseInt(buff.readLine());
        checkReleaseYear(releaseYear);
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setMovieGenre(movieGenre);
        movie.setReleaseYear(releaseYear);
        movieService.create(movie);
    }

    @Override
    public void getById(BufferedReader buff) throws IOException {
        System.out.println("Let`s find movies data by id number");
        System.out.println("Enter movie`s id number");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        Movie movie = movieService.getById(id);
        System.out.println("Movie -> " + movie);
    }

    @Override
    public void getAll() {
        System.out.println("You want to find all movies");
        Collection<Movie> movies = movieService.getAll();
        for (Movie mov : movies) {
            System.out.println("Movies ->" + mov);
        }
    }

    @Override
    public void update(BufferedReader buff) throws IOException {
        System.out.println("You want to change movies data");
        System.out.println("Enter movie`s id that will changed");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        Movie movie = movieService.getById(id);
        System.out.println("Please enter movie`s new  title");
        String title = buff.readLine();
        checkTitle(title);
        System.out.println("Please choose movie`s new genre");
        MovieGenre movieGenre = chooseGenre(buff);
        System.out.println("Please enter new movies release year");
        int releaseYear = Integer.parseInt(buff.readLine());
        checkReleaseYear(releaseYear);
        movie.setTitle(title);
        movie.setMovieGenre(movieGenre);
        movie.setReleaseYear(releaseYear);
        movieService.update(movie);
    }


    @Override
    public void delete(BufferedReader buff) throws IOException {
        System.out.println("You want to delete movies data ");
        System.out.println("Enter movies id");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        Movie movie = movieService.getById(id);
        Collection<Actor> actors = actorService.getAll();
        for (Actor actorList : actors) {
            Collection<Movie> movies = actorList.getMovies();
            for (Movie movieList : movies) {
                if (movieList.equals(movie)) {
                    movies.remove(movieList);
                }
            }
            actorService.update(actorList);
        }
        movieService.delete(movie);
    }


    private void checkTitle(String title) {
        if (!title.matches("^[\\w\\s,.:;'\"()!?-]+$")) {
            throw new RuntimeException("Incorrect title!");
        }
    }

    private void checkReleaseYear(int releaseYear) {
        if ((releaseYear < 1895) || (releaseYear > 2023)) {
            throw new RuntimeException("Wrong date of release!");
        }
    }

    private void menuGenre() {
        System.out.println("COMEDY    press 1 ");
        System.out.println("ACTION    press 2 ");
        System.out.println("FANTASY   press 3 ");
        System.out.println("DRAMA     press 4 ");
        System.out.println("SCI_FI    press 5 ");
    }

    private MovieGenre chooseGenre(BufferedReader buff) throws IOException {
        menuGenre();
        String choose = buff.readLine();
        switch (choose) {
            case "1" -> {
                return MovieGenre.COMEDY;
            }
            case "2" -> {
                return MovieGenre.ACTION;
            }
            case "3" -> {
                return MovieGenre.FANTASY;
            }
            case "4" -> {
                return MovieGenre.DRAMA;
            }
            case "5" -> {
                return MovieGenre.SCI_FI;
            }
        }
        return null;
    }

    private void checkId(Long id) {
        if ((id < 0)) {
            throw new RuntimeException("Wrong id value!");
        }
    }
}
