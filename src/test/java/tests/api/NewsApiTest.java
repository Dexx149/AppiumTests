package tests.api;

import static org.assertj.core.api.Assertions.assertThat;

import net.datafaker.Faker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import models.News;
import models.UserData;
import service.AuthService;
import service.NewsService;

public class NewsApiTest {

    @BeforeEach
    public void login() {
        UserData loginRequest = UserData.validUser();
        AuthService authService = new AuthService();
        authService.login(loginRequest);
    }

    @Test
    public void createNewsTest(){
        NewsService newsService = new NewsService();
        Response response = newsService.createNews(News.generateNews());
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    public void deleteNewsTest(){
        NewsService newsService = new NewsService();
        News newsToCreate = News.generateNews();
        Response response = newsService.createNews(newsToCreate);
        assertThat(response.statusCode()).isEqualTo(200);
        News createdNews = response.as(News.class);
        response = newsService.deleteNews(createdNews.id());
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    public void updateNewsTest(){
        Faker faker = new Faker();
        NewsService newsService = new NewsService();
        News newsToCreate = News.generateNews();
        Response response = newsService.createNews(newsToCreate);
        assertThat(response.statusCode()).isEqualTo(200);
        News createdNews = response.as(News.class);
        News updatedNews = new News(
                createdNews.id(),
                faker.number().numberBetween(1, 9),
                faker.lorem().characters(20),
                faker.lorem().characters(50),
                createdNews.creatorId(),
                createdNews.publishDate(),
                createdNews.publishDate(),
                false,
                createdNews.creatorName()
        );

        Response resultResponse = newsService.updateNews(updatedNews);
        assertThat(resultResponse.statusCode()).isEqualTo(200);
        News result = resultResponse.as(News.class);

        assertThat(result.id())
                .isEqualTo(createdNews.id());

        assertThat(result.title())
                .isEqualTo(updatedNews.title());

        assertThat(result.description())
                .isEqualTo(updatedNews.description());

        assertThat(result.newsCategoryId())
                .isEqualTo(updatedNews.newsCategoryId());

        assertThat(result.publishEnabled())
                .isEqualTo(updatedNews.isPublished());
    }

}
