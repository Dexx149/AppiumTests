package pages;

import org.openqa.selenium.By;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class MainPage extends BasePage {
    private final By header = AppiumBy.androidUIAutomator("new UiSelector().text(\"News\")");


    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isHeaderDisplayed() {
        return isElementDisplayed(header);
    }
}
