package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondTask {

    private static final int MAX_SIZE = 256;

    public static void main(String[] args) throws IOException {
        int i;
        int[] array = new int[MAX_SIZE];
        System.out.println("Enter the string ");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        for (i = 0; i < str.length(); i++) {
            array[str.charAt(i)]++;
        }
        for (i = 0; i < MAX_SIZE; i++) {
            if (array[i] != 0) {
                System.out.println((char) i + " --> " + array[i]);
            }
        }
        bf.close();
    }
}
