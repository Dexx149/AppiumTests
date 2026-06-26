package tests;

import org.junit.jupiter.api.Test;

public class MainPageTest extends BaseLoggedTest{

    @Test
    public void shouldDisplayMainPage() {
        openMainPage()
                .checkThatMainPageLoaded();
    }


    @Test
    public void shouldExpandAndCollapseNewsListOnButtonClick() {
        openMainPage()
                .checkThatNewsListIsDisplayed()
                .toggleButtonClick()
                .checkThatNewsListIsNotDisplayed()
                .toggleButtonClick()
                .checkThatNewsListIsDisplayed();

    }

    @Test
    public void shouldOpenNewsPage() {
        openMainPage()
                .allNewsButtonClick()
                .checkThatNewsPageLoaded();
    }
}
