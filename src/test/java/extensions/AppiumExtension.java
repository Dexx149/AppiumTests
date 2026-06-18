package extensions;

import driver.EmulatorDriver;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class AppiumExtension implements
        BeforeEachCallback,
        AfterEachCallback,
        TestExecutionExceptionHandler,
        LifecycleMethodExecutionExceptionHandler {

    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        AppiumDriver driver = EmulatorDriver.createAndroidDriver();
        driverThreadLocal.set(driver);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        AppiumDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        doScreenshot();
        throw throwable;
    }

    @Override
    public void handleBeforeEachMethodExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        doScreenshot();
        throw throwable;
    }

    @Override
    public void handleAfterEachMethodExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        doScreenshot();
        throw throwable;
    }

    public static AppiumDriver getDriver() {
        AppiumDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new IllegalStateException("AppiumDriver не инициализирован! Проверьте @ExtendWith(AppiumExtension.class)");
        }
        return driver;
    }

    private static void doScreenshot() {
        AppiumDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(
                        "Screenshot on fail",
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        "png"
                );
            } catch (Exception e) {
                System.err.println("Не удалось сделать скриншот: " + e.getMessage());
            }
        }
    }
}