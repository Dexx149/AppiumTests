package pages.components;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import pages.NewsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class MainNewsListComponent extends BaseComponent<MainNewsListComponent> {


    private static final By newsListLocator = AppiumBy.id("ru.iteco.fmhandroid:id/container_list_news_include_on_fragment_main");
    private static final By newsItemLocator = AppiumBy.id("ru.iteco.fmhandroid:id/container_list_news_include_on_fragment_main");

    private final By allNewsButton = AppiumBy.id("ru.iteco.fmhandroid:id/all_news_text_view");

    public MainNewsListComponent(AppiumDriver driver) {
        super(driver,newsListLocator);
    }

    // Используется проверка видимости кнопки вместо провекрки видимости списка так, как
    // кнопка находится в элементе списка, значит если видна кнопка, то и соттветственно виден и список
    public boolean isAllNewsButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(allNewsButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void allNewsButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(allNewsButton)).click();
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