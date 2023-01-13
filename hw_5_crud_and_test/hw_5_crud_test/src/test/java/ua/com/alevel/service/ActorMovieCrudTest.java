package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Movie;

import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActorMovieCrudTest {

    private static final ServiceActorMovie serviceActorMovie = new ServiceActorMovie();
    private static final String NAME = "testName";
    private static final String SURNAME = "testSurname";
    private static final String TITLE = "testTitle";
    private static final String GENRE = "testGenre";
    private static final String NEWNAME = "newName";
    private static final String NEWSURNAME = "newSurname";
    private static final String NEWTITLE = "newTitle";
    private static final String NEWGENRE = "newGenre";
    private static final String WRONGNAME = "petya 349";
    private static final String WRONGSURNAME = "petrenko 349";
    private static final String WRONGGENRE = "horror 349";
    private static final int MOVIESIZE = 10;
    private static final int ACTORSIZE = 10;

    @BeforeAll
    public static void setUpActor() {
        for (int i = 0; i < ACTORSIZE; i++) {
            Actor actor = generateActor(i);
            serviceActorMovie.addActor(actor);
        }
    }

    @BeforeAll
    public static void setUpMovie() {
        for (int i = 0; i < MOVIESIZE; i++) {
            Movie movie = generateMovie(i);
            serviceActorMovie.addMovie(movie);
        }
    }

    @Test
    @Order(1)
    public void checkSizeActor() {
        Assertions.assertEquals(serviceActorMovie.getAllActors().size(), ACTORSIZE);
    }

    @Test
    @Order(2)
    public void checkSizeMovie() {
        Assertions.assertEquals(serviceActorMovie.getAllMovies().size(), MOVIESIZE);
    }

    @Test
    @Order(3)
    public void addActorTest() {
        Actor actor = generateActor(ACTORSIZE + 1);
        serviceActorMovie.addActor(actor);
        Assertions.assertEquals(serviceActorMovie.getAllActors().size(), ACTORSIZE + 1);
    }

    @Test
    @Order(4)
    public void addMovieTest() {
        Movie movie = generateMovie(MOVIESIZE + 1);
        serviceActorMovie.addMovie(movie);
        Assertions.assertEquals(serviceActorMovie.getAllMovies().size(), MOVIESIZE + 1);
    }

    @Test
    @Order(5)
    public void getActorTest() {
        Actor actor = serviceActorMovie.getAllActors().get(0);
        Assertions.assertEquals(serviceActorMovie.getActor(actor.getId()), actor);
    }

    @Test
    @Order(6)
    public void getMovieTest() {
        Movie movie = serviceActorMovie.getAllMovies().get(0);
        Assertions.assertEquals(serviceActorMovie.getMovie(movie.getId()), movie);
    }

    @Test
    @Order(7)
    public void deleteActorTest() {
        Actor actor = serviceActorMovie.getAllActors().get(0);
        serviceActorMovie.deleteActor(actor.getId());
        Assertions.assertEquals(serviceActorMovie.getAllActors().size(), ACTORSIZE);
    }

    @Test
    @Order(8)
    public void deleteMovieTest() {
        Movie movie = serviceActorMovie.getAllMovies().get(0);
        serviceActorMovie.deleteMovie(movie.getId());
        Assertions.assertEquals(serviceActorMovie.getAllMovies().size(), MOVIESIZE);
    }

    @Test
    @Order(9)
    public void updateActorTest() {
        Actor actor = serviceActorMovie.getAllActors().get(0);
        actor.setName(NEWNAME);
        actor.setSurname(NEWSURNAME);
        serviceActorMovie.updateActor(actor);
        Assertions.assertNotEquals(serviceActorMovie.getAllActors().get(0).getName(), NAME);
        Assertions.assertNotEquals(serviceActorMovie.getAllActors().get(0).getSurName(), SURNAME);
    }

    @Test
    @Order(10)
    public void updateMovieTest() {
        Movie movie = serviceActorMovie.getAllMovies().get(0);
        movie.setTitle(NEWTITLE);
        movie.setGenre(NEWGENRE);
        serviceActorMovie.updateMovie(movie);
        Assertions.assertNotEquals(serviceActorMovie.getAllMovies().get(0).getTitle(), TITLE);
        Assertions.assertNotEquals(serviceActorMovie.getAllMovies().get(0).getGenre(), GENRE);
    }

    @Test
    @Order(11)
    public void addActorToMovieTest() {

        Movie movie = serviceActorMovie.getAllMovies().get(0);
        Actor actor = serviceActorMovie.getAllActors().get(0);
        serviceActorMovie.addActorToMovie(actor.getId(), movie.getId());
        Assertions.assertEquals(serviceActorMovie.getAllActorsByMovie(movie.getId()).size(), 1);

    }

    @Test
    @Order(12)
    public void addMovieToActorTest() {
        Movie movie = serviceActorMovie.getAllMovies().get(0);
        Actor actor = serviceActorMovie.getAllActors().get(0);
        serviceActorMovie.addMovieToActor(actor.getId(), movie.getId());
        Assertions.assertEquals(serviceActorMovie.getAllMoviesByActor(actor.getId()).size(), 1);
    }

    @Test
    @Order(13)
    public void getActorsByMovieTest() {
        Movie movie = serviceActorMovie.getAllMovies().get(0);
        int movieSize = serviceActorMovie.getAllActorsByMovie(movie.getId()).size();
        Assertions.assertEquals(movieSize, 1);
    }

    @Test
    @Order(14)
    public void getMoviesByActorTest() {
        Actor actor = serviceActorMovie.getAllActors().get(0);
        int actorSize = serviceActorMovie.getAllMoviesByActor(actor.getId()).size();
        Assertions.assertEquals(actorSize, 1);
    }

    @Test
    @Order(15)
    public void deleteActorFromMovieListTest() {
        Movie movie = serviceActorMovie.getAllMovies().get(0);
        Optional<Actor> actor = serviceActorMovie.getAllActorsByMovie(movie.getId()).get(0);
        serviceActorMovie.deleteActorFromMovieList(actor.get().getId(), movie.getId());
        Assertions.assertEquals(serviceActorMovie.getAllActorsByMovie(movie.getId()).size(), 0);

    }

    @Test
    @Order(16)
    public void deleteMovieFromActorListTest() {
        Actor actor = serviceActorMovie.getAllActors().get(0);
        Optional<Movie> movie = serviceActorMovie.getAllMoviesByActor(actor.getId()).get(0);
        serviceActorMovie.deleteMovieFromActorList(movie.get().getId(), actor.getId());
        Assertions.assertEquals(serviceActorMovie.getAllMoviesByActor(actor.getId()).size(), 0);
    }

    @Test
    @Order(17)
    public void doNotAddNewActorWhenNameAndSurnameIsSame() {
        Actor actor = serviceActorMovie.getAllActors().get(0);
        Actor newActor = new Actor();
        newActor.setName(actor.getName());
        newActor.setSurname(actor.getSurName());
        serviceActorMovie.addActor(newActor);
        Assertions.assertEquals(serviceActorMovie.getAllActors().size(), ACTORSIZE);
    }

    @Test
    @Order(18)
    public void doNotAddNewMovieWhenTitleAndGenreIsSame() {
        Movie movie = serviceActorMovie.getAllMovies().get(0);
        Movie newMovie = new Movie();
        newMovie.setTitle(movie.getTitle());
        newMovie.setGenre(movie.getGenre());
        serviceActorMovie.addMovie(newMovie);
        Assertions.assertEquals(serviceActorMovie.getAllMovies().size(), MOVIESIZE);
    }

    @Test
    @Order(19)
    public void doNotAddNewActorWhenNameIsNotValid() {
        Actor newActor = new Actor();
        serviceActorMovie.addActor(newActor);
        newActor.setName(WRONGNAME);
        newActor.setSurname(NEWSURNAME);
        Assertions.assertEquals(serviceActorMovie.getAllActors().size(), ACTORSIZE);
    }

    @Test
    @Order(20)
    public void doNotAddNewActorWhenSurnameIsNotValid() {
        Actor newActor = new Actor();
        newActor.setName(NEWNAME);
        newActor.setSurname(WRONGSURNAME);
        serviceActorMovie.addActor(newActor);
        Assertions.assertEquals(serviceActorMovie.getAllActors().size(), ACTORSIZE);
    }

    @Test
    @Order(21)
    public void doNotAddNewMovieWhenGenreIsNotValid() {
        Movie newMovie = new Movie();
        newMovie.setTitle(NEWTITLE);
        newMovie.setGenre(WRONGGENRE);
        serviceActorMovie.addMovie(newMovie);
        Assertions.assertEquals(serviceActorMovie.getAllMovies().size(), MOVIESIZE);
    }

    public static Actor generateActor(int i) {
        Actor actor = new Actor();
        actor.setName(NAME + (char) i);
        actor.setSurname(SURNAME + (char) i);
        return actor;
    }

    public static Movie generateMovie(int i) {
        Movie movie = new Movie();
        movie.setTitle(TITLE + (char) i);
        movie.setGenre(GENRE + (char) i);
        return movie;
    }
}