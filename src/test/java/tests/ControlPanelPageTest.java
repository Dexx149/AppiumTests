package tests;

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

}
