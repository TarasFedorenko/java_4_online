package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerTextAnalyze {
    TextAnalyze textAnalyze = new TextAnalyze();

    public void run() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please write the text");
        String str = bf.readLine();
        textAnalyze.analyzeThat(str);
        bf.close(); // а почему не try с ресурсами?)
    }
}
