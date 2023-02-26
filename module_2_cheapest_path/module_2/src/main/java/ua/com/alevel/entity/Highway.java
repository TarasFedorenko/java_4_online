package ua.com.alevel.entity;

import java.util.ArrayList;
import java.util.List;

public class Highway {
    private int cost;
    private List<Integer> leftBehindTowns;

    public Highway(int cost) {
        this.cost = cost;
        this.leftBehindTowns = new ArrayList<>();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Integer> getLeftBehindTowns() {
        return leftBehindTowns;
    }

    public void setLeftBehindTowns(List<Integer> leftBehindTowns) {
        this.leftBehindTowns = leftBehindTowns;
    }


}
