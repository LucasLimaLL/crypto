package br.com.lucaslima.criptograma.features.cryptogram.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.lucaslima.criptograma.features.cryptogram.application.AssignLetterUseCase;
import br.com.lucaslima.criptograma.features.cryptogram.application.ClearLetterUseCase;
import br.com.lucaslima.criptograma.features.cryptogram.application.InitializeGameUseCase;
import br.com.lucaslima.criptograma.features.cryptogram.application.RedoUseCase;
import br.com.lucaslima.criptograma.features.cryptogram.application.RestoreGameUseCase;
import br.com.lucaslima.criptograma.features.cryptogram.application.SaveGameUseCase;
import br.com.lucaslima.criptograma.features.cryptogram.application.StartNewGameUseCase;
import br.com.lucaslima.criptograma.features.cryptogram.application.UndoUseCase;
import br.com.lucaslima.criptograma.features.cryptogram.application.hint.RevealLetterHintUseCase;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameRun;
import br.com.lucaslima.criptograma.features.cryptogram.ui.state.CryptogramUiState;
import br.com.lucaslima.criptograma.features.cryptogram.ui.state.LoadingCryptogramUiState;

public final class CryptogramGameViewModel extends ViewModel {

    private final InitializeGameUseCase initializeGameUseCase;
    private final SaveGameUseCase saveGameUseCase;
    private final AssignLetterUseCase assignLetterUseCase;
    private final ClearLetterUseCase clearLetterUseCase;
    private final UndoUseCase undoUseCase;
    private final RedoUseCase redoUseCase;
    private final RevealLetterHintUseCase revealLetterHintUseCase;
    private final MutableLiveData<CryptogramUiState> states;
    private GameRun currentGameRun;

    public CryptogramGameViewModel(RevealLetterHintUseCase revealLetterHintUseCase,
                                   RedoUseCase redoUseCase,
                                   UndoUseCase undoUseCase,
                                   ClearLetterUseCase clearLetterUseCase,
                                   AssignLetterUseCase assignLetterUseCase,
                                   SaveGameUseCase saveGameUseCase,
                                   InitializeGameUseCase initializeGameUseCase) {
        this.revealLetterHintUseCase = revealLetterHintUseCase;
        this.redoUseCase = redoUseCase;
        this.undoUseCase = undoUseCase;
        this.clearLetterUseCase = clearLetterUseCase;
        this.assignLetterUseCase = assignLetterUseCase;
        this.saveGameUseCase = saveGameUseCase;
        this.initializeGameUseCase = initializeGameUseCase;
        this.states = new MutableLiveData<>(new LoadingCryptogramUiState());
    }

    public void initialize() {
        states.setValue(new LoadingCryptogramUiState());
        currentGameRun = initializeGameUseCase.execute();
        publishContent();
    }

    public void start() {
        states.setValue(new LoadingCryptogramUiState());
        currentGameRun = initializeGameUseCase.start();
        publishContent();
    }

    public void publishContent() {

    }
}
