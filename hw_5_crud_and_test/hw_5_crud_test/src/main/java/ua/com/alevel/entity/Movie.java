package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Movie extends BaseEntity {

    private String title;
    private String genre;

    private Set<String> actorIdList = new HashSet<>();

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public void setGenre(String genre) {
        if (genre.matches(".*\\d.*")) {
            System.out.println("Genre cannot contain numbers");
        } else {
            this.genre = genre;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Set<String> getActorIdList() {
        return actorIdList;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "genre='" + genre + '\'' +
                ", title='" + title + '\'' +
                ", id='" + getId() + '\'' +
                '}';
    }
}