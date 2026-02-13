package br.com.lucaslima.criptograma.features.game.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.lucaslima.criptograma.R;
import br.com.lucaslima.criptograma.features.game.application.StartNewGameUseCase;
import br.com.lucaslima.criptograma.features.game.data.session.DefaultGameSessionFactory;
import br.com.lucaslima.criptograma.features.game.domain.GameRun;

public class GameFragment extends Fragment {

    private StartNewGameUseCase startNewGameUseCase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.startNewGameUseCase = new StartNewGameUseCase(new DefaultGameSessionFactory(requireContext()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("GameFragment", "GameFragment created");
        GameRun gameRun = startNewGameUseCase.execute();
        return inflater.inflate(R.layout.game_fragment, container, false);
    }
}
