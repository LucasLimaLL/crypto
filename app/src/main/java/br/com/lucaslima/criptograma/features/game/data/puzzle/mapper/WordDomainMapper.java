package br.com.lucaslima.criptograma.features.game.data.puzzle.mapper;

import java.util.stream.Collectors;

import br.com.lucaslima.criptograma.features.game.data.mapper.DataMapper;
import br.com.lucaslima.criptograma.features.game.data.puzzle.PuzzleWordDefinition;
import br.com.lucaslima.criptograma.features.game.domain.Word;

public class WordDomainMapper extends DataMapper<PuzzleWordDefinition, Word> {
    @Override
    public PuzzleWordDefinition toEntity(Word domain) {
        return domain == null
                ? null
                : new PuzzleWordDefinition(
                domain.code(),
                domain.hint(),
                domain.letters().stream().map(String::valueOf).collect(Collectors.joining()));

    }

    @Override
    public Word toDomain(PuzzleWordDefinition entity) {
        return entity == null
                ? null
                : new Word(
                entity.wordCode(),
                entity.wordHint(),
                entity.wordText()
                        .chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toList()));
    }
}
