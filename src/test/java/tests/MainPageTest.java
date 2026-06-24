package tests;

import org.junit.jupiter.api.Test;

public class MainPageTest extends BaseLoggedTest{

    @Test
    public void shouldDisplayMainPage() {
        openMainPage()
                .checkThatPageLoaded();
    }


    @Test
    public void toggleNewsListButtonTest() {
        openMainPage()
                .checkThatNewsListIsDisplayed()
                .toggleButtonClick()
                .checkThatNewsListIsNotDisplayed()
                .toggleButtonClick()
                .checkThatNewsListIsDisplayed();

    }
}
