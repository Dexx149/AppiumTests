package tests;

import org.junit.jupiter.api.Test;

public class AboutPageTest extends BaseLoggedTest {


    @Test
    public void shouldDisplayAboutPage() {
        openMainPage()
                .openMainMenu()
                .goToAboutPage()
                .checkThatPageLoaded();
    }
}
