package br.com.lucaslima.criptograma.features.cryptogram.application;


import br.com.lucaslima.criptograma.features.cryptogram.domain.GameRun;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameState;

public final class RedoUseCase {

    public GameRun execute(GameRun gameRun) {
        if (!gameRun.history().canRedo()) {
            return gameRun;
        }

        GameState currentState = gameRun.session().state();
        gameRun.history().pushPast(currentState);

        GameState nextState = gameRun.history().popFuture();
        return gameRun.withSession(gameRun.session().withState(nextState));
    }
}
