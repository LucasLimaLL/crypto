package br.com.lucaslima.criptograma.features.game.data.puzzle;

import java.util.Optional;

public interface PuzzleDefinitionRepository {

    PuzzleDefinition getRandoPuzzleDefinition();

    Optional<PuzzleDefinition> getPuzzleDefinition(String id);

}
