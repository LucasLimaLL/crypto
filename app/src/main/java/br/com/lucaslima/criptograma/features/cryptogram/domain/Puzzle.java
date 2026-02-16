package br.com.lucaslima.criptograma.features.cryptogram.domain;

import java.util.List;

public final class Puzzle {

    private final String id;
    private final int pickColumn;
    private final String finalHint;
    private final List<Word> words;

    public Puzzle(String id, int pickColumn, String finalHint, List<Word> words) {
        validateId(id);
        validateWords(words);
        validatePickColumn(pickColumn, words);

        this.id = id;
        this.pickColumn = pickColumn;
        this.finalHint = finalHint;
        this.words = words;
    }

    public String id() { return id; }
    public int pickColumn() { return pickColumn; }
    public String finalHint() { return finalHint == null ? "" : finalHint; }
    public List<Word> words() { return words; }

    public int wordLength() { return words.get(0).length(); }
    public int finalLength() { return words.size(); }

    private static void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
    }

    private static void validateWords(List<Word> words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("words must not be empty");
        }

        int expectedWordLength = words.get(0).length();
        for (Word word : words) {
            if (word.length() != expectedWordLength) {
                throw new IllegalArgumentException("All words must have same length. Expected=" +
                        expectedWordLength + ", found=" + word.length() +
                        " for word=" + word.code());
            }
        }
    }

    private static void validatePickColumn(int pickColumn, List<Word> words) {
        if (pickColumn < 0) {
            throw new IllegalArgumentException("pickColumn must be >= 0");
        }

        if (!words.isEmpty() && pickColumn >= words.get(0).length()) {
            throw new IllegalArgumentException("pickColumn out of bounds. pickColumn=" + pickColumn + ", wordLength=" + words.get(0).length());
        }
    }
}
