package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import models.News;

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
        try {
            List<WebElement> webElements = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(newsItemLocator)
            );

            return webElements.stream()
                    .filter(this::hasVisibleTitle)
                    .map(element -> new NewsControlPanelItem(driver, element))
                    .collect(Collectors.toList());
        } catch (TimeoutException e) {
            return Collections.emptyList();
        }
    }

    public List<NewsControlPanelItem> getAllNewsItems() {
        List<NewsControlPanelItem> allItems = new ArrayList<>();
        Set<String> seenTitles = new HashSet<>();
        int maxScrolls = 10;

        for (int i = 0; i < maxScrolls; i++) {
            List<NewsControlPanelItem> visibleItems = getVisibleNewsItems();

            if (visibleItems.isEmpty()) {
                break;
            }

            String lastTitle = visibleItems.getLast().title();
            if (seenTitles.contains(lastTitle)) {
                break;
            }

            for (NewsControlPanelItem item : visibleItems) {
                String title = item.title();
                if (!seenTitles.contains(title)) {
                    seenTitles.add(title);
                    allItems.add(item);
                }
            }
            if (!scrollDown()) {
                break;
            }
        }
        return allItems;
    }

    private boolean scrollDown() {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public NewsControlPanelItem getFirstNews() {
        List<NewsControlPanelItem> visibleItems = getVisibleNewsItems();
        if (visibleItems.isEmpty()) {
            throw new RuntimeException("Список новостей пуст!");
        }
        return visibleItems.getFirst();
    }

    public NewsControlPanelItem getLastNews() {
        List<NewsControlPanelItem> allItems = getAllNewsItems();
        if (allItems.isEmpty()) {
            throw new RuntimeException("Список новостей пуст!");
        }
        return allItems.getLast();
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

