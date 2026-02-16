package br.com.lucaslima.criptograma.features.cryptogram.application;

import br.com.lucaslima.criptograma.features.cryptogram.domain.GameRun;

public final class InitializeGameUseCase {

    private final RestoreGameUseCase restoreGameUseCase;
    private final StartNewGameUseCase startNewGameUseCase;

    public InitializeGameUseCase(RestoreGameUseCase restoreGameUseCase, StartNewGameUseCase startNewGameUseCase) {
        this.restoreGameUseCase = restoreGameUseCase;
        this.startNewGameUseCase = startNewGameUseCase;
    }

    public GameRun execute() {
        GameRun restoredGame = restoreGameUseCase.executeOrNull();
        return restoredGame != null ? restoredGame : startNewGameUseCase.execute();
    }

    public GameRun start() {
        return startNewGameUseCase.execute();
    }

}
