package tests;

import org.junit.jupiter.api.Test;

import pages.BaseLoggedPage;

public class NewsTest extends BaseLoggedTest {

    @Test
    public void shouldDisplayNewsPage() {
        openMainPage()
                .openMainMenu()
                .goToNewsPage()
                .checkThatPageLoaded();
    }
}
