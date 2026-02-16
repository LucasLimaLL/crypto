package br.com.lucaslima.criptograma.features.cryptogram.application.hint;

public record SpecificRevealLetterHintCommand(int index) implements RevealLetterHintCommand {

    public SpecificRevealLetterHintCommand {
        if(index < 0) {
            throw new IllegalArgumentException("index must be >= 0");
        }
    }

}
