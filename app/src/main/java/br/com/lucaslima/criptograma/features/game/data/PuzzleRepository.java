package br.com.lucaslima.criptograma.features.game.data;

import br.com.lucaslima.criptograma.features.game.domain.Puzzle;

public interface PuzzleRepository {

    Puzzle loadRandomPuzzle();
}
