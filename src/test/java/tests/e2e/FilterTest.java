package tests.e2e;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import pages.BaseLoggedPage;
import service.NewsService;

public class FilterTest extends BaseLoggedTest {

    @Test
    public void shouldDisplayNewsList() {
        openMainPage()
                .openMainMenu()
                .openNewsPage()
                .openControlPanel()
                .openFilter()
                .selectCategory("Праздник")
                .setInactiveCheckbox(true)
                .setActiveCheckbox(false)
                .applyFilter();
    }



}
