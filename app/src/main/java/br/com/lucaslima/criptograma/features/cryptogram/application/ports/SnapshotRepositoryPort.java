package br.com.lucaslima.criptograma.features.cryptogram.application.ports;

import br.com.lucaslima.criptograma.features.cryptogram.domain.GameSnapshot;

public interface SnapshotRepositoryPort {

    GameSnapshot loadLatestOrNull();

    void save(GameSnapshot gameSnapshot);

}
