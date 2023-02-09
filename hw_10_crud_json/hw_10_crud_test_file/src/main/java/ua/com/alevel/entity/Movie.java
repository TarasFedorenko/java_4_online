package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Movie extends BaseEntity {

    private String title;
    private String genre;
    private Integer releaseYear;

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    private Set<String> actorIdList = new HashSet<>();

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getActorIdList() {
        return actorIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return title.equals(movie.title) && genre.equals(movie.genre) && releaseYear.equals(movie.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, releaseYear);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}