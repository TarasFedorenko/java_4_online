package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThirdTask {
    public static void main(String[]args) throws IOException {

        System.out.println("What lesson do you need?");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int lessonNumber = Integer.parseInt(bufferedReader.readLine());


        int totalInMinutes = ((lessonNumber * 45) + ((lessonNumber -1) / 2) * 15) + ((((lessonNumber -1) / 2) + ((lessonNumber -1) % 2)) * 5);
        int hoursLesson = totalInMinutes / 60 + 9;
        int minutesLesson = totalInMinutes % 60;
        System.out.println("Lesson № " + lessonNumber + " over at " + hoursLesson +":" + minutesLesson);

    }
}
