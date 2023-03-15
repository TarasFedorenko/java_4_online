package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.dao.MovieDao;
import ua.com.alevel.persistence.dao.impl.MovieDaoImpl;
import ua.com.alevel.persistence.dto.MovieDTO;
import ua.com.alevel.persistence.entity.Movie;
import ua.com.alevel.service.MovieService;

import java.util.Collection;


public class MovieServiceImpl implements MovieService {
    MovieDao movieDao = new MovieDaoImpl();

    @Override
    public void create(Movie movie) {
        if (!isMovieExist(movie)) {
            movieDao.create(movie);
        } else {
            System.out.println("This movie already exist");
        }
    }

    @Override
    public Movie getById(Long id) {
        return movieDao.getById(id).get();
    }

    @Override
    public Collection<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public void update(Movie movie) {
        movieDao.update(movie);
    }

    @Override
    public void delete(Movie movie) {
        movieDao.delete(movie);
    }

    @Override
    public Collection<MovieDTO> getNumberOfActorsByMovie() {
        return movieDao.getNumberOfActorsByMovie();
    }

    private boolean isMovieExist(Movie movie) {
        Collection<Movie> movies;
        movies = movieDao.getAll();
        for (Movie movieList : movies) {
            if (movieList.getTitle().equals(movie.getTitle()) && (movieList.getReleaseYear() == movie.getReleaseYear())) {
                return true;
            }
        }
        return false;
    }
}
