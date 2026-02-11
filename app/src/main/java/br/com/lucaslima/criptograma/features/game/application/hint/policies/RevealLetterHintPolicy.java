package br.com.lucaslima.criptograma.features.game.application.hint.policies;

import br.com.lucaslima.criptograma.features.game.application.hint.RevealLetterHintCommand;
import br.com.lucaslima.criptograma.features.game.domain.GameRun;

public interface RevealLetterHintPolicy {
    
    GameRun apply(GameRun gameRun, RevealLetterHintCommand command);
}
