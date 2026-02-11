package br.com.lucaslima.criptograma.features.game.application;

import br.com.lucaslima.criptograma.features.game.application.ports.SnapshotRepositoryPort;
import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameSession;
import br.com.lucaslima.criptograma.features.game.domain.GameSnapshot;
import br.com.lucaslima.criptograma.features.game.domain.GameState;
import br.com.lucaslima.criptograma.features.game.domain.Puzzle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given a SaveGameUseCase")
class SaveGameUseCaseTest {

    @Mock
    private SnapshotRepositoryPort snapshotRepositoryPort;

    @Mock
    private GameRun gameRun;

    @Mock
    private GameSession session;

    @Mock
    private Puzzle puzzle;

    @Mock
    private GameState gameState;

    @InjectMocks
    private SaveGameUseCase saveGameUseCase;

    @Test
    @DisplayName("When a game is saved, then it should create and save a snapshot")
    void givenGameToSaveWhenExecuteThenCreateAndSaveSnapshot() {
        String sessionId = "session123";
        String puzzleId = "puzzle456";
        Map<Integer, Character> guesses = Map.of(1, 'A', 2, 'B');

        when(gameRun.session()).thenReturn(session);
        when(session.sessionId()).thenReturn(sessionId);
        when(session.puzzle()).thenReturn(puzzle);
        when(puzzle.id()).thenReturn(puzzleId);
        when(session.state()).thenReturn(gameState);
        when(gameState.guesses()).thenReturn(guesses);

        saveGameUseCase.execute(gameRun);

        ArgumentCaptor<GameSnapshot> snapshotCaptor = ArgumentCaptor.forClass(GameSnapshot.class);
        verify(snapshotRepositoryPort).save(snapshotCaptor.capture());

        GameSnapshot savedSnapshot = snapshotCaptor.getValue();
        assertEquals(sessionId, savedSnapshot.sessionId);
        assertEquals(puzzleId, savedSnapshot.puzzleId);
        assertEquals(guesses, savedSnapshot.guesses);
        assertEquals(Collections.emptyList(), savedSnapshot.past);
        assertEquals(Collections.emptyList(), savedSnapshot.future);
    }
}
