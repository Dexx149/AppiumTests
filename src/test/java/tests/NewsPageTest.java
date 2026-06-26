package tests;

import org.junit.jupiter.api.Test;

public class NewsPageTest extends BaseLoggedTest {

    @Test
    public void shouldDisplayNewsPage() {
        openMainPage()
                .openMainMenu()
                .openNewsPage()
                .checkThatNewsPageLoaded();
    }
}
