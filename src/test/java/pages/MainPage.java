package pages;

import org.openqa.selenium.By;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import static org.assertj.core.api.Assertions.assertThat;


public class MainPage extends BasePage {
    private final By header = AppiumBy.androidUIAutomator("new UiSelector().text(\"News\")");


    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Check that main page is loaded")
    public MainPage checkThatPageLoaded() {
        assertThat(isElementDisplayed(header))
                .isTrue();
        return this;
    }
}
