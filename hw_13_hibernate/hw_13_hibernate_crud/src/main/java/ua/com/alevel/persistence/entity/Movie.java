package ua.com.alevel.persistence.entity;

import jakarta.persistence.*;
import ua.com.alevel.persistence.type.MovieGenre;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "movie_genre")
    private MovieGenre movieGenre;
    @Column(name = "release_year")
    private int releaseYear;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "mov_act",
            joinColumns = @JoinColumn(name = "mov_id"),
            inverseJoinColumns = @JoinColumn(name = "act_id")
    )
    Set<Actor> actors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieGenre getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(MovieGenre movieGenre) {
        this.movieGenre = movieGenre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Movie() {
        super();
        actors = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Movie{" +
                " id= " + getId() +
                "; was created " + getCreated() +
                "; title='" + title + '\'' +
                "; movie_genre= '" + movieGenre + '\'' +
                "; release_year= " + releaseYear +
                '}';
    }
}
