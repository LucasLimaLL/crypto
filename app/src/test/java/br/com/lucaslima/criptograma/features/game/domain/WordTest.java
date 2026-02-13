package br.com.lucaslima.criptograma.features.game.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Given a Word")
class WordTest {

    @Nested
    @DisplayName("When a new instance is created")
    class WhenNewInstance {

        @Test
        @DisplayName("Then it should create successfully with valid arguments")
        void givenValidArgumentsWhenNewInstanceThenCreateSuccessfully() {
            Word word = new Word("CODE", "hint", List.of('C', 'O', 'D', 'E'));
            assertEquals("CODE", word.code());
            assertEquals("hint", word.hint());
            assertEquals(4, word.length());
        }

        @Test
        @DisplayName("And the hint is null, then it should be initialized as an empty string")
        void givenNullHintWhenNewInstanceThenHintIsEmpty() {
            Word wordWithNullHint = new Word("CODE", null, List.of('C', 'O', 'D', 'E'));
            assertTrue(wordWithNullHint.hint().isEmpty());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"  ", "\t", "\n"})
        @DisplayName("And the code is null or blank, then it should throw IllegalArgumentException")
        void givenNullOrBlankCodeWhenNewInstanceThenThrowException(String blankCode) {
            assertThrows(IllegalArgumentException.class, () -> new Word(blankCode, "hint", List.of('A')));
        }

        @Test
        @DisplayName("And the letter list is null, then it should throw IllegalArgumentException")
        void givenNullLetterListWhenNewInstanceThenThrowException() {
            assertThrows(IllegalArgumentException.class, () -> new Word("code", "hint", null));
        }

        @Test
        @DisplayName("And the letter list is empty, then it should throw IllegalArgumentException")
        void givenEmptyLetterListWhenNewInstanceThenThrowException() {
            assertThrows(IllegalArgumentException.class, () -> new Word("code", "hint", Collections.emptyList()));
        }

        @Test
        @DisplayName("And the letter list contains null, then it should throw IllegalArgumentException")
        void givenLetterListWithNullWhenNewInstanceThenThrowException() {
            List<Character> letters = new ArrayList<>();
            letters.add('A');
            letters.add(null);
            letters.add('C');
            assertThrows(IllegalArgumentException.class, () -> new Word("code", "hint", letters));
        }

        @ParameterizedTest
        @MethodSource("br.com.lucaslima.criptograma.features.game.domain.WordTest#invalidCharacters")
        @DisplayName("And the letter list contains invalid characters, then it should throw IllegalArgumentException")
        void givenLetterListWithInvalidCharsWhenNewInstanceThenThrowException(Character invalidChar) {
            assertThrows(IllegalArgumentException.class, () -> new Word("code", "hint", List.of('A', invalidChar, 'C')));
        }

        @Test
        @DisplayName("And letters are lowercase and with accents, then they should be normalized to uppercase and without accents")
        void givenLowercaseAndAccentedLettersWhenNewInstanceThenNormalize() {
            List<Character> mixedCaseLetters = List.of('à', 'B', 'ç', 'd', 'É');
            Word word = new Word("code", "hint", mixedCaseLetters);

            List<Character> expectedLetters = List.of('A', 'B', 'C', 'D', 'E');
            assertEquals(expectedLetters, word.letters());
        }
    }

    private static Stream<Character> invalidCharacters() {
        return Stream.of('1', '@', '-');
    }
}
