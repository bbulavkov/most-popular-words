package bulavkov.popular.words;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) throws IOException {
        int maxWordLength = 3;
        int mostPopularWordsCount = 4;

        String fileContent = new String(Files.readAllBytes(Paths.get("README.md")));

        Stream<String> wordsLongerThan = Arrays.stream(fileContent.split("\\s+"))
                .filter(s -> s.matches("[a-zA-Z]+") && s.length() >= maxWordLength);

        List<Map.Entry<String, Long>> mostPopularWords =
                getNMostPopularWords(wordsLongerThan, mostPopularWordsCount);

        System.out.println(mostPopularWords);
    }

    public static List<Map.Entry<String, Long>> getNMostPopularWords(Stream<String> words, int count) {
        Map<String, Long> wordsByCount = words
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        return wordsByCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(count)
                .collect(Collectors.toList());
    }
}
