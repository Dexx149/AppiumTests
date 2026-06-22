package pages.components;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseComponent<T extends BaseComponent<?>> {

    protected final AppiumDriver driver;
    protected final By locator;
    protected final WebDriverWait wait;

    protected BaseComponent(AppiumDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement getSelf() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public T click() {
        getSelf().click();
        return (T) this;
    }

    public boolean isDisplayed() {
        try {
            return getSelf().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}