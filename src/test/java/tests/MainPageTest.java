package tests;

import org.junit.jupiter.api.Test;

public class MainPageTest extends BaseLoggedTest{

    @Test
    public void shouldDisplayMainPage() {
        openMainPage()
                .checkThatPageLoaded();
    }
}
