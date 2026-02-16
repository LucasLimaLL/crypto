package br.com.lucaslima.criptograma.features.cryptogram.ui.model;

import java.util.List;

public record ContentCryptogramUiState(String sessionId, String puzzleHint, List<CryptogramWordModel> words, CryptogramHighlightedCellsModel highlightedCells, boolean hintEnabled, boolean undoEnabled, boolean redoEnabled) implements CryptogramUiState {
}
