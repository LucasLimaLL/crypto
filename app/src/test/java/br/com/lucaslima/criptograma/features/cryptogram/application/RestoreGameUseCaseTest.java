package br.com.lucaslima.criptograma.features.cryptogram.application;

import br.com.lucaslima.criptograma.features.cryptogram.application.ports.GameSessionLoaderPort;
import br.com.lucaslima.criptograma.features.cryptogram.application.ports.SnapshotRepositoryPort;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameRun;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameSession;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given a RestoreGameUseCase")
class RestoreGameUseCaseTest {

    @Mock
    private SnapshotRepositoryPort snapshotRepositoryPort;

    @Mock
    private GameSessionLoaderPort gameSessionLoaderPort;

    @Mock
    private GameSession gameSession;

    @InjectMocks
    private RestoreGameUseCase restoreGameUseCase;

    @Nested
    @DisplayName("When a snapshot exists")
    class WhenSnapshotExists {

        @Test
        @DisplayName("Then it should restore the cryptogram and return a cryptogram run")
        void givenSnapshotExistsWhenExecuteThenRestoreGame() {
            GameSnapshot snapshot = new GameSnapshot("sessionId", "puzzleId", Map.of(1, 'A'), List.of(), List.of());
            when(snapshotRepositoryPort.loadLatestOrNull()).thenReturn(snapshot);
            when(gameSessionLoaderPort.loadBy(snapshot.sessionId, snapshot.puzzleId)).thenReturn(gameSession);
            when(gameSession.withState(any())).thenReturn(gameSession);

            GameRun gameRun = restoreGameUseCase.executeOrNull();

            assertNotNull(gameRun);
            assertEquals(gameSession, gameRun.session());
        }
    }

    @Nested
    @DisplayName("When no snapshot exists")
    class WhenNoSnapshotExists {

        @Test
        @DisplayName("Then it should return null")
        void givenNoSnapshotWhenExecuteThenReturnNull() {
            when(snapshotRepositoryPort.loadLatestOrNull()).thenReturn(null);

            GameRun gameRun = restoreGameUseCase.executeOrNull();

            assertNull(gameRun);
        }
    }
}
