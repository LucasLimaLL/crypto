package br.com.lucaslima.criptograma.features.game.application;


import br.com.lucaslima.criptograma.features.game.domain.GameRun;

public final class BuildFinalWordUseCase {

    public String execute(GameRun gameRun) {
        int pickColumn = gameRun.session().puzzle().pickColumn();

        return gameRun.session().puzzle().words().stream()
                .map(word -> word.letters().get(pickColumn))
                .map(realLetter -> {
                    int number = gameRun.session().template().numberFor(realLetter);
                    Character guessedLetter = gameRun.session().state().guessFor(number);
                    return guessedLetter == null ? '_' : guessedLetter;
                })
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
