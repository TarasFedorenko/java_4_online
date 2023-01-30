package ua.com.alevel;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerTextAnalyzer {
    TextAnalyzer textAnalyzer = new TextAnalyzer();

    public void run() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please write the text");
        String str = bf.readLine();
        textAnalyzer.analyzeThat(str);
        bf.close();
    }
}
