package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class ControlPanelNewsListComponent extends BaseComponent<ControlPanelNewsListComponent> {

    private static final By newsListLocator = AppiumBy.id("ru.iteco.fmhandroid:id/news_list_recycler_view");
    private static final By newsItemLocator = AppiumBy.id("ru.iteco.fmhandroid:id/news_item_material_card_view");

    public ControlPanelNewsListComponent(AppiumDriver driver) {
        super(driver, newsListLocator);
    }

    public boolean isNewsListVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(newsListLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<NewsControlPanelItem> getVisibleNewsItems() {
        List<WebElement> webElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(newsItemLocator)
        );

        return webElements.stream()
                .filter(this::hasVisibleTitle)
                .map(element -> new NewsControlPanelItem(driver, element))
                .collect(Collectors.toList());
    }

    public NewsControlPanelItem getFirstNews() {
        List<NewsControlPanelItem> visibleItems = getVisibleNewsItems();
        return visibleItems.getFirst();
    }


    public NewsControlPanelItem getLastNews() {
        Set<String> seenTitles = new HashSet<>();
        NewsControlPanelItem lastItem = null;
        int maxScrolls = 10;

        for (int i = 0; i < maxScrolls; i++) {
            List<NewsControlPanelItem> visibleItems = getVisibleNewsItems();
            lastItem = visibleItems.getLast();
            String lastTitle = lastItem.getTitle();

            if (seenTitles.contains(lastTitle)) {
                return lastItem;
            }

            for (NewsControlPanelItem item : visibleItems) {
                seenTitles.add(item.getTitle());
            }
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
            ));
        }
        return lastItem;
    }

    // Метод для проверки видимости заголовка новости в карточке
    private boolean hasVisibleTitle(WebElement element) {
        try {
            WebElement titleElement = element.findElement(
                    By.id("ru.iteco.fmhandroid:id/news_item_title_text_view")
            );
            return titleElement.isDisplayed() && !titleElement.getText().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    public int getVisibleNewsCount() {
        return getVisibleNewsItems().size();
    }
}

