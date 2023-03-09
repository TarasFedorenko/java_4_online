package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.dto.MovieGenreDTO;
import ua.com.alevel.persistence.entity.Movie;

import java.util.Collection;
import java.util.Optional;

public interface MovieDAO {
    void createMovie(Movie movie);

    Optional<Movie> getMovie(Long id);

    void updateMovie(Movie movie);

    void deleteMovie(Long id);

    Collection<Movie> getAllMovie();

    Collection<Movie> getAllMoviesByActor(Long actorId);

    Collection<MovieGenreDTO> getNumberOfActorsByMovieGenre();
}