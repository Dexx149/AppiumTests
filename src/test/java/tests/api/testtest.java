package tests.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import models.News;
import models.UserData;
import service.AuthService;
import service.BaseService;
import service.NewsService;

public class testtest extends BaseService {


    @BeforeEach
    public void login() {
        UserData loginRequest = UserData.validUser();
        AuthService authService = new AuthService();
        authService.login(loginRequest);
    }
    @Test
    public void testt(){
        NewsService newsService = new NewsService();
        List<News> newsList = newsService.getNewsVisibleOnControlPanel();
    }

}
