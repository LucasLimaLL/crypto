package br.com.lucaslima.criptograma.features.game.domain;

import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Given a GameState")
class GameStateTest {

    @Nested
    @DisplayName("When a new instance is created")
    class WhenNewInstance {

        @Test
        @DisplayName("Then it should create successfully with valid arguments")
        void givenValidArgumentsWhenNewInstanceThenCreateSuccessfully() {
            GameState gameState = Instancio.create(GameState.class);
            assertEquals(gameState.guesses(), gameState.guesses());
        }

        @Test
        @DisplayName("And the guesses map is null, then it should throw IllegalArgumentException")
        void givenNullGuessesWhenNewInstanceThenThrowException() {
            assertThrows(IllegalArgumentException.class, () -> new GameState(null));
        }

        @Test
        @DisplayName("And the guess map contains lowercase and accented letters, then they should be normalized")
        void givenLowercaseAndAccentedLettersWhenNewInstanceThenNormalize() {
            Map<Integer, Character> guesses = Map.of(1, 'à', 2, 'B', 3, 'ç');
            GameState gameState = new GameState(guesses);
            Map<Integer, Character> expectedGuesses = Map.of(1, 'A', 2, 'B', 3, 'C');
            assertEquals(expectedGuesses, gameState.guesses());
        }
    }

    @Nested
    @DisplayName("When withGuess is called")
    class WhenWithGuess {

        @Test
        @DisplayName("And the number and letter are valid, then it should add the guess")
        void givenValidNumberAndLetterWhenWithGuessThenAddGuess() {
            GameState initialGameState = GameState.empty();
            GameState newGameState = initialGameState.withGuess(1, 'A');
            assertEquals('A', newGameState.guessFor(1));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        @DisplayName("And the number is invalid, then it should throw IllegalArgumentException")
        void givenInvalidNumberWhenWithGuessThenThrowException(int invalidNumber) {
            GameState gameState = GameState.empty();
            assertThrows(IllegalArgumentException.class, () -> gameState.withGuess(invalidNumber, 'A'));
        }

        @ParameterizedTest
        @ValueSource(chars = {'1', '@', ' '})
        @DisplayName("And the letter is invalid, then it should throw IllegalArgumentException")
        void givenInvalidLetterWhenWithGuessThenThrowException(char invalidLetter) {
            GameState gameState = GameState.empty();
            assertThrows(IllegalArgumentException.class, () -> gameState.withGuess(1, invalidLetter));
        }
    }

    @Nested
    @DisplayName("When withoutGuess is called")
    class WhenWithoutGuess {

        @Test
        @DisplayName("And the guess exists, then it should be removed")
        void givenExistingGuessWhenWithoutGuessThenRemoveGuess() {
            GameState initialGameState = new GameState(Map.of(1, 'A'));
            GameState newGameState = initialGameState.withoutGuess(1);
            assertNull(newGameState.guessFor(1));
        }

        @Test
        @DisplayName("And the guess does not exist, then it should do nothing")
        void givenNonExistingGuessWhenWithoutGuessThenDoNothing() {
            GameState initialGameState = GameState.empty();
            GameState newGameState = initialGameState.withoutGuess(1);
            assertEquals(initialGameState, newGameState);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        @DisplayName("And the number is invalid, then it should throw IllegalArgumentException")
        void givenInvalidNumberWhenWithoutGuessThenThrowException(int invalidNumber) {
            GameState gameState = GameState.empty();
            assertThrows(IllegalArgumentException.class, () -> gameState.withoutGuess(invalidNumber));
        }
    }
    
    @Nested
    @DisplayName("When hasGuessedLetter is called")
    class WhenHasGuessedLetter {

        @Test
        @DisplayName("And the letter has been guessed, then it should return true")
        void givenGuessedLetterWhenHasGuessedLetterThenReturnTrue() {
            GameState gameState = new GameState(Map.of(1, 'A'));
            assertTrue(gameState.hasGuessedLetter('A'));
        }

        @Test
        @DisplayName("And the letter has not been guessed, then it should return false")
        void givenNotGuessedLetterWhenHasGuessedLetterThenReturnFalse() {
            GameState gameState = GameState.empty();
            assertFalse(gameState.hasGuessedLetter('A'));
        }
    }

    @Nested
    @DisplayName("When hasGuessedNumber is called")
    class WhenHasGuessedNumber {

        @Test
        @DisplayName("And the number has been guessed, then it should return true")
        void givenGuessedNumberWhenHasGuessedNumberThenReturnTrue() {
            GameState gameState = new GameState(Map.of(1, 'A'));
            assertTrue(gameState.hasGuessedNumber(1));
        }

        @Test
        @DisplayName("And the number has not been guessed, then it should return false")
        void givenNotGuessedNumberWhenHasGuessedNumberThenReturnFalse() {
            GameState gameState = GameState.empty();
            assertFalse(gameState.hasGuessedNumber(1));
        }
    }
}
