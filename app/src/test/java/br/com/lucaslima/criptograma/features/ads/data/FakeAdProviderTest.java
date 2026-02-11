package br.com.lucaslima.criptograma.features.ads.data;

import br.com.lucaslima.criptograma.features.ads.ui.AdPlacement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given a FakeAdProvider")
class FakeAdProviderTest {

    @InjectMocks
    private FakeAdProvider fakeAdProvider;

    @Test
    @DisplayName("When isPuzzleCompleteAvailable is called, then it should return true")
    void whenIsPuzzleCompleteAvailableThenReturnTrue() {
        assertTrue(fakeAdProvider.isPuzzleCompleteAvailable());
    }

    @Test
    @DisplayName("When isRewardAvailable is called, then it should return true")
    void whenIsRewardAvailableThenReturnTrue() {
        assertTrue(fakeAdProvider.isRewardAvailable());
    }

    @Test
    @DisplayName("When showPuzzleComplete is called, then it should not throw any exception")
    void whenShowPuzzleCompleteThenNoException(@Mock AdPlacement adPlacement) {
        fakeAdProvider.showPuzzleComplete(adPlacement);
    }

    @Test
    @DisplayName("When showReward is called, then it should execute the onRewardEarned runnable")
    void whenShowRewardThenExecuteRunnable(@Mock AdPlacement adPlacement, @Mock Runnable onRewardEarned) {
        fakeAdProvider.showReward(adPlacement, onRewardEarned);
        verify(onRewardEarned).run();
    }
}
