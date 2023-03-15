package ua.com.alevel.service;

import ua.com.alevel.persistence.dto.MovieDTO;
import ua.com.alevel.persistence.entity.Movie;

import java.util.Collection;

public interface MovieService extends BaseService<Movie> {
    Collection<MovieDTO> getNumberOfActorsByMovie();

}
