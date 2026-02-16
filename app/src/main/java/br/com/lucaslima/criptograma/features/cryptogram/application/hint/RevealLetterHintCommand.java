package br.com.lucaslima.criptograma.features.cryptogram.application.hint;

public sealed interface RevealLetterHintCommand permits RandomRevealLetterHintCommand, SpecificRevealLetterHintCommand{

}
