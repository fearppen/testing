package ru.kontur.courses;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordStatisticImpl implements WordStatistics {
    protected final Map<String, Integer> statistics = new HashMap<>();

    @Override
    public void addWord(String word) {
        if (word == null) throw new IllegalArgumentException();
        if (word.isBlank()) return;
        if (word.length() > 10)
            word = word.substring(0, 10);
        String lowerWord = word.toLowerCase();

        int count = statistics.getOrDefault(lowerWord, 0);
        statistics.put(lowerWord, 1 + count);
    }

    @Override
    public Iterable<WordCount> getStatistics() {
        return statistics.entrySet().stream().map(it -> new WordCount(it.getKey(), it.getValue()))
                .sorted((left, right) -> Integer.compare(right.getCount(), left.getCount()))
                .sorted(Comparator.comparing(WordCount::getWord)).collect(Collectors.toList());
    }
}
