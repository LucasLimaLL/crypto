package br.com.lucaslima.criptograma.features.cryptogram.ui.model;

import java.util.List;

public record CryptogramWordModel(String hint, List<CryptogramCellModel> cells) {
}
