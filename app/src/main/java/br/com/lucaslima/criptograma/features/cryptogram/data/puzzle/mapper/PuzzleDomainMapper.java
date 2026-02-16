package br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.mapper;

import java.util.stream.Collectors;

import br.com.lucaslima.criptograma.features.cryptogram.data.mapper.DataMapper;
import br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.PuzzleDefinition;
import br.com.lucaslima.criptograma.features.cryptogram.domain.Puzzle;

public class PuzzleDomainMapper extends DataMapper<PuzzleDefinition, Puzzle> {

    private final WordDomainMapper wordDomainMapper;

    public PuzzleDomainMapper() {
        this.wordDomainMapper = new WordDomainMapper();
    }

    @Override
    public PuzzleDefinition toEntity(Puzzle domain) {
        return domain == null
                ? null
                : new PuzzleDefinition(
                domain.id(),
                domain.pickColumn(),
                domain.finalHint(),
                domain
                        .words()
                        .stream()
                        .map(wordDomainMapper::toEntity)
                        .collect(Collectors.toList()));
    }

    @Override
    public Puzzle toDomain(PuzzleDefinition entity) {
        return entity == null
                ? null
                : new Puzzle(
                entity.puzzleId(),
                entity.pickColumn(),
                entity.finalHunt(),
                entity
                        .words()
                        .stream()
                        .map(wordDomainMapper::toDomain)
                        .collect(Collectors.toList()));
    }
}
