package pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import pages.components.ControlPanelNewsListComponent;
import pages.components.NewsControlPanelItem;

public class NewsControlPanelPage extends BaseLoggedPage {

    private final By header = AppiumBy.id("ru.iteco.fmhandroid:id/container_custom_app_bar_include_on_fragment_news_control_panel");
    private final By sortButton = AppiumBy.id("ru.iteco.fmhandroid:id/sort_news_material_button");
    private final By filterButton = AppiumBy.id("ru.iteco.fmhandroid:id/filter_news_material_button");
    private final By confirmDeleteButton = AppiumBy.id("android:id/button1");



    private final ControlPanelNewsListComponent newsListComponent;
    public NewsControlPanelPage(AppiumDriver driver) {
        super(driver);
        newsListComponent = new ControlPanelNewsListComponent(driver);
    }

    @Step("Check that control panel page is loaded")
    public NewsControlPanelPage checkThatPageLoaded() {
        assertThat(isElementDisplayed(header)).isTrue();
        return this;
    }

    @Step("Check that news list is displayed")
    public NewsControlPanelPage checkThatNewsListIsDisplayed() {
        assertThat(newsListComponent.isNewsListVisible()).isTrue();
        assertThat(newsListComponent.getVisibleNewsCount()).isGreaterThan(0);
        return this;
    }

    @Step("First news click")
    public NewsControlPanelPage firstNewsClick() {
        newsListComponent.getVisibleNewsItems().getFirst().click();
        return this;
    }
    @Step("Check That Description Text Is Displayed")
    public NewsControlPanelPage checkThatFirstNewsDescriptionIsDisplayed() {
        wait.until(driver -> !newsListComponent.getVisibleNewsItems().isEmpty());
        assertThat(newsListComponent.getVisibleNewsItems().getFirst().isDescriptionTextDisplayed()).isTrue();
        return this;
    }

    @Step("Check That Description Text Is Not Displayed")
    public NewsControlPanelPage checkThatFirstNewsDescriptionIsNotDisplayed() {

        wait.until(driver -> !newsListComponent.getVisibleNewsItems().isEmpty());
        assertThat(newsListComponent.getVisibleNewsItems().getFirst().isDescriptionTextDisplayed())
                .isFalse();

        return this;
    }

    @Step("Check That Sort is working")
    public NewsControlPanelPage checkThatSortIsWorking() {

        String firstTitleBefore = newsListComponent.getFirstNews().getTitle();
        String lastTitleBefore = newsListComponent.getLastNews().getTitle();

        wait.until(ExpectedConditions.elementToBeClickable(sortButton)).click();

        String firstTitleAfter = newsListComponent.getFirstNews().getTitle();
        String lastTitleAfter = newsListComponent.getLastNews().getTitle();

        assertThat(firstTitleAfter)
                .isEqualTo(lastTitleBefore);
        assertThat(lastTitleAfter)
                .isEqualTo(firstTitleBefore);

        return this;
    }

    @Step("Delete first news and verify")
    // добавить сюда шаги в allure
    public NewsControlPanelPage deleteFirstNews() {
        String title = newsListComponent.getFirstNews().getTitle();

        newsListComponent.getFirstNews().clickDeleteButton();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();

        List<String> titles = newsListComponent.getVisibleNewsItems().stream()
                .map(NewsControlPanelItem::getTitle)
                .collect(Collectors.toList());

        assertThat(titles)
                .doesNotContain(title);

        return this;
    }

    @Step("Open filter")
    public FilterNewsPage openFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
        return new FilterNewsPage(driver);
    }
}
