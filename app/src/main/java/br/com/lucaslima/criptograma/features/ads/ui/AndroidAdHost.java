package br.com.lucaslima.criptograma.features.ads.ui;

import br.com.lucaslima.criptograma.features.ads.data.AdProvider;

public final class AndroidAdHost implements AdHost {

    private final AdProvider adProvider;

    public AndroidAdHost(AdProvider adProvider) {
        this.adProvider = adProvider;
    }

    @Override
    public void showPuzzleComplete(AdPlacement adPlacement) {
        if (adProvider.isPuzzleCompleteAvailable()) {
            adProvider.showPuzzleComplete(adPlacement);
        }
    }

    @Override
    public void showReward(AdPlacement adPlacement, Runnable onRewardEarned) {
        if (adProvider.isRewardAvailable()) {
            adProvider.showReward(adPlacement, onRewardEarned);
        }
    }
}
