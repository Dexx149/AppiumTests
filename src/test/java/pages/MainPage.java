package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import pages.components.MainMenu;
import pages.components.MainNewsListComponent;

import static org.assertj.core.api.Assertions.assertThat;


public class MainPage extends BaseLoggedPage {
    private final By header = AppiumBy.id("ru.iteco.fmhandroid:id/container_custom_app_bar_include_on_fragment_main");
    private final MainNewsListComponent mainNewsListComponent;
    private final By toggleNewsListButton = AppiumBy.id("ru.iteco.fmhandroid:id/expand_material_button");

    public MainPage(AppiumDriver driver) {
        super(driver);
        mainNewsListComponent = new MainNewsListComponent(driver);
    }

    @Step("Check that main page is loaded")
    public MainPage checkThatPageLoaded() {
        assertThat(isElementDisplayed(header)).isTrue();
        return this;
    }

    @Step("Check that news list is displayed")
    public MainPage checkThatNewsListIsDisplayed() {
        assertThat(mainNewsListComponent.isAllNewsButtonVisible()).isTrue();
        return this;
    }

    @Step("Check that news list is not displayed")
    public MainPage checkThatNewsListIsNotDisplayed() {
        assertThat(mainNewsListComponent.isAllNewsButtonVisible()).isFalse();
        return this;
    }

    @Step("NewsList ToggleButton click")
    public MainPage toggleButtonClick() {
        $(toggleNewsListButton).click();
        return this;
    }
}
