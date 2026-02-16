package br.com.lucaslima.criptograma.features.cryptogram.data.puzzle;

import java.util.Optional;

public interface PuzzleDefinitionRepository {

    PuzzleDefinition getRandomPuzzleDefinition();

    Optional<PuzzleDefinition> getPuzzleDefinition(String id);

}
