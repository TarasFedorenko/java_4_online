package ua.com.alevel.persistence.entity;

public class Actor extends BaseEntity {

    public String name;
    public String surname;
    public int age;

    public Actor() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Actor {" +
                " id -> " + getId() +
                "; was created : " + getCreated() +
                "; name =  " + name + " " + surname +
                "; age = " + age + " }";
    }
}
