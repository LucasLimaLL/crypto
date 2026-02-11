package br.com.lucaslima.criptograma.features.game.application;

import br.com.lucaslima.criptograma.features.game.application.ports.GameSessionFactoryPort;
import br.com.lucaslima.criptograma.features.game.domain.GameRun;
import br.com.lucaslima.criptograma.features.game.domain.GameSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given a StartNewGameUseCase")
class StartNewGameUseCaseTest {

    @Mock
    private GameSessionFactoryPort gameSessionFactoryPort;

    @Mock
    private GameSession gameSession;

    @InjectMocks
    private StartNewGameUseCase startNewGameUseCase;

    @Test
    @DisplayName("When a new game is started, then it should create a new game run with a new session")
    void givenNewGameWhenExecuteThenCreateNewGameRun() {
        when(gameSessionFactoryPort.createNewSession()).thenReturn(gameSession);

        GameRun gameRun = startNewGameUseCase.execute();

        assertNotNull(gameRun);
        assertEquals(gameSession, gameRun.session());
        assertNotNull(gameRun.history());
    }
}
