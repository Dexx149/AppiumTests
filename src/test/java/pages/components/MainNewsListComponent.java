package pages.components;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class MainNewsListComponent extends BaseComponent<MainNewsListComponent> {


    private static final By newsItemLocator = AppiumBy.id("ru.iteco.fmhandroid:id/container_list_news_include_on_fragment_main");

    private final By allNewsButton = AppiumBy.id("ru.iteco.fmhandroid:id/all_news_text_view");

    public MainNewsListComponent(AppiumDriver driver) {
        super(driver,newsItemLocator);
    }

    public boolean isAllNewsButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(allNewsButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<NewsItem> getNewsItems() {
        List<WebElement> webElements = driver.findElements(newsItemLocator);

        return webElements.stream()
                .map(element -> new NewsItem(driver, element))
                .collect(Collectors.toList());
    }

    public int getNewsCount() {
        return getNewsItems().size();
    }

    public NewsItem getFirstNews() {
        return getNewsItems().getFirst();
    }

}