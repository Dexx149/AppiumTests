package pages;

import static org.assertj.core.api.Assertions.assertThat;

import net.datafaker.Faker;

import org.assertj.core.api.AssertionsForClassTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import models.News;
import models.NewsCategory;
import pages.components.ControlPanelNewsListComponent;
import pages.components.NewsControlPanelItem;
import service.NewsService;

public class NewsControlPanelPage extends BaseLoggedPage {

    private final By header = AppiumBy.id("ru.iteco.fmhandroid:id/container_custom_app_bar_include_on_fragment_news_control_panel");
    private final By sortButton = AppiumBy.id("ru.iteco.fmhandroid:id/sort_news_material_button");
    private final By filterButton = AppiumBy.id("ru.iteco.fmhandroid:id/filter_news_material_button");
    private final By confirmDeleteButton = AppiumBy.id("android:id/button1");
    private final By emptyListMessage = AppiumBy.id("ru.iteco.fmhandroid:id/control_panel_empty_news_list_text_view");

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

        String firstTitleBefore = newsListComponent.getFirstNews().title();
        String lastTitleBefore = newsListComponent.getLastNews().title();

        wait.until(ExpectedConditions.elementToBeClickable(sortButton)).click();

        String firstTitleAfter = newsListComponent.getFirstNews().title();
        String lastTitleAfter = newsListComponent.getLastNews().title();

        assertThat(firstTitleAfter)
                .isEqualTo(lastTitleBefore);
        assertThat(lastTitleAfter)
                .isEqualTo(firstTitleBefore);

        return this;
    }

    @Step("Delete first news and verify")
    // добавить сюда шаги в allure
    public NewsControlPanelPage deleteFirstNews() {
        String title = newsListComponent.getFirstNews().title();

        newsListComponent.getFirstNews().clickDeleteButton();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();

        List<String> titles = newsListComponent.getVisibleNewsItems().stream()
                .map(NewsControlPanelItem::title)
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



    @Step("Apply random filter and verify it works")
    public NewsControlPanelPage applyRandomFilterAndVerify() {
        FilterContext context = generateRandomFilter();
        applyFilter(context);
        verifyFilterResult(context);
        return this;
    }


    private record FilterContext(Integer categoryId, Boolean publishEnabled) {}

    @Step("Generate random filter parameters")
    private FilterContext generateRandomFilter() {
        Faker faker = new Faker();

        int categoryId = faker.number().numberBetween(1, NewsCategory.values().length);

        boolean publishEnabled = faker.bool().bool();

        return new FilterContext(categoryId, publishEnabled);
    }

    @Step("Apply filter in UI: category={0}, publishEnabled={1}")
    private NewsControlPanelPage applyFilter(FilterContext context) {
        FilterNewsPage filterPage = openFilter();

        if (context.categoryId() != null) {
            String categoryName = NewsCategory.fromId(context.categoryId()).getDisplayName();
            filterPage.selectCategory(categoryName);
        }

        if (context.publishEnabled() != null) {
            filterPage.setActiveCheckbox(context.publishEnabled());
            filterPage.setInactiveCheckbox(!context.publishEnabled());
        }

        filterPage.applyFilter();
        return this;
    }

    @Step("Verify filter result matches API")
    private NewsControlPanelPage verifyFilterResult(FilterContext context) {
        NewsService newsService = new NewsService();
        List<News> apiNews = newsService.getNewsVisibleOnControlPanel();

        List<News> expectedNews = apiNews.stream()
                .filter(n -> context.categoryId() == null || n.newsCategoryId() == context.categoryId())
                .filter(n -> context.publishEnabled() == null || n.publishEnabled() == context.publishEnabled())
                .toList();

        if (expectedNews.isEmpty()) {
            checkThatNewsListIsEmpty();
        } else {
            checkThatNewsListMatches(expectedNews);
        }
        return this;
    }

    @Step("Check that news list is empty (no news match filter)")
    private NewsControlPanelPage checkThatNewsListIsEmpty() {

        assertThat(isElementDisplayed(emptyListMessage)).isTrue();

        assertThat(newsListComponent.getAllNewsItems())
                .isEmpty();

        return this;
    }

    @Step("Check that news list matches expected news")
    private NewsControlPanelPage checkThatNewsListMatches(List<News> expectedNews) {
        List<News> actualNews = newsListComponent.getAllNewsItems().stream()
                .map(NewsControlPanelItem::toNews)
                .toList();

        assertThat(actualNews)
                .as("UI должен показывать те же новости, что и API")
                .usingRecursiveComparison()
                .comparingOnlyFields("title", "publishEnabled")
                .ignoringCollectionOrder()
                .isEqualTo(expectedNews);

        return this;
    }
}
