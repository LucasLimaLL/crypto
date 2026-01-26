package br.com.lucaslima.criptograma.features.game.domain;

import java.util.List;

public final class Puzzle {

    private final String id;
    private final List<Word> words;

    public Puzzle(String id, List<Word> words) {
        this.id = id;
        this.words = words;
    }

    public String getId() {
        return id;
    }

    public List<Word> getWords() {
        return words;
    }
}
