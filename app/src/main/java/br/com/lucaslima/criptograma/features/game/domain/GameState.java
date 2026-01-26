package br.com.lucaslima.criptograma.features.game.domain;

public final class GameState {

    private final Puzzle puzzle;
    private boolean solved;

    public GameState(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public boolean isSolved() {
        return solved;
    }

    public void markSolved() {
        this.solved = true;
    }

}
