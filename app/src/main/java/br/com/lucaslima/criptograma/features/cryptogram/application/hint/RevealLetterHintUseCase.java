package br.com.lucaslima.criptograma.features.cryptogram.application.hint;

import br.com.lucaslima.criptograma.features.cryptogram.application.hint.policies.RevealLetterHintPolicy;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameRun;

public final class RevealLetterHintUseCase {

    private final RevealLetterHintPolicy revealLetterHintPolicy;

    public RevealLetterHintUseCase(RevealLetterHintPolicy revealLetterHintPolicy) {
        validatePolicy(revealLetterHintPolicy);
        this.revealLetterHintPolicy = revealLetterHintPolicy;
    }


    public GameRun execute(GameRun gameRun, RevealLetterHintCommand command) {
        validateGameRun(gameRun);
        validateCommand(command);

        return revealLetterHintPolicy.apply(gameRun, command);

    }

    private void validatePolicy(RevealLetterHintPolicy revealLetterHintPolicy) {
        if (revealLetterHintPolicy == null) {
            throw new IllegalArgumentException("Policy must not be null");
        }
    }

    private void validateCommand(RevealLetterHintCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Command must not be null");
        }
    }

    private void validateGameRun(GameRun gameRun) {
        if (gameRun == null) {
            throw new IllegalArgumentException("GameRun must not be null");
        }

    }
}
