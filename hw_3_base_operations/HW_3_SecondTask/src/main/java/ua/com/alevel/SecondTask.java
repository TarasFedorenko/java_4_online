package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class SecondTask {
    public static void main(String[]args)throws IOException{
        int i;
        int array[] = new int[256];
        System.out.println("Enter the string ");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();

        for (i = 0; i <str.length(); i++)
        {
            array[(int) str.charAt(i)]++;
        }
        for (i = 0; i < 256; i++)
        {
            if (array[i] != 0)
            {
                System.out.println((char) i + " --> " + array[i]);
            }
        }
        bf.close();
    }
}