package br.com.lucaslima.criptograma.features.ads.ui;

import br.com.lucaslima.criptograma.features.ads.data.AdProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given an AndroidAdHost")
class AndroidAdHostTest {

    @Mock
    private AdProvider adProvider;

    @Mock
    private AdPlacement adPlacement;

    @InjectMocks
    private AndroidAdHost androidAdHost;

    @Nested
    @DisplayName("When showPuzzleComplete is called")
    class WhenShowPuzzleComplete {

        @Test
        @DisplayName("And the ad is available, then it should show the ad")
        void givenAdAvailableWhenShowPuzzleCompleteThenShowAd() {
            when(adProvider.isPuzzleCompleteAvailable()).thenReturn(true);

            androidAdHost.showPuzzleComplete(adPlacement);

            verify(adProvider).showPuzzleComplete(adPlacement);
        }

        @Test
        @DisplayName("And the ad is not available, then it should not show the ad")
        void givenAdNotAvailableWhenShowPuzzleCompleteThenDoNotShowAd() {
            when(adProvider.isPuzzleCompleteAvailable()).thenReturn(false);

            androidAdHost.showPuzzleComplete(adPlacement);

            verify(adProvider, never()).showPuzzleComplete(adPlacement);
        }
    }

    @Nested
    @DisplayName("When showReward is called")
    class WhenShowReward {

        private final Runnable onRewardEarned = () -> {};

        @Test
        @DisplayName("And the ad is available, then it should show the ad")
        void givenAdAvailableWhenShowRewardThenShowAd() {
            when(adProvider.isRewardAvailable()).thenReturn(true);

            androidAdHost.showReward(adPlacement, onRewardEarned);

            verify(adProvider).showReward(adPlacement, onRewardEarned);
        }

        @Test
        @DisplayName("And the ad is not available, then it should not show the ad")
        void givenAdNotAvailableWhenShowRewardThenDoNotShowAd() {
            when(adProvider.isRewardAvailable()).thenReturn(false);

            androidAdHost.showReward(adPlacement, onRewardEarned);

            verify(adProvider, never()).showReward(adPlacement, onRewardEarned);
        }
    }
}
