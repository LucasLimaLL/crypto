package br.com.lucaslima.criptograma.features.game.application;

import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameSession;
import br.com.lucaslima.criptograma.features.game.domain.GameState;
import br.com.lucaslima.criptograma.features.game.domain.UndoRedoStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given an AssignLetterUseCase")
class AssignLetterUseCaseTest {

    @Mock
    private GameRun gameRun;

    @Mock
    private GameSession session;

    @Mock
    private GameState currentState;

    @Mock
    private GameState nextState;

    @Mock
    private UndoRedoStack history;

    @InjectMocks
    private AssignLetterUseCase assignLetterUseCase;

    @Test
    @DisplayName("When a letter is assigned, then it should update the game state and history")
    void givenLetterToAssignWhenExecuteThenUpdateStateAndHistory() {
        int numberToAssign = 5;
        char letterToAssign = 'X';

        when(gameRun.session()).thenReturn(session);
        when(session.state()).thenReturn(currentState);
        when(gameRun.history()).thenReturn(history);
        when(currentState.withGuess(numberToAssign, letterToAssign)).thenReturn(nextState);
        when(session.withState(nextState)).thenReturn(session);
        when(gameRun.withSession(session)).thenReturn(gameRun);

        GameRun result = assignLetterUseCase.execute(gameRun, numberToAssign, letterToAssign);

        verify(history).pushPast(currentState);
        verify(history).clearFuture();
        verify(currentState).withGuess(numberToAssign, letterToAssign);
        assertEquals(gameRun, result);
    }
}
