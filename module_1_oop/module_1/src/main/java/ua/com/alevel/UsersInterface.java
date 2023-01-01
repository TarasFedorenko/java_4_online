package ua.com.alevel;

import ua.com.alevel.dbStorage.DBStorage;
import ua.com.alevel.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

;

public class UsersInterface {
    public void start() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Main menu");
        String select;
        menu();
        while ((select = bf.readLine()) != null) {
            crud(bf, select);
        }
    }
    private void menu() {
        System.out.println();
        System.out.println("To add new actor press - 1");
        System.out.println("To add new movie press - 2");
        System.out.println("To add actor in movie list press - 3");
        System.out.println("To add movie in actor list press - 4");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("To find actors profile press - 5");
        System.out.println("To find movies profile press - 6");
        System.out.println("To find all actors profile press - 7");
        System.out.println("To find all movies profile press - 8");
        System.out.println("To find all actors in movie press - 9");
        System.out.println("To find all movies with given actor press - 10");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("To change actors profile press - 11");
        System.out.println("To change movies profile press - 12");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("To delete actors profile press - 13");
        System.out.println("To delete movies profile press - 14");
        System.out.println("To delete actor from movie list press - 15");
        System.out.println("To delete movie from actors list press - 16");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
    }
    private void crud(BufferedReader bf, String select) throws IOException {
        switch (select) {
            case "1":
                addNewActor(bf);
                break;
            case "2":
                addNewMovie(bf);
                break;
            case "3":
                addActorInMovie(bf);
                break;
            case "4":
                addMovieInActor(bf);
                break;
            case "5":
                findActor(bf);
                break;
            case "6":
                findMovie(bf);
                break;
            case "7":
                findAllActor();
                break;
            case "8":
                findAllMovie();
                break;
            case "9":
                findAllActorsInMovie(bf);
                break;
            case "10":
                findAllMoviesInActor(bf);
                break;
            case "11":
                changeActor(bf);
                break;
            case "12":
                changeMovie(bf);
                break;
            case "13":
                deleteProfileActor(bf);
                break;
            case "14":
                deleteProfileMovie(bf);
                break;
            case "15":
                deleteActorInMovie(bf);
                break;
            case "16":
                deleteMovieInActor(bf);
                break;
        }
        menu();
    }

    private void addNewActor(BufferedReader bf) throws IOException {
        System.out.println("You want to create profile of new actor");
        System.out.println("Please enter actors name");
        String name = bf.readLine();
        System.out.println("Please enter actors surname");
        String surname = bf.readLine();
        Actor actor = new Actor();
        actor.setName(name);
        actor.setSurname(surname);
        if (actor.getName()==null||actor.getSurName()==null){
            System.out.println("Incorrect data, please try again");
        } else{
        DBStorage.addActor(actor);}
    }
    private void addNewMovie(BufferedReader bf)throws IOException{
        System.out.println("You want to create profile of new movie");
        System.out.println("Please enter movies title");
        String title =bf.readLine();
        System.out.println("Please enter movies genre");
        String genre = bf.readLine();
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        if(movie.getGenre()==null){
            System.out.println("Incorrect data, please try again");
        } else{
        DBStorage.addMovie(movie);}

    }
    private void addActorInMovie(BufferedReader bf) throws IOException {
        System.out.println("You want add actor to some movie");
        System.out.println("Please enter actor ID");
        String actorID = bf.readLine();
        System.out.println("Please enter movie ID");
        String movieID = bf.readLine();
        DBStorage.addActorToMovie(actorID,movieID);
    }
    private void addMovieInActor(BufferedReader bf) throws IOException {
        System.out.println("You want add movie to some actor");
        System.out.println("Please enter actor ID");
        String actorID = bf.readLine();
        System.out.println("Please enter movie ID");
        String movieID = bf.readLine();
        DBStorage.addMovieToActor(actorID,movieID);}
    private void findActor(BufferedReader bf) throws IOException {
        System.out.println("You want to find actor");
        System.out.println("Please enter actor ID");
        String actorID = bf.readLine();
        Actor actor = DBStorage.getActor(actorID);
        if (actor == null) {
            System.out.println("Wrong ID");
        } else {
            System.out.println(actor);}

    }
    private void findMovie(BufferedReader bf) throws IOException {
        System.out.println("You want to find movie");
        System.out.println("Please enter movie ID");
        String movieID = bf.readLine();
        Movie movie = DBStorage.getMovie(movieID);
        if (movie == null) {
            System.out.println("Wrong ID");
        } else {
            System.out.println(movie);}
    }
    private void findAllActor( ){
        System.out.println("You want to find all actors");
        Actor[]actors = DBStorage.getAllActors();
        System.out.println(Arrays.toString(actors));

    }
    private void findAllMovie( ){
        System.out.println("You want to find all movies");
        Movie [] movies = DBStorage.getAllMovies();
        System.out.println(Arrays.toString(movies));
    }
    private void findAllActorsInMovie(BufferedReader bf) throws IOException {
        System.out.println("You want to find all actors in movie");
        System.out.println("Please enter movie ID");
        String movieID = bf.readLine();
        Actor[]actors = DBStorage.getAllActorsByMovie(movieID);
        System.out.println(Arrays.toString(actors));
    }
    private void findAllMoviesInActor(BufferedReader bf) throws IOException {
        System.out.println("You want to find all movie in actor");
        System.out.println("Please enter actor ID");
        String actorID = bf.readLine();
        Movie[]movies = DBStorage.getAllMoviesByActor(actorID);
        System.out.println(Arrays.toString(movies));
    }
    private void changeActor(BufferedReader bf) throws IOException {
        System.out.println("You want to change actors profile");
        System.out.println("Please enter actor ID");
        String actorID = bf.readLine();
        Actor actor = DBStorage.getActor(actorID);
        if (actor == null) {
            System.out.println("Wrong ID");
        } else {
            System.out.println("Enter new name of actor");
            String name = bf.readLine();
            actor.setName(name);
            System.out.println("Enter new surname of actor");
            String surname = bf.readLine();
            actor.setSurname(surname);
            System.out.println("Generate new ID number of actor");
            String newID = DBStorage.generateActorID();
            actor.setId(newID);
            System.out.println("New ID number is "+ newID);

        }
    }

    private void changeMovie(BufferedReader bf) throws IOException {
        System.out.println("You want to change actors profile");
        System.out.println("Please enter movie ID");
        String movieID = bf.readLine();
        Movie movie = DBStorage.getMovie(movieID);
        if (movie == null) {
            System.out.println("Wrong ID");
        } else {
            System.out.println("Enter new title of movie");
            String title = bf.readLine();
            movie.setTitle(title);
            System.out.println("Enter new genre of movie");
            String genre = bf.readLine();
            movie.setGenre(genre);
            System.out.println("Generate new ID number of movie");
            String newID = DBStorage.generateMovieID();
            movie.setId(newID);
            System.out.println("New ID number is "+ newID);

        }
    }
    private void deleteProfileActor(BufferedReader bf) throws IOException {
        System.out.println("You want to delete actors profile");
        System.out.println("Please enter actors ID");
        String actorsID = bf.readLine();
        DBStorage.deleteActor(actorsID);
    }
    private void deleteProfileMovie(BufferedReader bf) throws IOException {
        System.out.println("You want to delete movies profile");
        System.out.println("Please enter movie ID");
        String moviesID = bf.readLine();
        DBStorage.deleteMovie(moviesID);
    }
    private void deleteActorInMovie(BufferedReader bf) throws IOException {
        System.out.println("You want delete actor from some movie");
        System.out.println("Please enter actor ID");
        String actorID = bf.readLine();
        System.out.println("Please enter movie ID");
        String movieID = bf.readLine();
        DBStorage.deleteActorFromMovieList(actorID,movieID);}

    private void deleteMovieInActor(BufferedReader bf) throws IOException {
        System.out.println("You want delete movie from some actor");
        System.out.println("Please enter actor ID");
        String actorID = bf.readLine();
        System.out.println("Please enter movie ID");
        String movieID = bf.readLine();
        DBStorage.deleteMovieFromActorList(movieID,actorID);}

    private void quit() {
        System.exit(0);
    }

    }

