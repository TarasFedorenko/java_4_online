package ua.com.alevel.dao;

import com.google.gson.Gson;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DAOActorMovieImpl implements DAOActorMovie {

    @Override
    public void addActor(Actor actor) {
        actor.setId(UUID.randomUUID().toString());
        Gson gson = new Gson();
        try {
            ActorsContainer actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
            List<Actor> actors = actorsContainer.getActors();
            actors.add(actor);
            String json = gson.toJson(actorsContainer);
            FileWriter fileWriter = new FileWriter("actors.json");
            fileWriter.write(json);
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addMovie(Movie movie) {
        movie.setId(UUID.randomUUID().toString());
        Gson gson = new Gson();
        try {
            MoviesContainer moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
            List<Movie> movies = moviesContainer.getMovies();
            movies.add(movie);
            String json = gson.toJson(moviesContainer);
            FileWriter fileWrite = new FileWriter("movies.json");
            fileWrite.write(json);
            fileWrite.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Actor> getAllActors() {
        Gson gson = new Gson();
        try {
            ActorsContainer actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
            return actorsContainer.getActors();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        Gson gson = new Gson();
        try {
            MoviesContainer moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
            return moviesContainer.getMovies();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Actor> getActor(String id) {
        Gson gson = new Gson();
        ActorsContainer actorsContainer = null;
        try {
            actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
            return actorsContainer.getActors()
                    .stream()
                    .filter(actor -> actor.getId().equals(id))
                    .findFirst();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Movie> getMovie(String id) {
        Gson gson = new Gson();
        MoviesContainer moviesContainer = null;
        try {
            moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
            return moviesContainer.getMovies()
                    .stream()
                    .filter(movie -> movie.getId().equals(id))
                    .findFirst();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMovie(String id) {
        Gson gson = new Gson();
        MoviesContainer moviesContainer = null;
        try {
            moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
            moviesContainer.getMovies()
                    .removeIf(movie -> movie.getId().equals(id));
            String json = gson.toJson(moviesContainer);
            FileWriter fileWrite = null;
            try {
                fileWrite = new FileWriter("movies.json");
                fileWrite.write(json);
                fileWrite.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteActor(String id) {
        Gson gson = new Gson();
        ActorsContainer actorsContainer = null;
        try {
            actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
            actorsContainer.getActors()
                    .removeIf(actor -> actor.getId().equals(id));
            String json = gson.toJson(actorsContainer);
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("actors.json");
                fileWriter.write(json);
                fileWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addActorToMovie(String actorId, String movieId) {
        Gson gson = new Gson();
        MoviesContainer moviesContainer = null;
        try {
            moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
            List<Movie> movies = moviesContainer.getMovies();
            for (Movie mov : movies) {
                if (mov.getId().equals(movieId)) {
                    mov.getActorIdList().add(actorId);
                }
            }
            String json = gson.toJson(moviesContainer);
            FileWriter fileWrite = null;
            try {
                fileWrite = new FileWriter("movies.json");
                fileWrite.write(json);
                fileWrite.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addMovieToActor(String actorId, String movieId) {
        Gson gson = new Gson();
        ActorsContainer actorsContainer = null;
        try {
            actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
            List<Actor> actors = actorsContainer.getActors();
            for (Actor act : actors) {
                if (act.getId().equals(actorId)) {
                    act.getMovieIdList().add(movieId);
                }
            }
            String json = gson.toJson(actorsContainer);
            FileWriter fileWrite = null;
            try {
                fileWrite = new FileWriter("actors.json");
                fileWrite.write(json);
                fileWrite.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Optional<Actor>> getAllActorsByMovie(String movieId) {
        Gson gson = new Gson();
        MoviesContainer moviesContainer;
        Set<String> setActor = new HashSet<>();
        List<Optional<Actor>> newList = new ArrayList<>();
        try {
            moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
            List<Movie> movies = moviesContainer.getMovies();
            for (Movie mov : movies) {
                if (mov.getId().equals(movieId)) {
                    setActor = mov.getActorIdList();
                }
            }
            for (String setId : setActor) {
                newList.add(getActor(setId));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return newList;
    }

    @Override
    public List<Optional<Movie>> getAllMoviesByActor(String actorId) {
        Gson gson = new Gson();
        ActorsContainer actorsContainer;
        Set<String> setMovie = new HashSet<>();
        List<Optional<Movie>> newList = new ArrayList<>();
        try {
            actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
            List<Actor> actors = actorsContainer.getActors();
            for (Actor act : actors) {
                if (act.getId().equals(actorId)) {
                    setMovie = act.getMovieIdList();
                }
            }
            for (String setId : setMovie) {
                newList.add(getMovie(setId));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return newList;
    }

    @Override
    public void deleteActorFromMovieList(String actorId, String movieId) {
        Gson gson = new Gson();
        MoviesContainer moviesContainer;
        Set<String> setActor = new HashSet<>();
        try {
            moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
            List<Movie> movies = moviesContainer.getMovies();
            for (Movie mov : movies) {
                if (mov.getId().equals(movieId)) {
                    setActor = mov.getActorIdList();
                    for (String idList : setActor) {
                        if (idList.equals(actorId)) {
                            setActor.remove(idList);
                        }
                    }
                }
            }
            String json = gson.toJson(moviesContainer);
            FileWriter fileWrite = null;
            try {
                fileWrite = new FileWriter("movies.json");
                fileWrite.write(json);
                fileWrite.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMovieFromActorList(String movieId, String actorId) {
        Gson gson = new Gson();
        ActorsContainer actorsContainer;
        Set<String> setMovie = new HashSet<>();
        try {
            actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
            List<Actor> actors = actorsContainer.getActors();
            for (Actor act : actors) {
                if (act.getId().equals(actorId)) {
                    setMovie = act.getMovieIdList();
                    for (String idList : setMovie) {
                        if (idList.equals(movieId)) {
                            setMovie.remove(idList);
                        }
                    }
                }
            }
            String json = gson.toJson(actorsContainer);
            FileWriter fileWrite = null;
            try {
                fileWrite = new FileWriter("actors.json");
                fileWrite.write(json);
                fileWrite.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateActor(Actor actor) {
        Gson gson = new Gson();
        ActorsContainer actorsContainer;
        try {
            actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
            List<Actor> actors = actorsContainer.getActors();
            for (int i = 0; i < actors.size(); i++) {
                if (actors.get(i).getId().equals(actor.getId())) {
                    actors.set(i, actor);
                }
            }
            String json = gson.toJson(actorsContainer);
            FileWriter fileWrite;
            try {
                fileWrite = new FileWriter("actors.json");
                fileWrite.write(json);
                fileWrite.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateMovie(Movie movie) {
        Gson gson = new Gson();
        MoviesContainer moviesContainer;
        try {
            moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
            List<Movie> movies = moviesContainer.getMovies();
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getId().equals(movie.getId())) {
                    movies.set(i, movie);
                }
            }
            String json = gson.toJson(moviesContainer);
            FileWriter fileWrite;
            try {
                fileWrite = new FileWriter("movies.json");
                fileWrite.write(json);
                fileWrite.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean isNameActorExist(Actor actor) throws FileNotFoundException {
        Gson gson = new Gson();
        ActorsContainer actorsContainer;
        actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
        List<Actor> actors = actorsContainer.getActors();
        for (Actor actorsName : actors) {
            if (actorsName.getName().equals(actor.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isSurnameActorExist(Actor actor) throws FileNotFoundException {
        Gson gson = new Gson();
        ActorsContainer actorsContainer;
        actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
        List<Actor> actors = actorsContainer.getActors();
        for (Actor actorsSurname : actors) {
            if (actorsSurname.getSurName().equals(actor.getSurName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isAgeActorExist(Actor actor) throws FileNotFoundException {
        Gson gson = new Gson();
        ActorsContainer actorsContainer;
        actorsContainer = gson.fromJson(new FileReader("actors.json"), ActorsContainer.class);
        List<Actor> actors = actorsContainer.getActors();
        for (Actor actorsAge : actors) {
            if (actorsAge.getAge().equals(actor.getAge())) {
                return true;
            }
        }
        return false;
    }


    public boolean isActorExist(Actor actor) {
        try {
            if (isNameActorExist(actor) && isSurnameActorExist(actor) && isAgeActorExist(actor) == true) {
                return true;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private boolean isTitleMovieExist(Movie movie) throws FileNotFoundException {
        Gson gson = new Gson();
        MoviesContainer moviesContainer;
        moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
        List<Movie> movies = moviesContainer.getMovies();
        for (Movie moviesTitle : movies) {
            if (moviesTitle.getTitle().equals(movie.getTitle())) {
                return true;
            }
        }
        return false;

    }

    private boolean isGenreMovieExist(Movie movie) throws FileNotFoundException {
        Gson gson = new Gson();
        MoviesContainer moviesContainer;
        moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
        List<Movie> movies = moviesContainer.getMovies();
        for (Movie moviesGenre : movies) {
            if (moviesGenre.getGenre().equals(movie.getGenre())) {
                return true;
            }
        }
        return false;

    }

    private boolean isReleaseYearExist(Movie movie) throws FileNotFoundException {
        Gson gson = new Gson();
        MoviesContainer moviesContainer;
        moviesContainer = gson.fromJson(new FileReader("movies.json"), MoviesContainer.class);
        List<Movie> movies = moviesContainer.getMovies();
        for (Movie moviesYear : movies) {
            if (moviesYear.getReleaseYear().equals(movie.getReleaseYear())) {
                return true;
            }
        }
        return false;

    }

    public boolean isMovieExist(Movie movie) {
        try {
            if (isTitleMovieExist(movie) && isGenreMovieExist(movie) && isReleaseYearExist(movie) == true) {
                return true;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private static class ActorsContainer {
        private List<Actor> actors;

        public List<Actor> getActors() {
            return actors;
        }

        public void setActors(List<Actor> actors) {
            this.actors = actors;
        }
    }

    private static class MoviesContainer {
        private List<Movie> movies;

        public List<Movie> getMovies() {
            return movies;
        }

        public void setMovies(List<Movie> movies) {
            this.movies = movies;
        }
    }

}
