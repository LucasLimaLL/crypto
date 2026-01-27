package br.com.lucaslima.criptograma.features.game.application;


import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameState;

public final class AssignLetterUseCase {

    public GameRun execute(GameRun gameRun, int number, char letter) {
        GameState currentState = gameRun.session().state();

        gameRun.history().pushPast(currentState);
        gameRun.history().clearFuture();

        GameState nextState = currentState.withGuess(number, letter);
        return gameRun.withSession(gameRun.session().withState(nextState));
    }
}
