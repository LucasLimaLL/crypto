package br.com.lucaslima.criptograma.features.game.data.puzzle.mapper;

import br.com.lucaslima.criptograma.features.game.data.mapper.DataMapper;
import br.com.lucaslima.criptograma.features.game.data.puzzle.PuzzleWordDefinition;
import br.com.lucaslima.criptograma.features.game.domain.Word;

public class WordDomainMapper extends DataMapper<PuzzleWordDefinition, Word> {
    @Override
    public PuzzleWordDefinition toEntity(Word domain) {
        return null;
    }

    @Override
    public Word toDomain(PuzzleWordDefinition entity) {
        return null;
    }
}
