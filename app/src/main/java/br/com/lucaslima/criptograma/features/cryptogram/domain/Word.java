package br.com.lucaslima.criptograma.features.cryptogram.domain;

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
    public String hint() { return hint == null ? "" : hint; }
    public List<Character> letters() { return letters; }
    public List<Character> unresolvedLetters() { return letters; }
    public int length() { return (int) letters.stream().filter(c -> c != ' ').count(); }

    private void validateCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("code must not be blank");
        }
    }

    private void validateLetters(List<Character> letters) {
        if (letters == null || letters.isEmpty()) {
            throw new IllegalArgumentException("letters must not be empty");
        }
    }

    private List<Character> normalizeLetters(List<Character> input) {
        List<Character> output = new ArrayList<>(input.size());

        for (Character character : input) {
            output.add(validateLetter(character));
        }

        return List.copyOf(output);
    }

    private char validateLetter(Character character) {
        validateLetterNotNull(character);

        if (character == ' ') {
            return ' ';
        }

        char upper = Character.toUpperCase(character);
        char normalized = removeAccent(upper);

        validateLetterAZ(normalized);
        return normalized;
    }

    private void validateLetterNotNull(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("letters must not contain null");
        }
    }

    private void validateLetterAZ(char character) {
        if (character < 'A' || character > 'Z') {
            throw new IllegalArgumentException("letters must contain only A-Z and spaces");
        }
    }

    private char removeAccent(char character) {
        String normalized = Normalizer.normalize(String.valueOf(character), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.charAt(0);
    }
}
