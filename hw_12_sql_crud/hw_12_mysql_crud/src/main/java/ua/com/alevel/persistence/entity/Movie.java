package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.MovieGenre;

public class Movie extends BaseEntity {

    private String title;
    private MovieGenre movieGenre;
    private int releaseYear;

    public Movie() {
        super();
    }

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

    @Override
    public String toString() {
        return " Movie {" +
                " id -> " + getId() +
                "; was created  " + getCreated() +
                "; title -> " + title +
                "; movieGenre -> " + movieGenre +
                "; releaseYear " + releaseYear + " }";
    }
}