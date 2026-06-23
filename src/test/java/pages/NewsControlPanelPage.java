package pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

public class NewsControlPanelPage extends BaseLoggedPage {

    private final By header = AppiumBy.id("ru.iteco.fmhandroid:id/container_custom_app_bar_include_on_fragment_news_control_panel");
    public NewsControlPanelPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Check that control panel page is loaded")
    public NewsControlPanelPage checkThatPageLoaded() {
        assertThat(isElementDisplayed(header)).isTrue();
        return this;
    }



}
