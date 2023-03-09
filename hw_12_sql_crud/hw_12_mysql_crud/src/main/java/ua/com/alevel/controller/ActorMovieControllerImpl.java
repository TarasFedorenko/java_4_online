package ua.com.alevel.controller;

import ua.com.alevel.persistence.dto.ActorDTO;
import ua.com.alevel.persistence.dto.MovieGenreDTO;
import ua.com.alevel.persistence.entity.Actor;
import ua.com.alevel.persistence.entity.Movie;
import ua.com.alevel.persistence.type.MovieGenre;
import ua.com.alevel.service.ActorService;
import ua.com.alevel.service.MovieService;
import ua.com.alevel.service.impl.ActorServiceImpl;
import ua.com.alevel.service.impl.MovieServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class ActorMovieControllerImpl implements ActorMovieController {

    ActorService actorService = new ActorServiceImpl();
    MovieService movieService = new MovieServiceImpl();

    @Override
    public void start() {
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("*** MAIN MENU ***");
            String select;
            menu();
            while ((select = buff.readLine()) != null) {
                crud(buff, select);
            }
        } catch (IOException e) {
            System.out.println("IO problem " + e);
        }
    }

    public void menu() {
        System.out.println();
        System.out.println("1. Create actor press << 1 >>");
        System.out.println("2. Create movie press << 2 >>");
        System.out.println("3. Find some actor press << 3 >>");
        System.out.println("4. Find some movie press << 4 >>");
        System.out.println("5. Find all actors press << 5 >>");
        System.out.println("6. Find all movies press << 6 >>");
        System.out.println("7. Update actor press << 7 >>");
        System.out.println("8. Update movie press << 8 >>");
        System.out.println("9. Delete actor press << 9 >>");
        System.out.println("10. Delete movie press << 10 >>");
        System.out.println("11. Add actor to movie cast  press << 11 >>");
        System.out.println("12. Delete actor from movie cast press << 12 >>");
        System.out.println("13. Find all actor in some movie press << 13 >>");
        System.out.println("14. Find all movie in actors portfolio press << 14 >>");
        System.out.println("15. Find number of movie genre by actor << 15 >>");
        System.out.println("16. Find number of actor by movie genre press << 16 >>");
        System.out.println("17. Shutdown application press << 17 >>");
        System.out.println("_______________________________________________________");
    }

    public void crud(BufferedReader buff, String select) throws IOException {
        switch (select) {
            case "1" -> createActor(buff);
            case "2" -> createMovie(buff);
            case "3" -> getActor(buff);
            case "4" -> getMovie(buff);
            case "5" -> getAllActor();
            case "6" -> getAllMovie();
            case "7" -> updateActor(buff);
            case "8" -> updateMovie(buff);
            case "9" -> deleteActor(buff);
            case "10" -> deleteMovie(buff);
            case "11" -> addActorToMovie(buff);
            case "12" -> deleteActorFromMovie(buff);
            case "13" -> getAllActorsByMovie(buff);
            case "14" -> getAllMoviesByActor(buff);
            case "15" -> getNumberOfMoviesByActor();
            case "16" -> getNumberOfActorsByMovieGenre();
            case "17" -> shutdown();
        }
        menu();
    }

    private void createActor(BufferedReader buff) throws IOException {
        System.out.println("You want to create actor ?");
        System.out.println("Please enter name of actor ");
        String name = buff.readLine();
        checkNames(name);
        System.out.println("Please enter surname of actor");
        String surname = buff.readLine();
        checkNames(surname);
        System.out.println("Please enter age of actor");
        int age = Integer.parseInt(buff.readLine());
        checkAge(age);
        Actor actor = new Actor();
        actor.setName(name);
        actor.setSurname(surname);
        actor.setAge(age);
        actorService.createActor(actor);
        System.out.println("Actor " + actor + "was created!");
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

    private void createMovie(BufferedReader buff) throws IOException {
        System.out.println("You want to create movie ?");
        System.out.println("Please enter title of movie ");
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
        movieService.createMovie(movie);
        System.out.println("Movie " + movie + "was created!");
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

    private void getActor(BufferedReader buff) throws IOException {
        System.out.println("You want to find actor");
        System.out.println("Please enter actors id");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        Actor actor = actorService.getActor(id);
        System.out.println("Actor ->" + actor);
    }

    private void checkId(Long id) {
        if ((id < 0)) {
            throw new RuntimeException("Wrong id value!");
        }
    }

    private void getMovie(BufferedReader buff) throws IOException {
        System.out.println("You want to find movie");
        System.out.println("Please enter movies id");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        Movie movie = movieService.getMovie(id);
        System.out.println("Movie -> " + movie);
    }

    private void getAllActor() {
        System.out.println("You want to find all actors");
        Collection<Actor> actors = actorService.getAllActor();
        for (Actor act : actors) {
            System.out.println("Actors ->" + act);
        }
    }

    private void getAllMovie() {
        System.out.println("You want to find all movies");
        Collection<Movie> movies = movieService.getAllMovie();
        for (Movie mov : movies) {
            System.out.println("Movies ->" + mov);
        }
    }

    private void updateActor(BufferedReader buff) throws IOException {
        System.out.println("You want to change actors data");
        System.out.println("Please enter id actor who will changed");
        Long id = Long.parseLong(buff.readLine());
        System.out.println("Please enter new actors name");
        String name = buff.readLine();
        checkNames(name);
        System.out.println("Please enter new actors surname");
        String surname = buff.readLine();
        checkNames(surname);
        System.out.println("Please enter new actors age");
        int age = Integer.parseInt(buff.readLine());
        checkAge(age);
        Actor actor = new Actor();
        actor.setName(name);
        actor.setSurname(surname);
        actor.setAge(age);
        actorService.updateActor(id, actor);
        System.out.println("Actor with id ->" + id + "was updated");
    }

    private void updateMovie(BufferedReader buff) throws IOException {
        System.out.println("You want to change movies data");
        System.out.println("Please enter id movies that will changed");
        Long id = Long.parseLong(buff.readLine());
        System.out.println("Please enter new movies title");
        String title = buff.readLine();
        checkTitle(title);
        System.out.println("Please choose new movies genre");
        MovieGenre movieGenre = chooseGenre(buff);
        System.out.println("Please enter new movies release year");
        int releaseYear = Integer.parseInt(buff.readLine());
        checkReleaseYear(releaseYear);
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setMovieGenre(movieGenre);
        movie.setReleaseYear(releaseYear);
        movieService.updateMovie(id, movie);
        System.out.println("Movie with id ->" + id + "was updated");
    }

    private void deleteActor(BufferedReader buff) throws IOException {
        System.out.println("You want to delete actors data ");
        System.out.println("Enter actors id ");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        actorService.deleteActor(id);
        System.out.println("Actor with id -> " + id + " was deleted");
    }

    private void deleteMovie(BufferedReader buff) throws IOException {
        System.out.println("You want to delete movies data ");
        System.out.println("Enter movies id");
        Long id = Long.parseLong(buff.readLine());
        checkId(id);
        movieService.deleteMovie(id);
        System.out.println("Movie with id -> " + id + " was deleted");
    }

    private void addActorToMovie(BufferedReader buff) throws IOException {
        System.out.println("You want add actor to movie list");
        System.out.println("Please enter actor id");
        Long actorId = Long.parseLong(buff.readLine());
        checkId(actorId);
        System.out.println("Now enter movie id");
        Long movieId = Long.parseLong(buff.readLine());
        checkId(movieId);
        actorService.addActorToMovie(actorId, movieId);
        System.out.println("Actor was added");
    }

    private void deleteActorFromMovie(BufferedReader buff) throws IOException {
        System.out.println("You want remove actor from movie list");
        System.out.println("Please enter actor id");
        Long actorId = Long.parseLong(buff.readLine());
        checkId(actorId);
        System.out.println("Now enter movie id");
        Long movieId = Long.parseLong(buff.readLine());
        checkId(movieId);
        actorService.deleteActorFromMovie(actorId, movieId);
        System.out.println("Actor was removed");
    }

    private void getAllActorsByMovie(BufferedReader buff) throws IOException {
        System.out.println("You want find all actors by movie");
        System.out.println("Please enter movie id");
        String movieId = buff.readLine();
        checkId(Long.parseLong(movieId));
        Collection<Actor> actors = actorService.getAllActorsByMovie(Long.parseLong(movieId));
        for (Actor act : actors) {
            System.out.println("Actors ->" + act);
        }
    }

    private void getAllMoviesByActor(BufferedReader buff) throws IOException {
        System.out.println("You want find all movies by movie");
        System.out.println("Please enter actor id");
        String actorId = buff.readLine();
        checkId(Long.parseLong(actorId));
        Collection<Movie> movies = movieService.getAllMoviesByActor(Long.parseLong(actorId));
        for (Movie mov : movies) {
            System.out.println("Movies ->" + mov);
        }
    }

    private void getNumberOfMoviesByActor() {
        System.out.println("You want to know number of movies  in actors portfolio");
        Collection<ActorDTO> actorDTOS = actorService.getNumberOfMoviesByActor();
        for (ActorDTO actorsDTOS : actorDTOS) {
            System.out.println("ActorDTO ->" + actorsDTOS);
        }
    }

    private void getNumberOfActorsByMovieGenre() {
        System.out.println("You want to know number of actors who work in some genre");
        Collection<MovieGenreDTO> movieGenreDTOS = movieService.getNumberOfActorsByMovieGenre();
        for (MovieGenreDTO moviesDTOS : movieGenreDTOS) {
            System.out.println("MovieDTO ->" + moviesDTOS);
        }
    }

    private void shutdown() {
        System.exit(0);
    }
}

