package ua.com.alevel.service;


import ua.com.alevel.persistence.dto.MovieGenreDTO;
import ua.com.alevel.persistence.entity.Movie;

import java.util.Collection;


public interface MovieService {
    void createMovie(Movie movie);

    Movie getMovie(Long id);

    void updateMovie(Long id, Movie movie);

    void deleteMovie(Long id);

    Collection<Movie> getAllMovie();

    Collection<Movie> getAllMoviesByActor(Long actorId);

    Collection<MovieGenreDTO> getNumberOfActorsByMovieGenre();
}
