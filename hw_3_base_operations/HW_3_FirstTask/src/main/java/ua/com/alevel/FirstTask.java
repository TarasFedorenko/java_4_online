package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirstTask {
    public static void main(String[] args) throws IOException {
        int sum = 0;
        char c;
        System.out.println("Enter the string with the numbers ");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();

        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            c = array[i];
            if (Character.isDigit(c)) {
                sum += Integer.parseInt(String.valueOf(c));
            }
        }

        System.out.println("The sum of a numbers is "+sum);
        bf.close();

    }
}