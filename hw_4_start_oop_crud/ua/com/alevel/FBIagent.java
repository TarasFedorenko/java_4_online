package ua.com.alevel;

class FBIagent {
    private String name;
    private int id;
    private String rank;
    boolean hasAGun;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public boolean isHasAGun() {
        return hasAGun;
    }

    public void setHasAGun(boolean hasAGun) {
        this.hasAGun = hasAGun;
    }
}