package tests.e2e;

import org.junit.jupiter.api.extension.ExtendWith;

import extensions.AppiumExtension;
import io.appium.java_client.android.AndroidDriver;
import pages.LoginPage;

@ExtendWith(AppiumExtension.class)
public abstract class BaseTest {

    protected AndroidDriver driver() {
        return (AndroidDriver) AppiumExtension.getDriver();
    }

    protected LoginPage getLoginPage() {
        return new LoginPage(driver());
    }

}
