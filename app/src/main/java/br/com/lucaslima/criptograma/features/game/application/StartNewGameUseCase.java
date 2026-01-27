package br.com.lucaslima.criptograma.features.game.application;


import br.com.lucaslima.criptograma.features.game.application.ports.GameSessionFactoryPort;
import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameSession;
import br.com.lucaslima.criptograma.features.game.domain.UndoRedoStack;

public final class StartNewGameUseCase {

    private final GameSessionFactoryPort gameSessionFactoryPort;

    public StartNewGameUseCase(GameSessionFactoryPort gameSessionFactoryPort) {
        this.gameSessionFactoryPort = gameSessionFactoryPort;
    }

    public GameRun execute() {
        GameSession gameSession = gameSessionFactoryPort.createNewSession();
        UndoRedoStack undoRedoStack = new UndoRedoStack();
        return new GameRun(gameSession, undoRedoStack);
    }


}
