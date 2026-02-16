package br.com.lucaslima.criptograma.features.cryptogram.exceptions;

import br.com.lucaslima.criptograma.features.cryptogram.domain.Word;

public class WordNotFoundException extends RuntimeException {

    public WordNotFoundException(Word word) {
        super("A palavra " + word.code() + " não foi encontrada");
    }
}
