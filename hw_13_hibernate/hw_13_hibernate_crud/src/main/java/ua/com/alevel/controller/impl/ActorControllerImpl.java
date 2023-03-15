package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.ActorController;
import ua.com.alevel.persistence.dto.ActorDTO;
import ua.com.alevel.persistence.entity.Actor;
import ua.com.alevel.persistence.entity.Movie;
import ua.com.alevel.service.ActorService;
import ua.com.alevel.service.MovieService;
import ua.com.alevel.service.impl.ActorServiceImpl;
import ua.com.alevel.service.impl.MovieServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;


public class ActorControllerImpl implements ActorController {
    ActorService actorService = new ActorServiceImpl();
    MovieService movieService = new MovieServiceImpl();

    @Override
    public void addActorToMovie(BufferedReader buff) throws IOException {
        System.out.println("You want to add actor`s profile to some movie");
        System.out.println("Enter actor`s id which you want to add");
        Long actorsId = Long.parseLong(buff.readLine());
        checkId(actorsId);
        System.out.println("Enter movie`s id ");
        Long moviesId = Long.parseLong(buff.readLine());
        checkId(moviesId);
        Movie movie = movieService.getById(moviesId);
        Actor actor = actorService.getById(actorsId);
        Set<Actor> actorsList = movie.getActors();
        actorsList.add(actor);
        movieService.update(movie);
    }

    @Override
    public void deleteActorFromMovie(BufferedReader buff) throws IOException {
        System.out.println("You want to remove actor from movie list ");
        System.out.println("Enter actor`s id number");
        Long actorId = Long.parseLong(buff.readLine());
        checkId(actorId);
        Actor actor = actorService.getById(actorId);
        System.out.println("Now enter movie`s id number");
        Long movieId = Long.parseLong(buff.readLine());
        checkId(movieId);
        Movie movie = movieService.getById(movieId);
        Collection<Actor> actors = movie.getActors();
        actors.remove(actor);
        movieService.update(movie);
    }

    @Override
    public void getAllActorsByMovie(BufferedReader buff) throws IOException {
        System.out.println("You want to find all actors by movie");
        System.out.println("Enter movie`s id ");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        Movie movie = movieService.getById(id);
        Collection<Actor> actors = movie.getActors();
        for (Actor actorList : actors) {
            System.out.println("Actor -> " + actorList);
        }
    }

    @Override
    public void getNumberOfMoviesByActor() {
        Collection<ActorDTO> actors = actorService.getNumberOfMoviesByActor();
        for (ActorDTO actorsList : actors) {
            System.out.println("Actor -> " + actorsList);
        }
    }

    @Override
    public void create(BufferedReader buff) throws IOException {
        System.out.println("Let`s create actor`s profile");
        System.out.println("Enter actor`s name");
        String name = buff.readLine();
        checkNames(name);
        System.out.println("Enter actor`s surname");
        String surname = buff.readLine();
        checkNames(surname);
        System.out.println("Enter actor`s age");
        int age = Integer.parseInt(buff.readLine());
        checkAge(age);
        Actor actor = new Actor();
        actor.setName(name);
        actor.setSurname(surname);
        actor.setAge(age);
        actorService.create(actor);
    }

    @Override
    public void getById(BufferedReader buff) throws IOException {
        System.out.println("Let`s find actors profile by id number");
        System.out.println("Enter actor`s id number");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        Actor actor = actorService.getById(id);
        System.out.println("Actor -> " + actor);
    }

    @Override
    public void getAll() {
        System.out.println("Let`s find all actors profiles");
        Collection<Actor> actors = actorService.getAll();
        for (Actor actorList : actors) {
            System.out.println("Actor -> " + actorList);
        }
    }

    @Override
    public void update(BufferedReader buff) throws IOException {
        System.out.println("Let`s change actor`s profile");
        System.out.println("Enter actor`s id  who will changed");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        Actor actor = actorService.getById(id);
        System.out.println("Enter actor`s new name");
        String name = buff.readLine();
        checkNames(name);
        System.out.println("Enter actor`s new surname");
        String surname = buff.readLine();
        checkNames(surname);
        System.out.println("Enter actor`s new age");
        int age = Integer.parseInt(buff.readLine());
        checkAge(age);
        actor.setName(name);
        actor.setSurname(surname);
        actor.setAge(age);
        actor.setCreated(new Date());
        actorService.update(actor);
    }

    @Override
    public void delete(BufferedReader buff) throws IOException {
        System.out.println("Let`s remove actor`s profile");
        System.out.println("Enter id actor`s id who will removed");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        Actor actor = actorService.getById(id);
        Collection<Movie> movies = movieService.getAll();
        for (Movie movieList : movies) {
            Collection<Actor> actors = movieList.getActors();
            for (Actor actorList : actors) {
                if (actorList.equals(actor)) {
                    actors.remove(actorList);
                }
            }
            movieService.update(movieList);
        }
        actorService.delete(actor);
    }

    private void checkNames(String name) {
        if (!name.matches("^[a-zA-Z]{3,20}$")) {
            throw new RuntimeException("Incorrect value!");
        }
    }

    private void checkAge(int age) {
        if ((age > 100) || (age < 0)) {
            throw new RuntimeException("Incorrect age!");
        }
    }

    private void checkId(Long id) {
        if ((id < 0)) {
            throw new RuntimeException("Wrong id value!");
        }
    }
}