package com.example.myapplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextMetricsCalculator {

    public static class MetricsResult {
        private final int sentences;
        private final int words;
        private final int punctuationMarks;
        private final int numbers;

        public MetricsResult(int sentences, int words, int punctuationMarks, int numbers) {
            this.sentences = sentences;
            this.words = words;
            this.punctuationMarks = punctuationMarks;
            this.numbers = numbers;
        }

        public int getSentences() {
            return sentences;
        }

        public int getWords() {
            return words;
        }

        public int getPunctuationMarks() {
            return punctuationMarks;
        }

        public int getNumbers() {
            return numbers;
        }
    }

    public MetricsResult calculate(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new MetricsResult(0, 0, 0, 0);
        }

        String trimmed = text.trim();

        // 1. Calculate number of sentences
        String[] sentenceParts = trimmed.split("[.!?]+");
        int sentenceCount = 0;
        for (String part : sentenceParts) {
            if (!part.trim().isEmpty()) {
                sentenceCount++;
            }
        }
        if (sentenceCount == 0) {
            sentenceCount = 1;
        }

        // 2. Calculate number of words
        String[] wordParts = trimmed.split("\\s+");
        int wordCount = 0;
        for (String word : wordParts) {
            if (!word.trim().isEmpty()) {
                wordCount++;
            }
        }

        // 3. Calculate number of punctuation marks
        Pattern punctPattern = Pattern.compile("[.,!?;:'\"()\\-\\[\\]{}]");
        Matcher punctMatcher = punctPattern.matcher(trimmed);
        int punctuationCount = 0;
        while (punctMatcher.find()) {
            punctuationCount++;
        }

        // 4. Calculate number of numbers
        Pattern numberPattern = Pattern.compile("\\b\\d+(\\.\\d+)?\\b");
        Matcher numberMatcher = numberPattern.matcher(trimmed);
        int numberCount = 0;
        while (numberMatcher.find()) {
            numberCount++;
        }

        return new MetricsResult(sentenceCount, wordCount, punctuationCount, numberCount);
    }

    public String formatHistoryEntry(String input, MetricsResult result) {
        return "\"" + input + "\" => " +
                result.getSentences() + ", " +
                result.getWords() + ", " +
                result.getPunctuationMarks() + ", " +
                result.getNumbers();
    }
}