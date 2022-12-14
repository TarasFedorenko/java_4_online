package ua.com.alevel;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String message = "Wazzzup";
        Random rand = new Random();
        boolean noMessage = true;
        ArrayList<Friends> friends = new ArrayList<>();
        friends.add(new Friends("Someone", false));
        friends.add(new Friends("Katya", false));
        friends.add(new Friends("Alex", false));
        friends.add(new Friends("Vasja", false));
        friends.add(new Friends("Dasha", false));
        friends.add(new Friends("Vitya", false));


        for (int i = 0; i < friends.size(); i++) {         //Знаю что этот кусок от сюда и до конца, шедевр говнокодинга, но ничего лучше не придумал.

            if (rand.nextInt(2) == 0) {
                friends.get(i).setFool(false);
                friends.get(i).sendMessageNotFool(message);
            } else {
                friends.get(i).setFool(true);
                friends.get(i).sendMessageFool();
                i = 0;
            }
            if (friends.get(i).getName() == "Vitya")
                System.out.println(Friends.cong);
        }

    }
}
