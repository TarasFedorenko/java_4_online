package ua.com.alevel;

class FBIagent {
    private String name;
    private String id;
    private String rank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "FBIAgent name= " + name +
                ", id=" + id +
                ", rank= " + rank+ "|";
    }
}