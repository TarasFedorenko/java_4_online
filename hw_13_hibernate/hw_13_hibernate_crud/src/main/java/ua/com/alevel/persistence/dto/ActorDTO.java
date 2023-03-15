package ua.com.alevel.persistence.dto;

import ua.com.alevel.persistence.entity.Actor;

public record ActorDTO(Actor actor, long movieCount) {
    @Override
    public String toString() {
        return "ActorDTO{" +
                "actor= " + actor.getName() +
                " " + actor.getSurname() +
                ", movieCount= " + movieCount +
                '}';
    }
}



