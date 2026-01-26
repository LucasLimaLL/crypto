package br.com.lucaslima.criptograma.features.home.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.lucaslima.criptograma.R;
import br.com.lucaslima.criptograma.app.CriptografiaApplication;
import br.com.lucaslima.criptograma.features.ads.ui.AdPlacement;
import br.com.lucaslima.criptograma.features.game.ui.GameFragment;

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        Button startNewGameButton = root.findViewById(R.id.start_new_game_button);
        Button rankingButton = root.findViewById(R.id.ranking_button);

        startNewGameButton.setOnClickListener(
                view -> startNewGame()
        );

        rankingButton.setOnClickListener(
                view -> simulateRanking()
        );

        return root;
    }

    private void simulateRanking() {
        CriptografiaApplication
                .getInstance()
                .getAdsFeatureGraph()
                .getAdHost()
                .showReward(AdPlacement.REWARD, () -> {
                    Log.d("HomeFragment", "Reward shown");
                    Toast.makeText(requireContext(), "Reward shown", Toast.LENGTH_SHORT).show();
                });
    }


    private void startNewGame() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new GameFragment())
                .commit();
    }

}
