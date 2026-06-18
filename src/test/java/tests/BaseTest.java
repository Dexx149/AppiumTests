package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import driver.EmulatorDriver;
import io.appium.java_client.android.AndroidDriver;
import pages.LoginPage;

public abstract class BaseTest {

    private static AndroidDriver driver;

    @BeforeEach
    public void setUp() {
        driver = EmulatorDriver.createAndroidDriver();
    }
    protected LoginPage getLoginPage() {
        return new LoginPage(driver);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
