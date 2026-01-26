package br.com.lucaslima.criptograma.features.ads.data;

import android.util.Log;

import br.com.lucaslima.criptograma.features.ads.ui.AdPlacement;

public class FakeAdProvider implements AdProvider {
    @Override
    public boolean isPuzzleCompleteAvailable() {
        return true;
    }

    @Override
    public boolean isRewardAvailable() {
        return true;
    }

    @Override
    public void showPuzzleComplete(AdPlacement adPlacement) {
        Log.d("FakeAdProvider", "Fake showPuzzleComplete: " + adPlacement);
    }

    @Override
    public void showReward(AdPlacement adPlacement, Runnable onRewardEarned) {
        Log.d("FakeAdProvider", "Fake showReward: " + adPlacement);
        onRewardEarned.run();
    }
}
