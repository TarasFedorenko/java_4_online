package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.dao.ActorDao;
import ua.com.alevel.persistence.dao.impl.ActorDaoImpl;
import ua.com.alevel.persistence.dto.ActorDTO;
import ua.com.alevel.persistence.entity.Actor;
import ua.com.alevel.service.ActorService;

import java.util.Collection;

public class ActorServiceImpl implements ActorService {
    ActorDao actorDao = new ActorDaoImpl();

    @Override
    public Collection<ActorDTO> getNumberOfMoviesByActor() {
        return actorDao.getNumberOfMoviesByActor();
    }

    @Override
    public void create(Actor actor) {
        if (!isActorExist(actor)) {
            actorDao.create(actor);
        } else {
            System.out.println("This actor already exist");
        }
    }

    @Override
    public Actor getById(Long id) {
        return actorDao.getById(id).get();
    }

    @Override
    public Collection<Actor> getAll() {
        return actorDao.getAll();
    }

    @Override
    public void update(Actor actor) {
        actorDao.update(actor);
    }

    @Override
    public void delete(Actor actor) {
        actorDao.delete(actor);

    }

    private boolean isActorExist(Actor actor) {
        Collection<Actor> actors;
        actors = actorDao.getAll();
        for (Actor actorList : actors) {
            if (actorList.getName().equals(actor.getName()) && (actorList.getSurname().equals(actor.getSurname()))) {
                return true;
            }
        }
        return false;
    }
}