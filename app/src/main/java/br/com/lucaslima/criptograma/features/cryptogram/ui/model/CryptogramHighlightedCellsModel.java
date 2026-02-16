package br.com.lucaslima.criptograma.features.cryptogram.ui.model;

import java.util.List;

public record CryptogramHighlightedCellsModel(String highlightHint, List<CryptogramHighlightedCellModel> cells) {
}
