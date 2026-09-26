package com.example.myapplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextMetricsCalculator {

    // Code Review Fix #1: Pre-compile regex patterns as static final constants for better performance
    // and avoid splitting decimal numbers (e.g., 3.14) as separate sentences.
    private static final Pattern SENTENCE_PATTERN = Pattern.compile("(?<!\\d)[.!?]+(?!\\d)");
    private static final Pattern WORD_PATTERN = Pattern.compile("\\s+");
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[.,!?;:'\"()\\-\\[\\]{}]");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\b\\d+(\\.\\d+)?\\b");

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
        String[] sentenceParts = SENTENCE_PATTERN.split(trimmed);
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
        String[] wordParts = WORD_PATTERN.split(trimmed);
        int wordCount = 0;
        for (String word : wordParts) {
            if (!word.trim().isEmpty()) {
                wordCount++;
            }
        }

        // 3. Calculate number of punctuation marks
        Matcher punctMatcher = PUNCTUATION_PATTERN.matcher(trimmed);
        int punctuationCount = 0;
        while (punctMatcher.find()) {
            punctuationCount++;
        }

        // 4. Calculate number of numbers
        Matcher numberMatcher = NUMBER_PATTERN.matcher(trimmed);
        int numberCount = 0;
        while (numberMatcher.find()) {
            numberCount++;
        }

        return new MetricsResult(sentenceCount, wordCount, punctuationCount, numberCount);
    }

    public String formatHistoryEntry(String input, MetricsResult result) {
        String sanitizedInput = input.replaceAll("\\s+", " ").trim();
        return "\"" + sanitizedInput + "\" => " +
                result.getSentences() + ", " +
                result.getWords() + ", " +
                result.getPunctuationMarks() + ", " +
                result.getNumbers();
    }
}