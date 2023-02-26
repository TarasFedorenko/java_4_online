package ua.com.alevel.service;

import ua.com.alevel.entity.Town;
import ua.com.alevel.entity.Highway;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    private int maxTowns;
    private final int maxCost = 200000;
    private Town[] townsList;
    private int[][] connectionTown;
    private int countOfTown;
    private int countOfTownInList;
    private List<Highway> shortestPaths;
    private int currentTown;
    private int startToRide;

    public void parseGraph() {

        try (BufferedReader reader = new BufferedReader(new FileReader("module_2_files/input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("module_2_files/output.txt"))) {
            while (reader.ready()) {
                maxTowns = Integer.parseInt(reader.readLine());
                fillArray();
                for (int i = 0; i < maxTowns; i++) {
                    String nameOfTown = reader.readLine();
                    addTown(nameOfTown, i);
                    int countOfNeighbours = Integer.parseInt(reader.readLine());
                    for (int j = 0; j < countOfNeighbours; j++) {
                        String pathToNeighbour = reader.readLine();
                        String[] townAndCost = pathToNeighbour.split(" ");
                        if (townAndCost.length != 2) {
                            throw new RuntimeException("Wrong data in chapter of town " + nameOfTown);
                        }
                        addCost(i, Integer.parseInt(townAndCost[0]) - 1, Integer.parseInt(townAndCost[1]));
                    }
                }
                int countOfPath = Integer.parseInt(reader.readLine());
                for (int j = 0; j < countOfPath; j++) {
                    String townsToFindWay = reader.readLine();
                    String[] wayBetweenTowns = townsToFindWay.split(" ");
                    if (wayBetweenTowns.length != 2) {
                        throw new RuntimeException("Wrong data in chapter of directions " + townsToFindWay);
                    }
                    Town startTown = Arrays.stream(townsList)
                            .filter(town -> town.getName().equals(wayBetweenTowns[0]))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Can`t find start town" + wayBetweenTowns[0]));

                    Town endTown = Arrays.stream(townsList)
                            .filter(town -> town.getName().equals(wayBetweenTowns[1]))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Can`t find end town" + wayBetweenTowns[1]));
                    shortestWay(startTown.getNumberOfTown(), endTown.getNumberOfTown(), writer);
                    clean();
                }
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Wrong data");
        }
    }

    private void addTown(String name, int numberOfTown) {
        townsList[countOfTown++] = new Town(name, numberOfTown);
    }

    private void addCost(int start, int end, int cost) {
        connectionTown[start][end] = cost;
    }

    private void fillArray() {
        townsList = new Town[maxTowns];
        connectionTown = new int[maxTowns][maxTowns];
        countOfTown = 0;
        countOfTownInList = 0;
        for (int i = 0; i < maxTowns; i++) {
            for (int k = 0; k < maxTowns; k++) {
                connectionTown[i][k] = maxCost;
                shortestPaths = new ArrayList<>();
            }
        }
    }

    private void shortestWay(int startGraph, int endGraph, BufferedWriter writer) {
        townsList[startGraph].setActive(true);
        countOfTownInList = 1;
        for (int i = 0; i < countOfTown; i++) {
            int tempCost = connectionTown[startGraph][i];
            Highway cityPath = new Highway(tempCost);
            cityPath.getLeftBehindTowns().add(startGraph);
            shortestPaths.add(cityPath);
        }
        while (countOfTownInList < countOfTown) {
            int indexMin = getMin();
            int minDist = shortestPaths.get(indexMin).getCost();
            if (minDist == maxCost) {
                break;
            } else {
                currentTown = indexMin;
                startToRide = shortestPaths.get(indexMin).getCost();
            }
            townsList[currentTown].setActive(true);
            countOfTownInList++;
            updateShortestPaths();
        }
        displayPaths(startGraph, endGraph);
        writePaths(startGraph, endGraph, writer);
    }

    private int getMin() {
        int minDist = maxCost;
        int indexMin = 0;
        for (int i = 1; i < countOfTown; i++) {
            if (!townsList[i].isActive() && shortestPaths.get(i).getCost() < minDist) {
                minDist = shortestPaths.get(i).getCost();
                indexMin = i;
            }
        }
        return indexMin;
    }

    private void updateShortestPaths() {
        int townIndex = 1;
        while (townIndex < countOfTown) {
            if (townsList[townIndex].isActive()) {
                townIndex++;
                continue;
            }
            int currentToFringe = connectionTown[currentTown][townIndex];
            int startToFringe = startToRide + currentToFringe;
            int shortPathDistance = shortestPaths.get(townIndex).getCost();
            if (startToFringe < shortPathDistance) {
                List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentTown).getLeftBehindTowns());
                newParents.add(currentTown);
                shortestPaths.get(townIndex).setLeftBehindTowns(newParents);
                shortestPaths.get(townIndex).setCost(startToFringe);
            }
            townIndex++;
        }
    }

    private void writePaths(int startTown, int endTown, BufferedWriter writer) {
        String shortestWay = "";
        shortestWay = townsList[startTown].getName() + " -> " + townsList[endTown].getName() + " = ";
        if (shortestPaths.get(endTown).getCost() == maxCost) {
            shortestWay = shortestWay + "0";
        } else {
            String result = shortestPaths.get(endTown).getCost() + " (";
            List<Integer> parents = shortestPaths.get(endTown).getLeftBehindTowns();
            for (int j = 0; j < parents.size(); j++) {
                result += townsList[parents.get(j)].getName() + " -> ";
            }
            shortestWay = shortestWay + result + townsList[endTown].getName() + ")";
        }
        try {
            writer.write(shortestWay);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayPaths(int startTown, int endTown) {
        System.out.print(townsList[startTown].getName() + " -> " + townsList[endTown].getName() + " = ");
        if (shortestPaths.get(endTown).getCost() == maxCost) {
            System.out.println("0");
        } else {
            String result = shortestPaths.get(endTown).getCost() + " (";
            List<Integer> parents = shortestPaths.get(endTown).getLeftBehindTowns();
            for (int j = 0; j < parents.size(); j++) {
                result += townsList[parents.get(j)].getName() + " -> ";
            }
            System.out.println(result + townsList[endTown].getName() + ")");
        }
    }

    private void clean() {
        countOfTownInList = 0;
        for (int i = 0; i < countOfTown; i++) {
            townsList[i].setActive(false);
        }
        countOfTownInList = 0;
        for (int i = 0; i < maxTowns; i++) {
            for (int k = 0; k < maxTowns; k++) {
                shortestPaths = new ArrayList<>();
            }
        }
    }
}