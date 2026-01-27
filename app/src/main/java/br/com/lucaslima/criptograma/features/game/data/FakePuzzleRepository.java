package br.com.lucaslima.criptograma.features.game.data;

import br.com.lucaslima.criptograma.features.game.domain.Puzzle;

public final class FakePuzzleRepository implements PuzzleRepository {

    @Override
    public Puzzle loadRandomPuzzle() {
        return null;
    }
}
