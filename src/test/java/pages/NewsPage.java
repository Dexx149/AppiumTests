package pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

public class NewsPage extends BaseLoggedPage {

    private final By header = AppiumBy.id("ru.iteco.fmhandroid:id/container_custom_app_bar_include_on_fragment_news_list");
    private final By controlPanelButton = AppiumBy.id("ru.iteco.fmhandroid:id/edit_news_material_button");
    public NewsPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Check that News page is loaded")
    public NewsPage checkThatNewsPageLoaded() {
        assertThat(isElementDisplayed(header))
                .isTrue();
        return this;
    }

    @Step("Go to control panel page")
    public NewsControlPanelPage openControlPanel() {
        driver.findElement(controlPanelButton).click();
        return new NewsControlPanelPage(driver);
    }
}
