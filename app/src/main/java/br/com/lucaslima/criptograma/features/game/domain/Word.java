package br.com.lucaslima.criptograma.features.game.domain;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public final class Word {

    private final String code;
    private final String hint;
    private final List<Character> letters;

    public Word(String code, String hint, List<Character> letters) {
        validateCode(code);
        validateLetters(letters);

        this.code = code;
        this.hint = hint == null ? "" : hint;
        this.letters = normalizeLetters(letters);
    }

    public String code() { return code; }
    public String hint() { return hint; }
    public List<Character> letters() { return letters; }
    public int length() { return letters.size(); }

    private static void validateCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("code must not be blank");
        }
    }

    private static void validateLetters(List<Character> letters) {
        if (letters == null || letters.isEmpty()) {
            throw new IllegalArgumentException("letters must not be empty");
        }
    }

    private static List<Character> normalizeLetters(List<Character> input) {
        List<Character> output = new ArrayList<>(input.size());

        for (Character character : input) {
            validateLetterNotNull(character);

            char upper = Character.toUpperCase(character);
            char normalized = removeAccent(upper);

            validateLetterAZ(normalized);
            output.add(normalized);
        }

        return List.copyOf(output);
    }

    private static void validateLetterNotNull(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("letters must not contain null");
        }
    }

    private static void validateLetterAZ(char character) {
        if (character < 'A' || character > 'Z') {
            throw new IllegalArgumentException("letters must contain only A–Z");
        }
    }

    private static char removeAccent(char character) {
        String normalized = Normalizer.normalize(String.valueOf(character), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.charAt(0);
    }
}
