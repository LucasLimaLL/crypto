package br.com.lucaslima.criptograma.features.game.domain;

import java.util.List;

public final class Puzzle {

    private final String id;
    private final int pickColumn;
    private final String finalHint;
    private final List<Word> words;

    public Puzzle(String id, int pickColumn, String finalHint, List<Word> words) {
        validateId(id);
        validatePickColumnNonNegative(pickColumn);
        validateWords(words);

        this.id = id;
        this.pickColumn = pickColumn;
        this.finalHint = finalHint == null ? "" : finalHint;
        this.words = List.copyOf(words);

        validateStructure();
    }

    public String id() { return id; }
    public int pickColumn() { return pickColumn; }
    public String finalHint() { return finalHint; }
    public List<Word> words() { return words; }

    public int wordLength() { return words.get(0).length(); }
    public int finalLength() { return words.size(); }

    private static void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
    }

    private static void validatePickColumnNonNegative(int pickColumn) {
        if (pickColumn < 0) {
            throw new IllegalArgumentException("pickColumn must be >= 0");
        }
    }

    private static void validateWords(List<Word> words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("words must not be empty");
        }
    }

    private void validateStructure() {
        int expectedWordLength = words.get(0).length();

        words.stream()
                .filter(word -> word.length() != expectedWordLength)
                .findAny()
                .ifPresent(word -> {
                    throw new IllegalArgumentException(
                            "All words must have same length. Expected=" +
                                    expectedWordLength + ", found=" + word.length() +
                                    " for word=" + word.code()
                    );
                });

        validatePickColumnBounds(pickColumn, expectedWordLength);
    }

    private static void validatePickColumnBounds(int pickColumn, int expectedWordLength) {
        if (pickColumn >= expectedWordLength) {
            throw new IllegalArgumentException(
                    "pickColumn out of bounds. pickColumn=" + pickColumn + ", wordLength=" + expectedWordLength
            );
        }
    }
}
