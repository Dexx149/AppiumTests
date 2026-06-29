package pages.components;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewsItem {
    private final WebElement rootElement;
    private final AppiumDriver driver;

    private final By titleLocator = By.id("ru.iteco.fmhandroid:id/news_item_title_text_view");
    private final By descriptionLocator = By.id("ru.iteco.fmhandroid:id/news_item_description_text_view");

    protected final WebDriverWait wait;


    public NewsItem(AppiumDriver driver, WebElement rootElement) {
        this.driver = driver;
        this.rootElement = rootElement;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void click() {
        rootElement.click();
    }

}
