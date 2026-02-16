package br.com.lucaslima.criptograma.features.cryptogram.application.hint.policies;

import br.com.lucaslima.criptograma.features.cryptogram.application.hint.RevealLetterHintCommand;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameRun;

public interface RevealLetterHintPolicy {
    
    GameRun apply(GameRun gameRun, RevealLetterHintCommand command);
}
