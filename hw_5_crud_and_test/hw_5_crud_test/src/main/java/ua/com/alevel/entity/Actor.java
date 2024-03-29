package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Actor extends BaseEntity {

    private String name;
    private String surname;


    private Set<String> movieIdList = new HashSet<>();


    public String getName() {
        return name;
    }

    public String getSurName() {
        return surname;
    }

    public void setName(String name) {
        if (name.matches(".*\\d.*")) {
            System.out.println("Name cannot contain numbers");
        } else {
            this.name = name;
        }
    }

    public void setSurname(String surname) {
        if (surname.matches(".*\\d.*")) {
            System.out.println("Surname cannot contain numbers");
        } else {
            this.surname = surname;
        }
    }

    public Set<String> getMovieIdList() {
        return movieIdList;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id='" + getId() +
                '}';
    }
}