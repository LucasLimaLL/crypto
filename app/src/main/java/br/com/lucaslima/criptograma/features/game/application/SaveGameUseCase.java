package br.com.lucaslima.criptograma.features.game.application;


import java.util.List;

import br.com.lucaslima.criptograma.features.game.application.ports.SnapshotRepositoryPort;
import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameSnapshot;

public final class SaveGameUseCase {

    private final SnapshotRepositoryPort snapshotRepositoryPort;

    public SaveGameUseCase(SnapshotRepositoryPort snapshotRepositoryPort) {
        this.snapshotRepositoryPort = snapshotRepositoryPort;
    }

    public void execute(GameRun gameRun) {
        GameSnapshot gameSnapshot = new GameSnapshot(
                gameRun.session().sessionId(),
                gameRun.session().puzzle().id(),
                gameRun.session().state().guesses(),
                List.of(),
                List.of()
        );

        snapshotRepositoryPort.save(gameSnapshot);
    }
}
