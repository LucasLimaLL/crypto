package br.com.lucaslima.criptograma.features.game.application;

import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameSession;
import br.com.lucaslima.criptograma.features.game.domain.GameState;
import br.com.lucaslima.criptograma.features.game.domain.Puzzle;
import br.com.lucaslima.criptograma.features.game.domain.Template;
import br.com.lucaslima.criptograma.features.game.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("Given a CheckSolvedUseCase")
class CheckSolvedUseCaseTest {

    @Mock
    private GameRun gameRun;

    @Mock
    private GameSession session;

    @Mock
    private Puzzle puzzle;

    @Mock
    private Template template;

    @Mock
    private GameState gameState;

    @InjectMocks
    private CheckSolvedUseCase checkSolvedUseCase;

    @Test
    @DisplayName("When the puzzle is fully solved, then it should return true")
    void givenFullySolvedPuzzleWhenExecuteThenReturnTrue() {
        Word word = new Word("CODE", "hint", List.of('C', 'O', 'D', 'E'));
        when(gameRun.session()).thenReturn(session);
        when(session.puzzle()).thenReturn(puzzle);
        when(puzzle.words()).thenReturn(List.of(word));
        when(session.template()).thenReturn(template);
        when(session.state()).thenReturn(gameState);

        when(template.numberFor('C')).thenReturn(1);
        when(template.numberFor('O')).thenReturn(2);
        when(template.numberFor('D')).thenReturn(3);
        when(template.numberFor('E')).thenReturn(4);

        when(gameState.guessFor(1)).thenReturn('C');
        when(gameState.guessFor(2)).thenReturn('O');
        when(gameState.guessFor(3)).thenReturn('D');
        when(gameState.guessFor(4)).thenReturn('E');

        boolean result = checkSolvedUseCase.execute(gameRun);

        assertTrue(result);
    }

    @Test
    @DisplayName("When the puzzle is partially solved, then it should return false")
    void givenPartiallySolvedPuzzleWhenExecuteThenReturnFalse() {
        Word word = new Word("CODE", "hint", List.of('C', 'O', 'D', 'E'));
        when(gameRun.session()).thenReturn(session);
        when(session.puzzle()).thenReturn(puzzle);
        when(puzzle.words()).thenReturn(List.of(word));
        when(session.template()).thenReturn(template);
        when(session.state()).thenReturn(gameState);

        when(template.numberFor('C')).thenReturn(1);
        when(template.numberFor('O')).thenReturn(2);
        when(template.numberFor('D')).thenReturn(3);
        when(template.numberFor('E')).thenReturn(4);

        when(gameState.guessFor(1)).thenReturn('C');
        when(gameState.guessFor(2)).thenReturn('O');
        when(gameState.guessFor(3)).thenReturn(null); // Missing guess
        when(gameState.guessFor(4)).thenReturn('X'); // Incorrect guess

        boolean result = checkSolvedUseCase.execute(gameRun);

        assertFalse(result);
    }
}
