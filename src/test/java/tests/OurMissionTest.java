package tests;

import org.junit.jupiter.api.Test;

public class OurMissionTest extends BaseLoggedTest {

    @Test
    public void shouldDisplayOurMissionPage() {
        openMainPage()
                .ourMissionClick()
                .checkThatPageLoaded();
    }

}
