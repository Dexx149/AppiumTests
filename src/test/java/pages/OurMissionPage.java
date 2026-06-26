package pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import pages.components.QuotesListComponent;

public class OurMissionPage extends BaseLoggedPage{

    private final By header = AppiumBy.id("ru.iteco.fmhandroid:id/our_mission_title_text_view");
    private final QuotesListComponent quotesListComponent;
    public OurMissionPage(AppiumDriver driver) {
        super(driver);
        quotesListComponent = new QuotesListComponent(driver);
    }

    @Step("Check that OurMission page is loaded")
    public OurMissionPage checkThatOurMissionPageLoaded() {
        assertThat(isElementDisplayed(header))
                .isTrue();
        return this;
    }

    @Step("Check that quotes list is displayed")
    public OurMissionPage checkThatQuotesListIsDisplayed() {
        assertThat(quotesListComponent.isQuotesListVisible()).isTrue();
        assertThat(quotesListComponent.getQuotesCount()).isGreaterThan(0);
        return this;
    }

    @Step("First quote click")
    public OurMissionPage firstQuoteClick() {
        quotesListComponent.getQuotesItems().getFirst().click();
        return this;
    }
    @Step("Check That Quote Text Is Displayed After Toggle")
    public OurMissionPage checkThatFirstQuoteTextIsDisplayed() {
        assertThat(quotesListComponent.getQuotesItems().getFirst().isQuoteTextDisplayed()).isTrue();
        return this;
    }

    @Step("Check That Quote Text Is Not Displayed After Toggle")
    public OurMissionPage checkThatFirstQuoteTextIsNotDisplayed() {
        assertThat(quotesListComponent.getQuotesItems().getFirst().isQuoteTextDisplayed()).isFalse();
        return this;
    }
}
