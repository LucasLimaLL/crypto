package br.com.lucaslima.criptograma.features.game.domain;

import java.util.List;
import java.util.Map;

public final class GameSnapshot {

    public final String sessionId;
    public final String puzzleId;
    public final Map<Integer, Character> guesses;
    public final List<Map<Integer, Character>> past;
    public final List<Map<Integer, Character>> future;

    public GameSnapshot(String sessionId,
                        String puzzleId,
                        Map<Integer, Character> guesses,
                        List<Map<Integer, Character>> past,
                        List<Map<Integer, Character>> future) {
        this.sessionId = sessionId;
        this.puzzleId = puzzleId;
        this.guesses = guesses;
        this.past = past;
        this.future = future;
    }
}
