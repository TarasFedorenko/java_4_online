package ua.com.alevel.persistence.dto;

import ua.com.alevel.persistence.type.MovieGenre;

public record MovieGenreDTO(MovieGenre movieGenre, int actorsCount) {
    @Override
    public String toString() {
        return "MovieGenreDTO{" +
                "movieGenre=" + movieGenre +
                "----> actorsCount=" + actorsCount +
                '}';
    }
}
