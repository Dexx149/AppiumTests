package pages.components;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewsItem {
    private final WebElement rootElement;
    private final AppiumDriver driver;

    private final By titleLocator = By.id("ru.iteco.fmhandroid:id/news_item_title_text_view");
    private final By descriptionLocator = By.id("ru.iteco.fmhandroid:id/news_item_description_text_view");

    public NewsItem(AppiumDriver driver, WebElement rootElement) {
        this.driver = driver;
        this.rootElement = rootElement;
    }

    public String getTitle() {
        return rootElement.findElement(titleLocator).getText();
    }

    public String getDescription() {
        return rootElement.findElement(descriptionLocator).getText();
    }

    public void click() {
        rootElement.click();
    }

}
