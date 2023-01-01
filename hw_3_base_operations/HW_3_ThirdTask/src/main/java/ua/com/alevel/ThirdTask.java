package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThirdTask {

    private static final int SOMETHING_VALUE_1 = 1;
    private static final int SOMETHING_VALUE_2 = 2;
    private static final int SOMETHING_VALUE_5 = 5;
    private static final int SOMETHING_VALUE_9 = 9;
    private static final int SOMETHING_VALUE_45 = 45;
    private static final int SOMETHING_VALUE_60 = 60;

    public static void main(String[]args) throws IOException {
        System.out.println("What lesson do you need?");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int lessonNumber = Integer.parseInt(bufferedReader.readLine());
        int totalInMinutes = (
                (lessonNumber * SOMETHING_VALUE_45) +
                        ((lessonNumber - SOMETHING_VALUE_1) / SOMETHING_VALUE_2) * 15)
                + (
                        (
                                ((lessonNumber - SOMETHING_VALUE_1) / SOMETHING_VALUE_2)
                                        + ((lessonNumber - SOMETHING_VALUE_1) % SOMETHING_VALUE_2)
                        ) * SOMETHING_VALUE_5);
        int hoursLesson = totalInMinutes / SOMETHING_VALUE_60 + SOMETHING_VALUE_9;
        int minutesLesson = totalInMinutes % SOMETHING_VALUE_60;
        System.out.println("Lesson № " + lessonNumber + " over at " + hoursLesson +":" + minutesLesson);
    }
}
