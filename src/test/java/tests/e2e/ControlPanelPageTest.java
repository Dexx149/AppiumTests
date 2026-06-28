package tests.e2e;

import org.junit.jupiter.api.Test;

public class ControlPanelPageTest extends BaseLoggedTest{

    @Test
    public void shouldDisplayControlPanelPage() {
        openMainPage()
                .openMainMenu()
                .openNewsPage()
                .openControlPanel()
                .checkThatPageLoaded();
    }

    @Test
    public void shouldDisplayNewsList() {
        openMainPage()
                .openMainMenu()
                .openNewsPage()
                .openControlPanel()
                .checkThatNewsListIsDisplayed();
    }

    @Test
    public void shouldExpandAndCollapseDescriptionOnClick() {
        openMainPage()
                .openMainMenu()
                .openNewsPage()
                .openControlPanel()
                .checkThatFirstNewsDescriptionIsNotDisplayed()
                .firstNewsClick()
                .checkThatFirstNewsDescriptionIsDisplayed()
                .firstNewsClick()
                .checkThatFirstNewsDescriptionIsNotDisplayed();
    }

    @Test
    public void shouldSortNewsCorrectly() {
        openMainPage()
                .openMainMenu()
                .openNewsPage()
                .openControlPanel()
                .checkThatSortIsWorking();
    }

    @Test
    public void shouldDeleteFirstNews() {
        openMainPage()
                .openMainMenu()
                .openNewsPage()
                .openControlPanel()
                .deleteFirstNews();

    }

}
