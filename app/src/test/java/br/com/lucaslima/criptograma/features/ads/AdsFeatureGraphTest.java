package br.com.lucaslima.criptograma.features.ads;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Given an AdsFeatureGraph")
class AdsFeatureGraphTest {

    @Test
    @DisplayName("When created, then it should provide a non-null AdHost")
    void givenCreatedWhenGetAdHostThenReturnNonNull() {
        AdsFeatureGraph adsFeatureGraph = AdsFeatureGraph.create();
        assertNotNull(adsFeatureGraph.getAdHost());
    }
}
