package tests;

import org.junit.jupiter.api.Test;

public class OurMissionTest extends BaseLoggedTest {

    @Test
    public void shouldDisplayOurMissionPageWithQuotes() {
        openMainPage()
                .ourMissionClick()
                .checkThatOurMissionPageLoaded()
                .checkThatQuotesListIsDisplayed();
    }

    @Test
    public void shouldExpandAndCollapseQuoteTextOnClick() {
        openMainPage()
                .ourMissionClick()
                .checkThatFirstQuoteTextIsNotDisplayed()
                .firstQuoteClick()
                .checkThatFirstQuoteTextIsDisplayed()
                .firstQuoteClick()
                .checkThatFirstQuoteTextIsNotDisplayed();
    }
}
