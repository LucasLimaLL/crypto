package br.com.lucaslima.criptograma.features.game.data.puzzle.mapper;

import br.com.lucaslima.criptograma.features.game.data.mapper.DataMapper;
import br.com.lucaslima.criptograma.features.game.data.puzzle.PuzzleDefinition;
import br.com.lucaslima.criptograma.features.game.domain.Puzzle;

public class PuzzleDomainMapper extends DataMapper<PuzzleDefinition, Puzzle> {
    @Override
    public PuzzleDefinition toEntity(Puzzle domain) {
        return null;
    }

    @Override
    public Puzzle toDomain(PuzzleDefinition entity) {
        return null;
    }
}
