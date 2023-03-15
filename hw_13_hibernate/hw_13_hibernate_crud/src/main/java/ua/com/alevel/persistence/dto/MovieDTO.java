package ua.com.alevel.persistence.dto;

import ua.com.alevel.persistence.entity.Movie;

public record MovieDTO (Movie movie, long actorCount){
    @Override
    public String toString() {
        return "MovieDTO{" +
                "movie= " + movie.getTitle() +
                ", actorCount= " + actorCount +
                '}';
    }
}
