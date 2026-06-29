package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import io.appium.java_client.AppiumDriver;

public class QuoteItem{

    private final WebElement rootElement;
    private final AppiumDriver driver;

    private final By titleLocator = By.id("ru.iteco.fmhandroid:id/our_mission_item_title_text_view");
    private final By descriptionLocator = By.id("ru.iteco.fmhandroid:id/our_mission_item_description_text_view");

    protected final WebDriverWait wait;


    public QuoteItem(AppiumDriver driver, WebElement rootElement) {
        this.driver = driver;
        this.rootElement = rootElement;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isQuoteTextDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void click() {
        rootElement.click();
    }
}
