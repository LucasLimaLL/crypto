package br.com.lucaslima.criptograma.features.ads.ui;

public interface AdHost {

    void showPuzzleComplete(AdPlacement adPlacement);
    void showReward(AdPlacement adPlacement, Runnable onRewardEarned);
}
