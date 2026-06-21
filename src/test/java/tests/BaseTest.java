package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import driver.EmulatorDriver;
import extensions.AppiumExtension;
import io.appium.java_client.android.AndroidDriver;
import pages.LoginPage;
import pages.MainPage;

@ExtendWith(AppiumExtension.class)
public abstract class BaseTest {

    private static AndroidDriver driver;

    protected AndroidDriver driver() {
        return (AndroidDriver) AppiumExtension.getDriver();
    }

    protected LoginPage getLoginPage() {
        return new LoginPage(driver());
    }

}
