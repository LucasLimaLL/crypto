package br.com.lucaslima.criptograma.features.game.application;


import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameState;

public final class ClearLetterUseCase {

    public GameRun execute(GameRun gameRun, int number) {
        GameState currentState = gameRun.session().state();

        gameRun.history().pushPast(currentState);
        gameRun.history().clearFuture();

        GameState nextState = currentState.withoutGuess(number);
        return gameRun.withSession(gameRun.session().withState(nextState));
    }
}
