package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.dao.MovieDAO;
import ua.com.alevel.persistence.dao.impl.MovieDAOImpl;
import ua.com.alevel.persistence.dto.MovieGenreDTO;
import ua.com.alevel.persistence.entity.Movie;
import ua.com.alevel.service.MovieService;

import java.util.Collection;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {
    MovieDAO movieDAO = new MovieDAOImpl();

    @Override
    public void createMovie(Movie movie) {
        if (!isMovieExist(movie)) {
            movieDAO.createMovie(movie);
        } else {
            System.out.println("This movie already exist");
        }
    }

    @Override
    public Movie getMovie(Long id) {
        return movieDAO.getMovie(id).get();
    }

    @Override
    public void updateMovie(Long id, Movie movie) {
        Optional<Movie> optionalMovie = movieDAO.getMovie(id);
        if (optionalMovie.isPresent()) {
            movie.setId(id);
            movieDAO.updateMovie(movie);
        }
    }

    @Override
    public void deleteMovie(Long id) {
        movieDAO.deleteMovie(id);
    }

    @Override
    public Collection<Movie> getAllMovie() {
        return movieDAO.getAllMovie();
    }

    @Override
    public Collection<Movie> getAllMoviesByActor(Long actorId) {
        return movieDAO.getAllMoviesByActor(actorId);
    }

    @Override
    public Collection<MovieGenreDTO> getNumberOfActorsByMovieGenre() {
        return movieDAO.getNumberOfActorsByMovieGenre();
    }

    private boolean isMovieExist(Movie movie) {
        Collection<Movie> movies;
        movies = movieDAO.getAllMovie();
        for (Movie movieList : movies) {
            if ((movieList.getTitle() != movie.getTitle()) && (movieList.getReleaseYear() != movie.getReleaseYear())) {
                return false;
            }
        }
        return true;
    }
}