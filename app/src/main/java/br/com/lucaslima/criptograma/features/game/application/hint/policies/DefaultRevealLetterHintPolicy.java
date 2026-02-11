package br.com.lucaslima.criptograma.features.game.application.hint.policies;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import br.com.lucaslima.criptograma.features.game.application.hint.RandomRevealLetterHintCommand;
import br.com.lucaslima.criptograma.features.game.application.hint.RevealLetterHintCommand;
import br.com.lucaslima.criptograma.features.game.application.hint.SpecificRevealLetterHintCommand;
import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameState;

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
                .filter(letter -> gameRun.session().state().hasGuessedLetter(letter))
                .collect(Collectors.toList());

        if (isAllowedToReveal(unresolvedLetters)) {
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
        }

        if (command instanceof SpecificRevealLetterHintCommand specificRevealLetterHintCommand) {
            int index = specificRevealLetterHintCommand.index();
            int pickColumn = gameRun.session().puzzle().pickColumn();

            return gameRun
                    .session()
                    .puzzle()
                    .words()
                    .get(index)
                    .letters()
                    .get(pickColumn);
        }


        throw new IllegalStateException("Unknown command: " + command.getClass().getName());
    }

    private void validateRandom(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("random must not be null");
        }
    }

    private static boolean isAllowedToReveal(List<Character> unresolvedLetters) {
        return unresolvedLetters.size() <= 3;
    }
}
