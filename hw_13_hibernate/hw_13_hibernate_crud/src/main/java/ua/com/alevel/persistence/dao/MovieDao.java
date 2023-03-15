package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.dto.MovieDTO;
import ua.com.alevel.persistence.entity.Movie;

import java.util.Collection;

public interface MovieDao extends BaseDao<Movie> {

    Collection<MovieDTO> getNumberOfActorsByMovie();
}
