package ua.com.alevel.service;

import ua.com.alevel.persistence.dto.ActorDTO;
import ua.com.alevel.persistence.entity.Actor;

import java.util.Collection;


public interface ActorService {
    void createActor(Actor actor);

    Actor getActor(Long id);

    void updateActor(Long id, Actor actor);

    void deleteActor(Long id);

    Collection<Actor> getAllActor();

    Collection<Actor> getAllActorsByMovie(Long movieId);

    void addActorToMovie(Long actorId, Long movieId);

    void deleteActorFromMovie(Long actorId, Long movieId);

    Collection<ActorDTO> getNumberOfMoviesByActor();
}
