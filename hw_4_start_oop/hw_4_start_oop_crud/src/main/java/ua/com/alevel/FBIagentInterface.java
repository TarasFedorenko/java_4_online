package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FBIagentInterface {

    public void start() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Main menu");
        String select;
        menu();
        while ((select = bf.readLine()) != null) {
            crud(bf, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("To create profile of agent enter - 1");
        System.out.println("To find some agent by ID enter - 2");
        System.out.println("To view the details of all agents enter - 3");
        System.out.println("To delete profile of agent enter - 4");
        System.out.println("To change profile of agent enter - 5");
        System.out.println("To exit the menu enter - 6");
        System.out.println();
    }

    private void crud(BufferedReader bf, String select) throws IOException {
        switch (select) {
            case "1":
                create(bf);
                break;
            case "2":
                findByID(bf);
                break;
            case "3":
                findAll();
                break;
            case "4":
                deleteByID(bf);
                break;
            case "5":
                changeByID(bf);
                break;
            case "6":
                quit();
                break;
        }
        menu();
    }

    private void create(BufferedReader bf) throws IOException {
        System.out.println("You want to create profile of new agent");
        System.out.println("Please enter agent name");
        String name = bf.readLine();
        System.out.println("Please enter ID number of agent");
        String id = bf.readLine();
        System.out.println("Please enter rank of agent");
        String rank = bf.readLine();
        FBIagent fbi = new FBIagent();
        fbi.setName(name);
        fbi.setId(id);
        fbi.setRank(rank);
        FBIagentStorage.addFBIagents(fbi);
    }

    private void findAll() {
        System.out.println("You will find all agent ");
        FBIagent[] fbic = FBIagentStorage.getAllFbiAgents();
        System.out.println(Arrays.toString(fbic));
    }

    private void findByID(BufferedReader bf) throws IOException {
        System.out.println("You want to find agent by ID number?");
        System.out.println("Please enter agent ID");
        String id = bf.readLine();
        FBIagent fbi = FBIagentStorage.getFbiAgent(id);
        if (fbi == null) {
            System.out.println("Wrong ID");
        } else {
            System.out.println(fbi);
        }

    }

    private void deleteByID(BufferedReader bf) throws IOException {
        System.out.println("You want to delete agent by ID number?");
        System.out.println("Please enter agent ID");
        String id = bf.readLine();
        FBIagentStorage.deleteFBIagents(id);

    }

    private void changeByID(BufferedReader bf) throws IOException {
        System.out.println("You want to change agent`s profile");
        System.out.println("Please enter agent ID");
        String id = bf.readLine();
        FBIagent fbi = FBIagentStorage.getFbiAgent(id);
        if (fbi == null) {
            System.out.println("Wrong ID");
        } else {
            System.out.println("Enter new name of agent");
            String name = bf.readLine();
            fbi.setName(name);
            System.out.println("Enter new ID number of agent");
            String newId = bf.readLine();
            fbi.setId(newId);
            System.out.println("Enter new rank of agent");
            String rank = bf.readLine();
            fbi.setRank(rank);
        }
    }

    private void quit() {
        System.exit(0);
    }

}
