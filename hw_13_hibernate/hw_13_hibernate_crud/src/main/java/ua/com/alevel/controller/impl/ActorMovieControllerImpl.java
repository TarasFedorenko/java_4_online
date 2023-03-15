package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.ActorController;
import ua.com.alevel.controller.ActorMovieController;
import ua.com.alevel.controller.MovieController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActorMovieControllerImpl implements ActorMovieController {
    ActorController actorController = new ActorControllerImpl();
    MovieController movieController = new MovieControllerImpl();

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
        System.out.println("11. Add actor to movie cast press << 11 >>");
        System.out.println("12. Delete actor from movie cast press << 12 >>");
        System.out.println("13. Find all actor in some movie press << 13 >>");
        System.out.println("14. Find all movie in actors portfolio press << 14 >>");
        System.out.println("15. Find number of movie  by actor press << 15 >>");
        System.out.println("16. Find number of actor by movie press << 16 >>");
        System.out.println("17. Shutdown application press << 17 >>");
        System.out.println("_______________________________________________________");
    }

    public void crud(BufferedReader buff, String select) throws IOException {
        switch (select) {
            case "1" -> actorController.create(buff);
            case "2" -> movieController.create(buff);
            case "3" -> actorController.getById(buff);
            case "4" -> movieController.getById(buff);
            case "5" -> actorController.getAll();
            case "6" -> movieController.getAll();
            case "7" -> actorController.update(buff);
            case "8" -> movieController.update(buff);
            case "9" -> actorController.delete(buff);
            case "10" -> movieController.delete(buff);
            case "11" -> actorController.addActorToMovie(buff);
            case "12" -> actorController.deleteActorFromMovie(buff);
            case "13" -> actorController.getAllActorsByMovie(buff);
            case "14" -> movieController.getAllMoviesByActor(buff);
            case "15" -> actorController.getNumberOfMoviesByActor();
            case "16" -> movieController.getNumberOfActorsByMovie();
            case "17" -> shutdown();
        }
        menu();
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }
}