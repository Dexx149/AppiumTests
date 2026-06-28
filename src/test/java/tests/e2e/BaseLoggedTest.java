package tests.e2e;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;


import extensions.AppiumExtension;
import extensions.UserExtension;
import io.appium.java_client.android.AndroidDriver;
import models.UserData;
import pages.MainPage;
import service.AuthService;

@ExtendWith(UserExtension.class)
public abstract class BaseLoggedTest extends BaseTest {


    @BeforeAll
    static void apiLogin() {
        AuthService authService = new AuthService();
        authService.login(UserData.validUser());
    }

    @BeforeEach
    public void login(@UserExtension.UserType(UserExtension.UserType.Type.VALID) UserData user) {
        getLoginPage().fillLoginPage(user.login(), user.password())
                .submit(MainPage.class);
        ;
    }

    protected MainPage openMainPage() {
        return new MainPage(AppiumExtension.getDriver());
    }

}
