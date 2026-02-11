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
@DisplayName("Given an UndoUseCase")
class UndoUseCaseTest {

    @Mock
    private GameRun gameRun;

    @Mock
    private GameSession session;

    @Mock
    private GameState currentState;

    @Mock
    private GameState previousState;

    @Mock
    private UndoRedoStack history;

    @InjectMocks
    private UndoUseCase undoUseCase;

    @Nested
    @DisplayName("When undo is possible")
    class WhenUndoIsPossible {
        @Test
        @DisplayName("Then it should revert to the previous state and update history")
        void givenUndoIsPossibleWhenExecuteThenRevertStateAndUpdateHistory() {
            when(gameRun.history()).thenReturn(history);
            when(history.canUndo()).thenReturn(true);
            when(gameRun.session()).thenReturn(session);
            when(session.state()).thenReturn(currentState);
            when(history.popPast()).thenReturn(previousState);
            when(session.withState(previousState)).thenReturn(session);
            when(gameRun.withSession(session)).thenReturn(gameRun);

            GameRun result = undoUseCase.execute(gameRun);

            verify(history).pushFuture(currentState);
            verify(history).popPast();
            assertEquals(gameRun, result);
        }
    }

    @Nested
    @DisplayName("When undo is not possible")
    class WhenUndoIsNotPossible {
        @Test
        @DisplayName("Then it should do nothing and return the current game run")
        void givenUndoIsNotPossibleWhenExecuteThenDoNothing() {
            when(gameRun.history()).thenReturn(history);
            when(history.canUndo()).thenReturn(false);

            GameRun result = undoUseCase.execute(gameRun);

            assertEquals(gameRun, result);
        }
    }
}
