package br.com.lucaslima.criptograma.features.home.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.lucaslima.criptograma.R;
import br.com.lucaslima.criptograma.features.cryptogram.ui.CryptogramGameFragment;

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        Button startNewGameCryptogramButton = root.findViewById(R.id.start_new_game_cryptogram_button);
        Button resumeGameCryptogramButton = root.findViewById(R.id.resume_game_cryptogram_button);

        startNewGameCryptogramButton.setOnClickListener(
                view -> startNewCryptogramGame()
        );


        resumeGameCryptogramButton.setOnClickListener(
                view -> resumeCryptogramGame()
        );

        return root;
    }


    private void startNewCryptogramGame() {
        Log.d("HomeFragment", "Starting new cryptogram game");
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new CryptogramGameFragment())
                .commit();
    }


    private void resumeCryptogramGame() {
        Log.d("HomeFragment", "Resuming cryptogram game");
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new CryptogramGameFragment())
                .commit();
    }

}
