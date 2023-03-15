package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.dto.ActorDTO;
import ua.com.alevel.persistence.entity.Actor;

import java.util.Collection;

public interface ActorDao extends BaseDao<Actor> {
    Collection<ActorDTO> getNumberOfMoviesByActor();
}