package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.dto.ActorDTO;
import ua.com.alevel.dto.MovieDTO;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Movie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActorMovieCrudTest {

    private static final ServiceActorMovieImpl serviceActorMovie = new ServiceActorMovieImpl();
    private static final String NAME = "testName";
    private static final String SURNAME = "testSurname";
    private static final Integer AGE = 20;
    private static final String TITLE = "testTitle";
    private static final String GENRE = "testGenre";
    private static final Integer RELEASE_YEAR = 1950;
    private static final String NEWNAME = "newName";
    private static final String NEWSURNAME = "newSurname";
    private static final Integer NEWAGE = 30;
    private static final String NEWTITLE = "newTitle";
    private static final String NEWGENRE = "newGenre";
    private static final Integer NEWRELEASE_YEAR = 2000;
    private static final int MOVIESIZE = 10;
    private static final int ACTORSIZE = 10;

    @BeforeAll
    public static void createInitActor() throws IOException {
        File file = new File("actors.json");
        file.createNewFile();
        FileWriter fileWrite = new FileWriter("actors.json");
        fileWrite.write("{\"actors\":[]}");
        fileWrite.flush();
    }

    @BeforeAll
    public static void createInitMovie() throws IOException {
        File file = new File("movies.json");
        file.createNewFile();
        FileWriter fileWrite = new FileWriter("movies.json");
        fileWrite.write("{\"movies\":[]}");
        fileWrite.flush();
    }

    @BeforeAll
    public static void setUpActor() {
        for (int i = 0; i < ACTORSIZE; i++) {
            ActorDTO actorDTO = generateActor(i);
            serviceActorMovie.addActor(actorDTO);
        }
    }

    @BeforeAll
    public static void setUpMovie() {
        for (int i = 0; i < MOVIESIZE; i++) {
            MovieDTO movieDTO = generateMovie(i);
            serviceActorMovie.addMovie(movieDTO);
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
        ActorDTO actorDTO = generateActor(ACTORSIZE + 1);
        serviceActorMovie.addActor(actorDTO);
        Assertions.assertEquals(serviceActorMovie.getAllActors().size(), ACTORSIZE + 1);
    }

    @Test
    @Order(4)
    public void addMovieTest() {
        MovieDTO movieDTO = generateMovie(MOVIESIZE + 1);
        serviceActorMovie.addMovie(movieDTO);
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
        actor.setAge(NEWAGE);
        serviceActorMovie.updateActor(actor);
        Assertions.assertNotEquals(serviceActorMovie.getAllActors().get(0).getName(), NAME);
        Assertions.assertNotEquals(serviceActorMovie.getAllActors().get(0).getSurName(), SURNAME);
        Assertions.assertNotEquals(serviceActorMovie.getAllActors().get(0).getAge(), AGE);
    }

    @Test
    @Order(10)
    public void updateMovieTest() {
        Movie movie = serviceActorMovie.getAllMovies().get(0);
        movie.setTitle(NEWTITLE);
        movie.setGenre(NEWGENRE);
        movie.setReleaseYear(NEWRELEASE_YEAR);
        serviceActorMovie.updateMovie(movie);
        Assertions.assertNotEquals(serviceActorMovie.getAllMovies().get(0).getTitle(), TITLE);
        Assertions.assertNotEquals(serviceActorMovie.getAllMovies().get(0).getGenre(), GENRE);
        Assertions.assertNotEquals(serviceActorMovie.getAllMovies().get(0).getReleaseYear(), RELEASE_YEAR);
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
        Actor actor = serviceActorMovie.getAllActorsByMovie(movie.getId()).get(0);
        serviceActorMovie.deleteActorFromMovieList(actor.getId(), movie.getId());
        Assertions.assertEquals(serviceActorMovie.getAllActorsByMovie(movie.getId()).size(), 0);

    }

    @Test
    @Order(16)
    public void deleteMovieFromActorListTest() {
        Actor actor = serviceActorMovie.getAllActors().get(0);
        Movie movie = serviceActorMovie.getAllMoviesByActor(actor.getId()).get(0);
        serviceActorMovie.deleteMovieFromActorList(movie.getId(), actor.getId());
        Assertions.assertEquals(serviceActorMovie.getAllMoviesByActor(actor.getId()).size(), 0);
    }

    @Test
    @Order(17)
    public void doNotAddNewActorWhenNameSurnameAndAgeIsSame() {
        Actor actor = serviceActorMovie.getAllActors().get(0);
        ActorDTO newActor = new ActorDTO(actor.getName(), actor.getSurName(), actor.getAge());
        serviceActorMovie.addActor(newActor);
        Assertions.assertEquals(serviceActorMovie.getAllActors().size(), ACTORSIZE);
    }

    @Test
    @Order(18)
    public void doNotAddNewMovieWhenTitleGenreAndYearIsSame() {
        Movie movie = serviceActorMovie.getAllMovies().get(0);
        MovieDTO newMovie = new MovieDTO(movie.getTitle(), movie.getGenre(), movie.getReleaseYear());
        serviceActorMovie.addMovie(newMovie);
        Assertions.assertEquals(serviceActorMovie.getAllMovies().size(), MOVIESIZE);
    }

    public static ActorDTO generateActor(int i) {
        ActorDTO actorDTO = new ActorDTO((NAME + (char) i), (SURNAME + (char) i), (AGE + i));
        return actorDTO;
    }

    public static MovieDTO generateMovie(int i) {
        MovieDTO movieDTO = new MovieDTO((TITLE + (char) i), (GENRE + (char) i), (RELEASE_YEAR + (char) i));
        return movieDTO;
    }

}