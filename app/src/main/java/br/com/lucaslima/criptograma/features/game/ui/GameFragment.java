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

public class GameFragment extends Fragment {

    public GameFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("GameFragment", "GameFragment created");
        return inflater.inflate(R.layout.game_fragment, container, false);
    }
}
