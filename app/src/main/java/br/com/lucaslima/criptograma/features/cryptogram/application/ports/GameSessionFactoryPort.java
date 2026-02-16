package br.com.lucaslima.criptograma.features.cryptogram.application.ports;

import br.com.lucaslima.criptograma.features.cryptogram.domain.GameSession;

public interface GameSessionFactoryPort {
    GameSession createNewSession();
}
