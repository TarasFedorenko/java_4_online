package ua.com.alevel.entity;

import ua.com.alevel.dto.ActorDTO;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Actor extends BaseEntity {

    private String name;
    private String surname;
    private Integer age;
    private Set<String> movieIdList = new HashSet<>();

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<String> getMovieIdList() {
        return movieIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor actor)) return false;
        return name.equals(actor.name) && surname.equals(actor.surname) && age.equals(actor.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age + '}';
    }
}