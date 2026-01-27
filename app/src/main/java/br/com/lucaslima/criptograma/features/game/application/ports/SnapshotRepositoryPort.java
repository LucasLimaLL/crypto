package br.com.lucaslima.criptograma.features.game.application.ports;

import br.com.lucaslima.criptograma.features.game.domain.GameSnapshot;

public interface SnapshotRepositoryPort {

    GameSnapshot loadLatestOrNull();

    void save(GameSnapshot gameSnapshot);

}
