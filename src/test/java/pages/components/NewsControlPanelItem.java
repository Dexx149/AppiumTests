package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class NewsControlPanelItem {
    private final WebElement rootElement;
    private final AppiumDriver driver;

    private final By titleLocator = By.id("ru.iteco.fmhandroid:id/news_item_title_text_view");
    private final By descriptionLocator = By.id("ru.iteco.fmhandroid:id/news_item_description_text_view");
    private final By publicationDateLocator = By.id("ru.iteco.fmhandroid:id/news_item_publication_date_text_view");
    private final By creationDateLocator = By.id("ru.iteco.fmhandroid:id/news_item_create_date_text_view");
    private final By authorLocator = By.id("ru.iteco.fmhandroid:id/news_item_author_name_text_view");
    private final By activeStatusLocator = By.id("ru.iteco.fmhandroid:id/news_item_published_text_view");
    private final By deleteButton = By.id("ru.iteco.fmhandroid:id/delete_news_item_image_view");
    private final By editButton = By.id("ru.iteco.fmhandroid:id/edit_news_item_image_view");

    protected final WebDriverWait wait;


    public NewsControlPanelItem(AppiumDriver driver, WebElement rootElement) {
        this.rootElement = rootElement;
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void click() {
        rootElement.click();
    }

    public boolean isDescriptionTextDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTitle(){
        return rootElement.findElement(titleLocator).getText();
    }

    public NewsControlPanelItem clickDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        return this;
    }

    public NewsControlPanelItem clickEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
        return this;
    }
}
