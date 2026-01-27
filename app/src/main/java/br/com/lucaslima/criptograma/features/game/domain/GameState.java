package br.com.lucaslima.criptograma.features.game.domain;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public final class GameState {

    private final Map<Integer, Character> guesses;

    public GameState(Map<Integer, Character> guesses) {
        validateInputMap(guesses);
        this.guesses = Map.copyOf(normalizeGuesses(guesses));
    }

    public static GameState empty() {
        return new GameState(Map.of());
    }

    public Map<Integer, Character> guesses() {
        return guesses;
    }

    public Character guessFor(int number) {
        return guesses.get(number);
    }

    public GameState withGuess(int number, char letter) {
        validateNumber(number);

        char normalizedLetter = normalizeLetter(letter);
        validateLetter(normalizedLetter);

        var copy = new HashMap<>(guesses);
        copy.put(number, normalizedLetter);
        return new GameState(copy);
    }

    public GameState withoutGuess(int number) {
        validateNumber(number);

        if (!guesses.containsKey(number)) {
            return this;
        }

        var copy = new HashMap<>(guesses);
        copy.remove(number);
        return new GameState(copy);
    }

    private static void validateInputMap(Map<Integer, Character> map) {
        if (map == null) {
            throw new IllegalArgumentException("guesses must not be null");
        }
    }

    private static void validateNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("number must be > 0");
        }
    }

    private static void validateEntryKey(Integer number) {
        if (number == null || number <= 0) {
            throw new IllegalArgumentException("guess number must be > 0");
        }
    }

    private static void validateEntryValue(Character letter) {
        if (letter == null) {
            throw new IllegalArgumentException("guess letter must not be null");
        }
    }

    private static void validateLetter(char letter) {
        if (!String.valueOf(letter).matches("[A-Z]")) {
            throw new IllegalArgumentException("letter must be A–Z");
        }
    }

    private static Map<Integer, Character> normalizeGuesses(Map<Integer, Character> input) {
        var output = new HashMap<Integer, Character>(Math.max(16, input.size()));

        input.entrySet().stream().forEach(entry -> {
            Integer number = entry.getKey();
            Character letter = entry.getValue();

            validateEntryKey(number);
            validateEntryValue(letter);

            char normalizedLetter = normalizeLetter(letter);
            validateLetter(normalizedLetter);

            output.put(number, normalizedLetter);
        });

        return output;
    }

    private static char normalizeLetter(char letter) {
        String normalized = Normalizer
                .normalize(String.valueOf(letter), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toUpperCase();

        validateNormalizedNotEmpty(normalized);
        return normalized.charAt(0);
    }

    private static void validateNormalizedNotEmpty(String normalized) {
        if (normalized == null || normalized.isEmpty()) {
            throw new IllegalArgumentException("letter must not be empty");
        }
    }
}
