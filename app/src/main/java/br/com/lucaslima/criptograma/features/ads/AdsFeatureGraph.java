package br.com.lucaslima.criptograma.features.ads;

import br.com.lucaslima.criptograma.features.ads.data.AdProvider;
import br.com.lucaslima.criptograma.features.ads.data.FakeAdProvider;
import br.com.lucaslima.criptograma.features.ads.ui.AdHost;
import br.com.lucaslima.criptograma.features.ads.ui.AndroidAdHost;

public final class AdsFeatureGraph {

    private final AdHost adHost;

    private AdsFeatureGraph(AdHost adHost) {
        this.adHost = adHost;
    }

    public static AdsFeatureGraph create() {
        AdProvider adProvider = new FakeAdProvider();
        AdHost adHost = new AndroidAdHost(adProvider);
        return new AdsFeatureGraph(adHost);
    }

    public AdHost getAdHost() {
        return adHost;
    }
}
