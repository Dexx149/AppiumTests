package pages.components;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import pages.AboutPage;
import pages.MainPage;
import pages.NewsPage;

public class MainMenu extends BaseComponent<MainMenu> {

    private final By mainPageLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"Main\")");
    private final By newsPageLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"News\")");
    private final By aboutPageLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"About\")");

    public MainMenu(AppiumDriver driver) {
        super(driver, AppiumBy.id("ru.iteco.fmhandroid:id/main_menu_image_button"));
    }

    public MainPage openMainPage() {
        driver.findElement(mainPageLocator).click();
        return new MainPage(driver);
    }

    public AboutPage openAboutPage() {
        driver.findElement(aboutPageLocator).click();
        return new AboutPage(driver);
    }

    public NewsPage openNewsPage() {
        driver.findElement(newsPageLocator).click();
        return new NewsPage(driver);
    }
}
