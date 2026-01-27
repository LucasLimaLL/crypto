package br.com.lucaslima.criptograma.features.game.application;


import br.com.lucaslima.criptograma.features.game.application.ports.GameSessionLoaderPort;
import br.com.lucaslima.criptograma.features.game.application.ports.SnapshotRepositoryPort;
import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameSession;
import br.com.lucaslima.criptograma.features.game.domain.GameSnapshot;
import br.com.lucaslima.criptograma.features.game.domain.GameState;
import br.com.lucaslima.criptograma.features.game.domain.UndoRedoStack;

public final class RestoreGameUseCase {

    private final SnapshotRepositoryPort snapshotRepositoryPort;
    private final GameSessionLoaderPort gameSessionLoaderPort;

    public RestoreGameUseCase(SnapshotRepositoryPort snapshotRepositoryPort,
                              GameSessionLoaderPort gameSessionLoaderPort) {
        this.snapshotRepositoryPort = snapshotRepositoryPort;
        this.gameSessionLoaderPort = gameSessionLoaderPort;
    }

    public GameRun executeOrNull() {
        GameSnapshot gameSnapshot = snapshotRepositoryPort.loadLatestOrNull();
        if (gameSnapshot == null) {
            return null;
        }

        GameSession gameSession = gameSessionLoaderPort
                .loadBy(gameSnapshot.sessionId, gameSnapshot.puzzleId)
                .withState(new GameState(gameSnapshot.guesses));

        UndoRedoStack undoRedoStack = new UndoRedoStack();
        return new GameRun(gameSession, undoRedoStack);
    }



}
