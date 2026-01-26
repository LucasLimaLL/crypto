package br.com.lucaslima.criptograma.features.ads.data;

import br.com.lucaslima.criptograma.features.ads.ui.AdPlacement;

public interface AdProvider {

    boolean isPuzzleCompleteAvailable();
    boolean isRewardAvailable();

    void showPuzzleComplete(AdPlacement adPlacement);
    void showReward(AdPlacement adPlacement, Runnable onRewardEarned);
}
