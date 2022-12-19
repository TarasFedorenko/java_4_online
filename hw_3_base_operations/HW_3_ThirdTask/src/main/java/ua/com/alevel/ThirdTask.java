package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThirdTask {
    public static void main(String[] args) throws IOException {


        String [] first = {"0","9","10","11","12","13","14","15","16","17","17"};
        String [] second = {"0","45","35","35","25","25","15","15","05","05","55"};
        System.out.println("Enter # of lesson ");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        int in = Integer.parseInt(str);

        System.out.println("End of lesson # "+ str +" is "+ first[in]+" : "+ second[in]);
        bf.close();
    }
}