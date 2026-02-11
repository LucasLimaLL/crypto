package br.com.lucaslima.criptograma.features.game.application.hint;

import br.com.lucaslima.criptograma.features.game.domain.GameRun;

public sealed interface RevealLetterHintCommand permits RandomRevealLetterHintCommand, SpecificRevealLetterHintCommand{

}
