package br.com.lucaslima.criptograma.features.cryptogram.application.hint.policies;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import br.com.lucaslima.criptograma.features.cryptogram.application.hint.RandomRevealLetterHintCommand;
import br.com.lucaslima.criptograma.features.cryptogram.application.hint.RevealLetterHintCommand;
import br.com.lucaslima.criptograma.features.cryptogram.application.hint.SpecificRevealLetterHintCommand;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameRun;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameState;

public final class DefaultRevealLetterHintPolicy implements RevealLetterHintPolicy {

    private final Random random;

    public DefaultRevealLetterHintPolicy() {
        this(new Random());
    }

    public DefaultRevealLetterHintPolicy(Random random) {
        validateRandom(random);
        this.random = random;
    }


    @Override
    public GameRun apply(GameRun gameRun, RevealLetterHintCommand command) {

        List<Character> unresolvedLetters = gameRun
                .session()
                .puzzle()
                .words()
                .stream()
                .flatMap(word -> word.unresolvedLetters().stream())
                .distinct()
                .filter(letter -> !gameRun.session().state().hasGuessedLetter(letter))
                .collect(Collectors.toList());

        if (isNotAllowedToReveal(unresolvedLetters)) {
            return gameRun;
        }

        char revealedLetter = revealLetter(gameRun, command, unresolvedLetters);
        int number = gameRun
                .session()
                .template().numberFor(revealedLetter);

        if (gameRun.session().state().hasGuessedNumber(number)) {
            return gameRun;
        }

        GameState currentState = gameRun.session().state();
        gameRun.history().pushPast(currentState);
        gameRun.history().clearFuture();

        GameState nextState = currentState.withGuess(number, revealedLetter);

        return gameRun.withSession(gameRun.session().withState(nextState));

    }

    private char revealLetter(GameRun gameRun, RevealLetterHintCommand command, List<Character> unresolvedLetters) {
        if (command instanceof RandomRevealLetterHintCommand) {
            return unresolvedLetters.get(random.nextInt(unresolvedLetters.size()));
        } else if (command instanceof SpecificRevealLetterHintCommand specificCommand) {
            int index = specificCommand.index();
            int pickColumn = gameRun.session().puzzle().pickColumn();

            List<Character> lettersOnly = gameRun
                    .session()
                    .puzzle()
                    .words()
                    .get(index)
                    .letters()
                    .stream()
                    .filter(c -> c != ' ')
                    .collect(Collectors.toList());

            return lettersOnly.get(pickColumn);
        } else {
            throw new IllegalStateException("Unknown command: " + command.getClass().getName());
        }
    }

    private void validateRandom(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("random must not be null");
        }
    }

    private static boolean isNotAllowedToReveal(List<Character> unresolvedLetters) {
        return unresolvedLetters.size() <= 3;
    }
}
