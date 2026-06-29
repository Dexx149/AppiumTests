package pages;

import net.datafaker.Faker;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import models.News;
import models.NewsCategory;
import pages.components.CalendarComponent;
import pages.components.TimeComponent;

public class EditNewsPage extends BasePage<EditNewsPage>{

    private static final By titleFieldLocator = AppiumBy.id("ru.iteco.fmhandroid:id/news_item_title_text_input_edit_text");
    private static final By descriptionFieldLocator  = AppiumBy.id("ru.iteco.fmhandroid:id/news_item_description_text_input_edit_text");
    private static final By categoryDropdownLocator  = AppiumBy.id("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view");
    private  CalendarComponent publishDate;
    private  TimeComponent publishTime;
    private static final By activeCheckboxLocator  = AppiumBy.id("ru.iteco.fmhandroid:id/switcher");

    // Кнопки
    private static final By saveButtonLocator  = AppiumBy.id("ru.iteco.fmhandroid:id/save_button");
    private static final By cancelButtonLocator  = AppiumBy.id("ru.iteco.fmhandroid:id/cancel_button");


    public EditNewsPage(AppiumDriver driver) {

        super(driver);
        publishDate = new CalendarComponent(driver);
        publishTime = new TimeComponent(driver);
    }

    @Step("Enter title: {0}")
    public EditNewsPage enterTitle(String title) {
        WebElement titleField = wait.until(ExpectedConditions.elementToBeClickable(titleFieldLocator));
        titleField.click();
        titleField.clear();
        titleField.sendKeys(title);
        return this;
    }

    @Step("Enter description: {0}")
    public EditNewsPage enterDescription(String description) {
        WebElement descriptionField = wait.until(ExpectedConditions.elementToBeClickable(descriptionFieldLocator));
        descriptionField.clear();
        descriptionField.sendKeys(description);
        return this;
    }

    @Step("Enter created date: {0}")
    public EditNewsPage enterCurrentDate() {
        publishDate.click();
        publishDate.selectCurrentDate();
        return this;
    }

    @Step("Enter created time: {0}")
    public EditNewsPage enterCurrentTime() {
        publishTime.click();
        publishTime.selectCurrentTime();
        return this;
    }

    @Step("Select category: {0}")
    public EditNewsPage selectCategory(String categoryName) {
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(categoryDropdownLocator));
        dropdownElement.click();
        dropdownElement.clear();
        dropdownElement.sendKeys(categoryName);

        Rectangle rect = dropdownElement.getRect();
        int x = rect.getX() + rect.getWidth() / 2;
        int y = rect.getY() + rect.getHeight() + 50;

        Map<String, Object> args = new HashMap<>();
        args.put("x", x);
        args.put("y", y);
        driver.executeScript("mobile: clickGesture", args);

        return this;
    }

    @Step("Set active checkbox: {0}")
    public EditNewsPage setActiveCheckbox(boolean checked) {
        toggleCheckbox(activeCheckboxLocator, checked);
        return this;
    }

    private void toggleCheckbox(By locator, boolean shouldBeChecked) {
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        boolean isChecked = Boolean.parseBoolean(checkbox.getAttribute("checked"));
        if (isChecked != shouldBeChecked) {
            checkbox.click();
        }
    }

    @Step("Save news (mode: {0})")
    public NewsControlPanelPage saveNews() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator)).click();
        return new NewsControlPanelPage(driver);
    }

    @Step("Cancel editing")
    public NewsControlPanelPage cancelEditing() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButtonLocator)).click();
        return new NewsControlPanelPage(driver);
    }

    @Step("Fill form with random news data")
    public News fillRandomNews() {
        Faker faker = new Faker(new Locale("ru"));

        String title = faker.lorem().characters(20);
        String description = faker.lorem().characters(40);
        int categoryId = faker.number().numberBetween(1, NewsCategory.values().length);
        String categoryName = NewsCategory.fromId(categoryId).getDisplayName();
        boolean publishEnabled = true; // всегда true тк присутствует баг, который не позволяет делать новость неактивной

        enterTitle(title);
        enterDescription(description);
        selectCategory(categoryName);
        setActiveCheckbox(publishEnabled);
        enterCurrentDate();
        enterCurrentTime();

        return new News(
                0,
                categoryId,
                title,
                description,
                0,
                0,
                System.currentTimeMillis(),
                publishEnabled,
                ""
        );
    }

    @Step("Fill form with random data, save and verify")
    public NewsControlPanelPage saveAndVerifyRandomNews() {
        News expectedNews = fillRandomNews();

        ((AndroidDriver)driver).hideKeyboard();
        saveNews();

        NewsControlPanelPage panel = new NewsControlPanelPage(driver);
        panel.verifyNewsExists(expectedNews.title());

        return panel;
    }
}