package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;


import annotations.User;
import extensions.AppiumExtension;
import extensions.UserExtension;
import io.appium.java_client.android.AndroidDriver;
import models.UserData;
import pages.MainPage;

@ExtendWith(UserExtension.class)
public abstract class BaseLoggedTest extends BaseTest {

    @BeforeEach
    public void login(@UserExtension.UserType(UserExtension.UserType.Type.VALID) UserData user) {
        getLoginPage().fillLoginPage(user.login(), user.password())
                .submit(MainPage.class);
        ;
    }

    protected MainPage openMainPage() {
        return new MainPage((AndroidDriver) AppiumExtension.getDriver());
    }

}
