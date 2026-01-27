package br.com.lucaslima.criptograma.features.game.domain;

import java.util.HashMap;
import java.util.Map;

public final class Template {

    private final Map<Integer, Character> numberToLetter;
    private final Map<Character, Integer> letterToNumber;

    public Template(Map<Integer, Character> numberToLetter) {
        validateInputMap(numberToLetter);

        Map<Integer, Character> numberToLetterBuilder = new HashMap<>();
        Map<Character, Integer> letterToNumberBuilder = new HashMap<>();

        numberToLetter.entrySet().stream().forEach(entry -> {
            Integer number = entry.getKey();
            Character letter = entry.getValue();

            validateNumber(number);
            validateLetterNotNull(letter);

            char normalizedLetter = normalizeLetter(letter);

            validateUniqueNumber(numberToLetterBuilder, number);
            validateUniqueLetter(letterToNumberBuilder, normalizedLetter);

            numberToLetterBuilder.put(number, normalizedLetter);
            letterToNumberBuilder.put(normalizedLetter, number);
        });

        this.numberToLetter = Map.copyOf(numberToLetterBuilder);
        this.letterToNumber = Map.copyOf(letterToNumberBuilder);
    }

    public char letterFor(int number) {
        Character letter = numberToLetter.get(number);
        if (letter == null) {
            throw new IllegalStateException("Missing template mapping for number: " + number);
        }
        return letter;
    }

    public int numberFor(char letter) {
        char normalizedLetter = Character.toUpperCase(letter);

        Integer number = letterToNumber.get(normalizedLetter);
        if (number == null) {
            throw new IllegalStateException("Missing template mapping for letter: " + normalizedLetter);
        }
        return number;
    }

    public Map<Integer, Character> numberToLetterSnapshot() {
        return numberToLetter;
    }

    private static void validateInputMap(Map<Integer, Character> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("numberToLetter must not be empty");
        }
    }

    private static void validateNumber(Integer number) {
        if (number == null || number <= 0) {
            throw new IllegalArgumentException("number must be > 0");
        }
    }

    private static void validateLetterNotNull(Character letter) {
        if (letter == null) {
            throw new IllegalArgumentException("letter must not be null");
        }
    }

    private static char normalizeLetter(Character letter) {
        return Character.toUpperCase(letter);
    }

    private static void validateUniqueNumber(Map<Integer, Character> current, Integer number) {
        if (current.containsKey(number)) {
            throw new IllegalArgumentException("Duplicate number in template: " + number);
        }
    }

    private static void validateUniqueLetter(Map<Character, Integer> current, char letter) {
        if (current.containsKey(letter)) {
            throw new IllegalArgumentException("Duplicate letter in template: " + letter);
        }
    }
}
