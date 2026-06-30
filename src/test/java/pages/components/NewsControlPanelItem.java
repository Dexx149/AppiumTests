package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import models.News;

public class NewsControlPanelItem {

    private final News news;
    private WebElement rootElement;
    private final AppiumDriver driver;
    protected final WebDriverWait wait;

    private final By titleLocator = By.id("ru.iteco.fmhandroid:id/news_item_title_text_view");
    private final By descriptionLocator = By.id("ru.iteco.fmhandroid:id/news_item_description_text_view");
    private final By publicationDateLocator = By.id("ru.iteco.fmhandroid:id/news_item_publication_date_text_view");
    private final By creationDateLocator = By.id("ru.iteco.fmhandroid:id/news_item_create_date_text_view");
    private final By authorLocator = By.id("ru.iteco.fmhandroid:id/news_item_author_name_text_view");
    private final By activeStatusLocator = By.id("ru.iteco.fmhandroid:id/news_item_published_text_view");
    private final By deleteButton = By.id("ru.iteco.fmhandroid:id/delete_news_item_image_view");
    private final By editButton = By.id("ru.iteco.fmhandroid:id/edit_news_item_image_view");


    public NewsControlPanelItem(AppiumDriver driver, WebElement rootElement) {
        this.rootElement = rootElement;
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.news = toNews();
    }

    public void click() {
        rootElement.click();
    }

    public News toNews() {
        String title = safeGetText(titleLocator);
        String author = safeGetText(authorLocator);
        String publishStatusText = safeGetText(activeStatusLocator);


        long publishDate = parseDateToLong(safeGetText(publicationDateLocator));
        long createDate = parseDateToLong(safeGetText(creationDateLocator));

        // Определение статуса публикации
        boolean publishEnabled = publishStatusText.toLowerCase().contains("активна") || publishStatusText.toLowerCase().contains("active");

        return new News(
                0,              // id неизвестен из UI
                1,              // categoryId неизвестен
                title,
                "",     // пуст т.к изначально скрыт
                0,              // creatorId неизвестен
                createDate,
                publishDate,
                publishEnabled,
                author
        );
    }

    private String safeGetText(By locator) {
        try {
            WebElement element = rootElement.findElement(locator);
            if (element.isDisplayed()) {
                return element.getText();
            }
        } catch (Exception e) {
            // Элемент не найден или не виден
        }
        return "";
    }

    private long parseDateToLong(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date = formatter.parse(dateString);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isTitleDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(titleLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDescriptionTextDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String title(){
        return news.title();
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