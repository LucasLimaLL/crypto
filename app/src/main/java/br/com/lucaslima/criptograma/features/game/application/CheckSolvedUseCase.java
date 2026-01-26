package br.com.lucaslima.criptograma.features.game.application;

import br.com.lucaslima.criptograma.features.game.domain.GameState;
import br.com.lucaslima.criptograma.features.game.domain.Word;

public final class CheckSolvedUseCase {

    public boolean execute(GameState gameState) {
        boolean isSolved = gameState
                .getPuzzle()
                .getWords()
                .stream()
                .allMatch(Word::isSolved);

        if (isSolved) {
            gameState.markSolved();
        }

        return isSolved;
    }
}
