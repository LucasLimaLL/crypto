package br.com.lucaslima.criptograma.features.cryptogram.application;


import br.com.lucaslima.criptograma.features.cryptogram.domain.GameRun;
import br.com.lucaslima.criptograma.features.cryptogram.domain.Word;

public final class BuildFinalWordUseCase {

    public String execute(GameRun gameRun) {
        int pickColumn = gameRun.session().puzzle().pickColumn();

        return gameRun
                .session()
                .puzzle()
                .words()
                .stream()
                .map(word -> getCharacter(word, pickColumn))
                .map(realLetter -> isGuessedLetterCorrect(gameRun, realLetter))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static char isGuessedLetterCorrect(GameRun gameRun, Character realLetter) {
        int number = gameRun.session().template().numberFor(realLetter);
        Character guessedLetter = gameRun.session().state().guessFor(number);
        return guessedLetter == null ? '_' : guessedLetter;
    }

    private static Character getCharacter(Word word, int pickColumn) {
        return word.letters().get(pickColumn);
    }
}
