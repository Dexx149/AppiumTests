package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class QuotesListComponent extends BaseComponent<QuotesListComponent> {

    private static final By quotesListLocator = AppiumBy.id("ru.iteco.fmhandroid:id/our_mission_item_list_recycler_view");
    private static final By quotesItemLocator = AppiumBy.id("ru.iteco.fmhandroid:id/our_mission_item_material_card_view");


    public QuotesListComponent(AppiumDriver driver) {
        super(driver,quotesItemLocator);
    }


    public boolean isQuotesListVisible(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(quotesListLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<QuoteItem> getQuotesItems() {
        List<WebElement> webElements = driver.findElements(quotesItemLocator);

        return webElements.stream()
                .map(element -> new QuoteItem(driver, element))
                .collect(Collectors.toList());
    }

    public int getQuotesCount() {
        return getQuotesItems().size();
    }

}
