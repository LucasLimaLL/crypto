package br.com.lucaslima.criptograma.features.game.application;


import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameState;

public final class UndoUseCase {

    public GameRun execute(GameRun gameRun) {
        if (!gameRun.history().canUndo()) {
            return gameRun;
        }

        GameState currentState = gameRun.session().state();
        gameRun.history().pushFuture(currentState);

        GameState previousState = gameRun.history().popPast();
        return gameRun.withSession(gameRun.session().withState(previousState));
    }
}
