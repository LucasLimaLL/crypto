package br.com.lucaslima.criptograma.features.game.data.puzzle.local;

import java.util.Optional;

import br.com.lucaslima.criptograma.features.game.data.puzzle.PuzzleDefinition;
import br.com.lucaslima.criptograma.features.game.data.puzzle.PuzzleDefinitionRepository;

public class InMemoryPuzzleDefinitionRepository implements PuzzleDefinitionRepository {

    @Override
    public PuzzleDefinition getRandoPuzzleDefinition() {
        return null;
    }

    @Override
    public Optional<PuzzleDefinition> getPuzzleDefinition(String id) {
        return Optional.empty();
    }
}
