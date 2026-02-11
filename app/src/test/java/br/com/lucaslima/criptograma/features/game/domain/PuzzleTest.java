package br.com.lucaslima.criptograma.features.game.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Given a Puzzle")
class PuzzleTest {

    @Nested
    @DisplayName("When a new instance is created")
    class WhenNewInstance {

        @Test
        @DisplayName("Then it should create successfully with valid arguments")
        void givenValidArgumentsWhenNewInstanceThenCreateSuccessfully() {
            Puzzle puzzle = new Puzzle("id", 0, "finalHint", List.of(new Word("CODE", "hint", List.of('C', 'O', 'D', 'E'))));
            assertEquals("id", puzzle.id());
            assertEquals("finalHint", puzzle.finalHint());
            assertEquals(1, puzzle.words().size());
        }

        @Test
        @DisplayName("And the final hint is null, then it should be initialized as an empty string")
        void givenNullFinalHintWhenNewInstanceThenHintIsEmpty() {
            Puzzle puzzleWithNullHint = new Puzzle("id", 0, null, List.of(new Word("CODE", "hint", List.of('C', 'O', 'D', 'E'))));
            assertTrue(puzzleWithNullHint.finalHint().isEmpty());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"  ", "\t", "\n"})
        @DisplayName("And the id is null or blank, then it should throw IllegalArgumentException")
        void givenNullOrBlankIdWhenNewInstanceThenThrowException(String blankId) {
            assertThrows(IllegalArgumentException.class, () -> new Puzzle(blankId, 0, "finalHint", List.of(new Word("CODE", "hint", List.of('C', 'O', 'D', 'E')))));
        }

        @Test
        @DisplayName("And the pick column is negative, then it should throw IllegalArgumentException")
        void givenNegativePickColumnWhenNewInstanceThenThrowException() {
            assertThrows(IllegalArgumentException.class, () -> new Puzzle("id", -1, "finalHint", List.of(new Word("CODE", "hint", List.of('C', 'O', 'D', 'E')))));
        }

        @Test
        @DisplayName("And the word list is null, then it should throw IllegalArgumentException")
        void givenNullWordListWhenNewInstanceThenThrowException() {
            assertThrows(IllegalArgumentException.class, () -> new Puzzle("id", 0, "finalHint", null));
        }

        @Test
        @DisplayName("And the word list is empty, then it should throw IllegalArgumentException")
        void givenEmptyWordListWhenNewInstanceThenThrowException() {
            assertThrows(IllegalArgumentException.class, () -> new Puzzle("id", 0, "finalHint", Collections.emptyList()));
        }

        @Test
        @DisplayName("And words have different lengths, then it should throw IllegalArgumentException")
        void givenWordsWithDifferentLengthsWhenNewInstanceThenThrowException() {
            Word word1 = new Word("ABC", "hint", List.of('A', 'B', 'C'));
            Word word2 = new Word("DE", "hint", List.of('D', 'E'));
            List<Word> words = List.of(word1, word2);

            assertThrows(IllegalArgumentException.class, () -> new Puzzle("id", 0, "finalHint", words));
        }

        @Test
        @DisplayName("And the pick column is out of bounds, then it should throw IllegalArgumentException")
        void givenPickColumnOutOfBoundsWhenNewInstanceThenThrowException() {
            Word word = new Word("ABC", "hint", List.of('A', 'B', 'C'));
            assertThrows(IllegalArgumentException.class, () -> new Puzzle("id", 3, "finalHint", List.of(word)));
        }
    }
}
