package br.com.lucaslima.criptograma.features.cryptogram.application.hint.policies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Random;

import br.com.lucaslima.criptograma.features.cryptogram.application.hint.RandomRevealLetterHintCommand;
import br.com.lucaslima.criptograma.features.cryptogram.application.hint.SpecificRevealLetterHintCommand;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameRun;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameSession;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameState;
import br.com.lucaslima.criptograma.features.cryptogram.domain.Puzzle;
import br.com.lucaslima.criptograma.features.cryptogram.domain.Template;
import br.com.lucaslima.criptograma.features.cryptogram.domain.UndoRedoStack;
import br.com.lucaslima.criptograma.features.cryptogram.domain.Word;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("Given a DefaultRevealLetterHintPolicy")
class DefaultRevealLetterHintPolicyTest {

    @Mock
    private Random random;

    @Mock
    private GameRun gameRun;

    @Mock
    private GameSession session;

    @Mock
    private Puzzle puzzle;

    @Mock
    private Template template;

    @Mock
    private GameState currentState;

    @Mock
    private GameState nextState;

    @Mock
    private UndoRedoStack history;

    @Mock
    private Word word;

    private DefaultRevealLetterHintPolicy policy;

    @Nested
    @DisplayName("When apply is called")
    class WhenApply {

        @Test
        @DisplayName("And the command is Random, then it should reveal a random letter")
        void givenRandomCommandWhenApplyThenRevealRandomLetter() {
            
            setupGameRunWithUnresolvedLetters(List.of('A', 'B', 'C', 'D', 'E'));
            when(random.nextInt(5)).thenReturn(2);
            when(template.numberFor('C')).thenReturn(10);
            when(currentState.withGuess(10, 'C')).thenReturn(nextState);

            policy = new DefaultRevealLetterHintPolicy(random);
            policy.apply(gameRun, new RandomRevealLetterHintCommand());

            verify(history).pushPast(currentState);
            verify(history).clearFuture();
            verify(gameRun).withSession(session.withState(nextState));
        }

        @Test
        @DisplayName("And the command is Specific, then it should reveal the correct letter")
        void givenSpecificCommandWhenApplyThenRevealCorrectLetter() {
            
            setupGameRunWithUnresolvedLetters(List.of('A', 'B', 'C', 'D', 'E'));
            when(puzzle.words()).thenReturn(List.of(word));
            when(word.letters()).thenReturn(List.of('L', 'U', ' ', 'C', 'A', 'S'));
            when(puzzle.pickColumn()).thenReturn(3); // Should pick 'A'
            when(template.numberFor('A')).thenReturn(12);
            when(currentState.withGuess(12, 'A')).thenReturn(nextState);

            policy = new DefaultRevealLetterHintPolicy();
            policy.apply(gameRun, new SpecificRevealLetterHintCommand(0));

            verify(history).pushPast(currentState);
            verify(history).clearFuture();
            verify(gameRun).withSession(session.withState(nextState));
        }

        @Test
        @DisplayName("And there are too few letters to reveal, then do nothing")
        void givenTooFewLettersWhenApplyThenDoNothing() {
            
            setupGameRunWithUnresolvedLetters(List.of('A', 'B', 'C'));
            policy = new DefaultRevealLetterHintPolicy();

            GameRun result = policy.apply(gameRun, new RandomRevealLetterHintCommand());
            assertEquals(gameRun, result);
        }

        @Test
        @DisplayName("And the revealed letter is already guessed, then do nothing")
        void givenRevealedLetterIsAlreadyGuessedWhenApplyThenDoNothing() {
            
            setupGameRunWithUnresolvedLetters(List.of('A', 'B', 'C', 'D', 'E'));
            when(random.nextInt(5)).thenReturn(2); 
            when(template.numberFor('C')).thenReturn(10);
            when(currentState.hasGuessedNumber(10)).thenReturn(true);

            policy = new DefaultRevealLetterHintPolicy(random);

            GameRun result = policy.apply(gameRun, new RandomRevealLetterHintCommand());
            assertEquals(gameRun, result);
        }
    }

    @Nested
    @DisplayName("When a new instance is created")
    class WhenNewInstance {
        @Test
        @DisplayName("And random is null, then throw exception")
        void givenNullRandomWhenNewInstanceThenThrowException() {
            assertThrows(IllegalArgumentException.class, () -> new DefaultRevealLetterHintPolicy(null));
        }
    }

    private void setupGameRunWithUnresolvedLetters(List<Character> letters) {
        when(gameRun.session()).thenReturn(session);
        when(session.puzzle()).thenReturn(puzzle);
        when(puzzle.words()).thenReturn(List.of(word));
        when(word.unresolvedLetters()).thenReturn(letters);
        when(session.state()).thenReturn(currentState);
        when(session.template()).thenReturn(template);
        when(gameRun.history()).thenReturn(history);
    }
}
