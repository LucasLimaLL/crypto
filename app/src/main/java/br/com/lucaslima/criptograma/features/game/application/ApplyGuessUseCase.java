package br.com.lucaslima.criptograma.features.game.application;

import java.util.Optional;

import br.com.lucaslima.criptograma.features.game.domain.GameState;
import br.com.lucaslima.criptograma.features.game.domain.Word;
import br.com.lucaslima.criptograma.features.game.exceptions.WordNotFoundException;

public final class ApplyGuessUseCase {

    public GameState execute(GameState gameState, Word wordGuessed, int number, char letter) {
        Optional<Word> word = gameState
                .getPuzzle()
                .getWords()
                .stream()
                .filter(w -> w.getCode().equalsIgnoreCase(wordGuessed.getCode()))
                .findAny();

        if (word.isEmpty()) {
            throw new WordNotFoundException(wordGuessed);
        }

        word.get().guessLetter(number, letter);

        gameState
                .getPuzzle()
                .getWords()
                .stream()
                .filter(w -> w.getCode().equalsIgnoreCase(word.get().getCode()))
                .map(w -> word.get());

        return gameState;
    }

}
