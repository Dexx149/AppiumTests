package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import pages.FilterNewsPage;

public class CalendarComponent extends BaseComponent<CalendarComponent>{

    private static final By okButton = AppiumBy.id("android:id/button1");

    public CalendarComponent(AppiumDriver driver) {
        super(driver, AppiumBy.id("ru.iteco.fmhandroid:id/news_item_publish_date_text_input_edit_text"));
    }

    public FilterNewsPage selectCurrentDate(){
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
        return new FilterNewsPage(driver);
    }
}
