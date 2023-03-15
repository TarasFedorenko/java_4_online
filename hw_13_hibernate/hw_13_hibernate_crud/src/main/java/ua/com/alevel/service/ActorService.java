package ua.com.alevel.service;

import ua.com.alevel.persistence.dto.ActorDTO;
import ua.com.alevel.persistence.entity.Actor;

import java.util.Collection;


public interface ActorService extends BaseService<Actor> {

    Collection<ActorDTO> getNumberOfMoviesByActor();
}
