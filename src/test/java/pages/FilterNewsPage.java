package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;


public class FilterNewsPage extends BasePage<FilterNewsPage> {

    private static final By categoryDropdown = AppiumBy.id("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view");

    private static final By dateStartField = AppiumBy.id("ru.iteco.fmhandroid:id/news_item_publish_date_start_text_input_edit_text");
    private static final By dateEndField = AppiumBy.id("ru.iteco.fmhandroid:id/news_item_publish_date_end_text_input_edit_text");

    private static final By activeCheckbox = AppiumBy.id("ru.iteco.fmhandroid:id/filter_news_active_material_check_box");
    private static final By inactiveCheckbox = AppiumBy.id("ru.iteco.fmhandroid:id/filter_news_inactive_material_check_box");

    private static final By filterButton = AppiumBy.id("ru.iteco.fmhandroid:id/filter_button");
    private static final By cancelButton = AppiumBy.id("ru.iteco.fmhandroid:id/cancel_button");

    public FilterNewsPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Select category: {0}")
    public FilterNewsPage selectCategory(String categoryName) {
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
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

    @Step("Fill start date field")
    public FilterNewsPage fillStartDate(String startDate) {
        wait.until(ExpectedConditions.elementToBeClickable(dateStartField)).sendKeys(startDate);
        return this;
    }

    @Step("Fill end date field")
    public FilterNewsPage fillEndDate(String endDate) {
        wait.until(ExpectedConditions.elementToBeClickable(dateEndField)).sendKeys(endDate);
        return this;
    }

    @Step("Set 'Active' checkbox to: {0}")
    public FilterNewsPage setActiveCheckbox(boolean shouldBeChecked) {
        toggleCheckbox(activeCheckbox, shouldBeChecked);
        return this;
    }

    @Step("Set 'Inactive' checkbox to: {0}")
    public FilterNewsPage setInactiveCheckbox(boolean shouldBeChecked) {
        toggleCheckbox(inactiveCheckbox, shouldBeChecked);
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
        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
        return new NewsControlPanelPage(driver);
    }


}
