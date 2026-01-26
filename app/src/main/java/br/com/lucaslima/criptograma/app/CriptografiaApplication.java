package br.com.lucaslima.criptograma.app;

import android.app.Application;
import android.util.Log;

import br.com.lucaslima.criptograma.BuildConfig;
import br.com.lucaslima.criptograma.features.ads.AdsFeatureGraph;

public class CriptografiaApplication extends Application {

    private static CriptografiaApplication criptografiaApplication;
    private AppMode appMode;
    private AdsFeatureGraph adsFeatureGraph;


    @Override
    public void onCreate() {
        super.onCreate();
        criptografiaApplication = this;

        appMode = BuildConfig.ADS_ENABLED ? AppMode.ADS_ENABLED : AppMode.OFFLINE;
        Log.d(this.getClass().getName(), "CriptografiaApplication onCreate AppMode:" + appMode);

        adsFeatureGraph = AdsFeatureGraph.create();
        Log.d(this.getClass().getName(), "CriptografiaApplication onCreate AdsFeatureGraph:" + adsFeatureGraph);

    }

    public AppMode getAppMode() {
        return appMode;
    }

    public AdsFeatureGraph getAdsFeatureGraph() {
        return adsFeatureGraph;
    }

    public static CriptografiaApplication getInstance() {
        return criptografiaApplication;
    }


}
