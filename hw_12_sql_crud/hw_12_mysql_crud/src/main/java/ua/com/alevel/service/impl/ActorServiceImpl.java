package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.dao.ActorDAO;
import ua.com.alevel.persistence.dao.impl.ActorDAOImpl;
import ua.com.alevel.persistence.dto.ActorDTO;
import ua.com.alevel.persistence.entity.Actor;
import ua.com.alevel.service.ActorService;
import java.util.Collection;
import java.util.Optional;

public class ActorServiceImpl implements ActorService {
    ActorDAO actorDAO = new ActorDAOImpl();

    @Override
    public void createActor(Actor actor) {
        if (!isActorExist(actor)) {
            actorDAO.createActor(actor);
        } else {
            System.out.println("This actor already exist");
        }
    }

    @Override
    public Actor getActor(Long id) {
        return actorDAO.getActor(id).get();
    }

    @Override
    public void updateActor(Long id, Actor actor) {
        Optional<Actor> optionalActor = actorDAO.getActor(id);
        if (optionalActor.isPresent()) {
            actor.setId(id);
            actorDAO.updateActor(actor);
        }
    }

    @Override
    public void deleteActor(Long id) {
        actorDAO.deleteActor(id);
    }

    @Override
    public Collection<Actor> getAllActor() {
        return actorDAO.getAllActor();
    }

    @Override
    public Collection<Actor> getAllActorsByMovie(Long movieId) {
        return actorDAO.getAllActorsByMovie(movieId);
    }

    @Override
    public void addActorToMovie(Long actorId, Long movieId) {
        actorDAO.addActorToMovie(actorId, movieId);
    }

    @Override
    public void deleteActorFromMovie(Long actorId, Long movieId) {
        actorDAO.deleteActorFromMovie(actorId, movieId);
    }

    @Override
    public Collection<ActorDTO> getNumberOfMoviesByActor() {
        return actorDAO.getNumberOfMoviesByActor();
    }

    private boolean isActorExist(Actor actor) {
        Collection<Actor> actors;
        actors = actorDAO.getAllActor();
        for (Actor actorList : actors) {
            if ((actorList.getName() != actor.getName()) && (actorList.getSurname() != actor.getSurname())) {
                return false;
            }
        }
        return true;
    }
}
