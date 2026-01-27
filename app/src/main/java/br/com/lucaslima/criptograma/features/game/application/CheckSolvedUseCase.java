package br.com.lucaslima.criptograma.features.game.application;


import br.com.lucaslima.criptograma.features.game.domain.GameRun;

public final class CheckSolvedUseCase {

    public boolean execute(GameRun gameRun) {
        return gameRun.session().puzzle().words().stream()
                .flatMap(word -> word.letters().stream())
                .allMatch(realLetter -> {
                    int number = gameRun.session().template().numberFor(realLetter);
                    Character guessedLetter = gameRun.session().state().guessFor(number);
                    return guessedLetter != null && guessedLetter == realLetter;
                });
    }
}
