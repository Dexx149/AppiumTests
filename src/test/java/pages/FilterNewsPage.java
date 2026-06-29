package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

import io.qameta.allure.Step;


public class FilterNewsPage extends BasePage<FilterNewsPage> {

    private static final By categoryDropdownLocator = AppiumBy.id("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view");

    private static final By dateStartFieldLocator = AppiumBy.id("ru.iteco.fmhandroid:id/news_item_publish_date_start_text_input_edit_text");
    private static final By dateEndFieldLocator = AppiumBy.id("ru.iteco.fmhandroid:id/news_item_publish_date_end_text_input_edit_text");

    private static final By activeCheckboxLocator = AppiumBy.id("ru.iteco.fmhandroid:id/filter_news_active_material_check_box");
    private static final By Locator = AppiumBy.id("ru.iteco.fmhandroid:id/filter_news_inactive_material_check_box");

    private static final By filterButtonLocator = AppiumBy.id("ru.iteco.fmhandroid:id/filter_button");
    private static final By cancelButtonLocator = AppiumBy.id("ru.iteco.fmhandroid:id/cancel_button");

    public FilterNewsPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Select category: {0}")
    public FilterNewsPage selectCategory(String categoryName) {
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(categoryDropdownLocator));
        dropdownElement.click();
        dropdownElement.clear();
        dropdownElement.sendKeys(categoryName);

        org.openqa.selenium.Rectangle rect = dropdownElement.getRect();
        int x = rect.getX() + rect.getWidth() / 2;
        int y = rect.getY() + rect.getHeight() + 50;

        Map<String, Object> args = new HashMap<>();
        args.put("x", x);
        args.put("y", y);
        driver.executeScript("mobile: clickGesture", args);

        return this;
    }


    //методы не используются потому что фильтры по датам не работают
    @Step("Fill start date field")
    public FilterNewsPage fillStartDate(String startDate) {
        wait.until(ExpectedConditions.elementToBeClickable(dateEndFieldLocator)).sendKeys(startDate);
        return this;
    }

    @Step("Fill end date field")
    public FilterNewsPage fillEndDate(String endDate) {
        wait.until(ExpectedConditions.elementToBeClickable(dateEndFieldLocator)).sendKeys(endDate);
        return this;
    }

    @Step("Set 'Active' checkbox to: {0}")
    public FilterNewsPage setActiveCheckbox(boolean shouldBeChecked) {
        toggleCheckbox(activeCheckboxLocator, shouldBeChecked);
        return this;
    }

    @Step("Set 'Inactive' checkbox to: {0}")
    public FilterNewsPage setInactiveCheckbox(boolean shouldBeChecked) {
        toggleCheckbox(Locator, shouldBeChecked);
        return this;
    }

    private void toggleCheckbox(By locator, boolean shouldBeChecked) {
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        boolean isChecked = Boolean.parseBoolean(checkbox.getAttribute("checked"));

        if (isChecked != shouldBeChecked) {
            checkbox.click();
        }
    }

    @Step("Click 'Filter' button")
    public NewsControlPanelPage applyFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(filterButtonLocator)).click();
        return new NewsControlPanelPage(driver);
    }


}
