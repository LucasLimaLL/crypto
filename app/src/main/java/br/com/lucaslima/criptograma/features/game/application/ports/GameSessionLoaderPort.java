package br.com.lucaslima.criptograma.features.game.application.ports;

import br.com.lucaslima.criptograma.features.game.domain.GameSession;

public interface GameSessionLoaderPort {
    GameSession loadBy(String sessionId, String puzzleId);
}
