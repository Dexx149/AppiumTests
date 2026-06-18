package tests;

import annotations.User;
import extensions.UserExtension.UserType;
import extensions.UserExtension.UserType.Type;
import models.UserData;
import pages.LoginPage;
import pages.MainPage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LoginTest extends BaseTest {

    @Test
    @User
    public void shouldLoginWithValidCredentials(@UserType(Type.VALID) UserData user) {
        getLoginPage().fillLoginPage(user.login(),user.password())
                .submit(MainPage.class)
                .isHeaderDisplayed();
    }


    @Test
    @User
    public void shouldShowErrMsgWithInvalidCredentials(@UserType(Type.INVALID) UserData user) {
        getLoginPage().fillLoginPage(user.login(),user.password())
                .submit(LoginPage.class)
                .isErrorDisplayed();
    }


}
