package br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.PuzzleWordDefinition;
import br.com.lucaslima.criptograma.features.cryptogram.domain.Word;

class WordDomainMapperTest {

    private final WordDomainMapper mapper = new WordDomainMapper();


    @Test
    @DisplayName("Given a WordDomainMapper, When toEntity is called And the domain object is null, then it should return null")
    void givenNullDomainWhenToEntityThenReturnNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    @DisplayName("Given a WordDomainMapper, When toEntity is called And the domain object is valid, then it should return a valid entity")
    void givenValidDomainWhenToEntityThenReturnEntity() {

        Word word = new Word("CODE", "A hint", List.of('C', 'O', 'D', 'E'));


        PuzzleWordDefinition entity = mapper.toEntity(word);


        assertEquals("CODE", entity.wordCode());
        assertEquals("A hint", entity.wordHint());
        assertEquals("CODE", entity.wordText());
    }


    @Test
    @DisplayName("Given a WordDomainMapper, When toDomain is called And the entity object is null, then it should return null")
    void givenNullEntityWhenToDomainThenReturnNull() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    @DisplayName("And the entity object is valid, then it should return a valid domain object")
    void givenValidEntityWhenToDomainThenReturnDomain() {

        PuzzleWordDefinition entity = new PuzzleWordDefinition("CODE", "A hint", "CODE");

        Word word = mapper.toDomain(entity);

        assertEquals("CODE", word.code());
        assertEquals("A hint", word.hint());
        assertEquals(List.of('C', 'O', 'D', 'E'), word.letters());

    }
}
