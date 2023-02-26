package ua.com.alevel.entity;

public class Town {
    private String name;
    private int numberOfTown;
    private boolean isActive;

    public Town(String name, int numberOfTown) {
        this.name = name;
        this.numberOfTown = numberOfTown;
        this.isActive = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfTown() {
        return numberOfTown;
    }

    public void setNumberOfTown(int numberOfTown) {
        this.numberOfTown = numberOfTown;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
