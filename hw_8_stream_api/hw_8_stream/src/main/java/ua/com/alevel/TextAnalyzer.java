package ua.com.alevel;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextAnalyzer {
    private long wordCount;
    private LinkedHashMap<String, Long> wordsMap;
    private List<String> sortedWords;
    private List<Long> sortedRate;

    public void analyzeThat(String text) {

        String[] arrayWords = text.split("\\W+");

        Stream<String> countWord = Arrays.stream(arrayWords);
        wordCount = countWord
                .flatMap(s -> Stream.of(s.split("\\W+"))
                .filter(t -> !t.isEmpty()))
                .count();

        List<String> stringList = Arrays.asList(arrayWords);
        Map<String, Long> map = stringList.stream()
                .map(word -> word.toLowerCase())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        wordsMap = map.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        List<String> sortedWords = new ArrayList<>(wordsMap.keySet());
        List<Long> sortedRate = new ArrayList<>(wordsMap.values());


        String format = "| %-14s | %-7d| %-5d | %-6d |%n";
        System.out.format("+----------------+--------+-------+--------+%n");
        System.out.format("|      Words     | Rating | Count |Percent |%n");

        for (int i = 0; i < sortedWords.size(); i++) {
            System.out.format("+----------------+--------+-------+--------+%n");
            System.out.format(format, sortedWords.get(i), i + 1, sortedRate.get(i), sortedRate.get(i) * 100 / wordCount);
        }
        System.out.format("+----------------+--------+-------+--------+%n");
    }

}

