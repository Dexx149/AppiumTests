package pages;

import org.openqa.selenium.By;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import pages.components.MainMenu;

import static org.assertj.core.api.Assertions.assertThat;


public class MainPage extends BaseLoggedPage {
    private final By header = AppiumBy.id("ru.iteco.fmhandroid:id/container_custom_app_bar_include_on_fragment_main");
    private final By newsListToggle = AppiumBy.xpath("//android.widget.LinearLayout[@resource-id=\"ru.iteco.fmhandroid:id/container_list_news_include_on_fragment_main\"]/android.view.ViewGroup");


    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Check that main page is loaded")
    public MainPage checkThatPageLoaded() {
        assertThat(isElementDisplayed(header)).isTrue();
        return this;
    }

}
