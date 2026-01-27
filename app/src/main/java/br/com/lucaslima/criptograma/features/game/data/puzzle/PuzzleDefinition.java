package br.com.lucaslima.criptograma.features.game.data.puzzle;

import java.util.List;

public record PuzzleDefinition(String puzzleId, int pickColumn, String finalHunt, List<PuzzleWordDefinition> words) {


}
