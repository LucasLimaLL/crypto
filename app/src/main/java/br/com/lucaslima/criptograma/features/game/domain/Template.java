package br.com.lucaslima.criptograma.features.game.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Template {

    private final Map<Character, Integer> letterToNumber;
    private final Map<Integer, Character> numberToLetter;

    public Template(Puzzle puzzle) {
        List<Character> alphabet = buildAlphabet(puzzle);
        this.letterToNumber = Collections.unmodifiableMap(buildLetterToNumberMap(alphabet));
        this.numberToLetter = Collections.unmodifiableMap(buildNumberToLetterMap(alphabet));
    }

    public Map<Character, Integer> getLetterToNumber() {
        return letterToNumber;
    }

    public Map<Integer, Character> getNumberToLetter() {
        return numberToLetter;
    }

    public Integer numberFor(char letter) {
        return letterToNumber.get(letter);
    }

    public Character letterFor(int number) {
        return numberToLetter.get(number);
    }

    private static List<Character> buildAlphabet(Puzzle puzzle) {
        List<Character> finalWord = getFinalWord(puzzle);
        List<Character> alphabet = new ArrayList<>(26);

        for (char c : finalWord) {
            if (!alphabet.contains(c)) {
                alphabet.add(c);
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (!alphabet.contains(c)) {
                alphabet.add(c);
            }
        }

        return alphabet;
    }

    private static List<Character> getFinalWord(Puzzle puzzle) {
        List<Character> finalWord = new ArrayList<>();
        for (Word word : puzzle.words()) {
            List<Character> lettersOnly = new ArrayList<>();
            for (Character c : word.letters()) {
                if (c != ' ') {
                    lettersOnly.add(c);
                }
            }
            finalWord.add(lettersOnly.get(puzzle.pickColumn()));
        }
        return finalWord;
    }

    private static Map<Character, Integer> buildLetterToNumberMap(List<Character> alphabet) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            map.put(alphabet.get(i), i + 1);
        }
        return map;
    }

    private static Map<Integer, Character> buildNumberToLetterMap(List<Character> alphabet) {
        Map<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            map.put(i + 1, alphabet.get(i));
        }
        return map;
    }
}
