package pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

public class AboutPage extends BaseLoggedPage {

    private final By header = AppiumBy.id("ru.iteco.fmhandroid:id/container_custom_app_bar_include_on_fragment_about");

    public AboutPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Check that about page is loaded")
    public AboutPage checkThatAboutPageLoaded() {
        assertThat(isElementDisplayed(header))
                .isTrue();
        return this;
    }
}
