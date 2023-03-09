package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.dto.ActorDTO;
import ua.com.alevel.persistence.entity.Actor;

import java.util.Collection;
import java.util.Optional;

public interface ActorDAO {
    void createActor(Actor actor);

    Optional<Actor> getActor(Long id);

    void updateActor(Actor actor);

    void deleteActor(Long id);

    Collection<Actor> getAllActor();

    Collection<Actor> getAllActorsByMovie(Long movieId);

    void addActorToMovie(Long actorId, Long movieId);

    void deleteActorFromMovie(Long actorId, Long movieId);

    Collection<ActorDTO> getNumberOfMoviesByActor();


}
