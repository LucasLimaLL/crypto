package br.com.lucaslima.criptograma.features.game.application;

import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameSession;
import br.com.lucaslima.criptograma.features.game.domain.GameState;
import br.com.lucaslima.criptograma.features.game.domain.UndoRedoStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given a RedoUseCase")
class RedoUseCaseTest {

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
    private RedoUseCase redoUseCase;

    @Nested
    @DisplayName("When redo is possible")
    class WhenRedoIsPossible {
        @Test
        @DisplayName("Then it should advance to the next state and update history")
        void givenRedoIsPossibleWhenExecuteThenAdvanceStateAndUpdateHistory() {
            when(gameRun.history()).thenReturn(history);
            when(history.canRedo()).thenReturn(true);
            when(gameRun.session()).thenReturn(session);
            when(session.state()).thenReturn(currentState);
            when(history.popFuture()).thenReturn(nextState);
            when(session.withState(nextState)).thenReturn(session);
            when(gameRun.withSession(session)).thenReturn(gameRun);

            GameRun result = redoUseCase.execute(gameRun);

            verify(history).pushPast(currentState);
            verify(history).popFuture();
            assertEquals(gameRun, result);
        }
    }

    @Nested
    @DisplayName("When redo is not possible")
    class WhenRedoIsNotPossible {
        @Test
        @DisplayName("Then it should do nothing and return the current game run")
        void givenRedoIsNotPossibleWhenExecuteThenDoNothing() {
            when(gameRun.history()).thenReturn(history);
            when(history.canRedo()).thenReturn(false);

            GameRun result = redoUseCase.execute(gameRun);

            assertEquals(gameRun, result);
        }
    }
}
