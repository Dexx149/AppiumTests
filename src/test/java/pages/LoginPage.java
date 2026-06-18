package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nonnull;

public class LoginPage extends BasePage<LoginPage> {

    private final By loginField = AppiumBy.androidUIAutomator("new UiSelector().text(\"Login\")");
    private final By passwordField = AppiumBy.androidUIAutomator("new UiSelector().text(\"Password\")");
    private final By signinButton = AppiumBy.id("ru.iteco.fmhandroid:id/enter_button");
    private final By errorMsg = AppiumBy.xpath("//android.widget.Toast[@text=\"Something went wrong. Try again later.\"]");


    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Fill login page with credentials: username: '{0}', password: {1}")
    @Nonnull
    public LoginPage fillLoginPage(String login, String password) {
        setUsername(login);
        setPassword(password);
        return this;
    }

    @Step("Set username: '{0}'")
    @Nonnull
    public LoginPage setUsername(String username) {
        $(loginField).sendKeys(username);
        return this;
    }

    @Step("Set password: '{0}'")
    @Nonnull
    public LoginPage setPassword(String password) {
        $(passwordField).sendKeys(password);
        return this;
    }

    @Step("Submit login")
    @Nonnull
    public <T extends BasePage<?>> T submit(Class<T> pageClass) {
        $(signinButton).click();
        try {
            return pageClass.getConstructor(AppiumDriver.class).newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать страницу " + pageClass.getSimpleName(), e);
        }
    }
    @Step("Check error on page: {error}")
    @Nonnull
    public boolean isErrorDisplayed() {
        return isElementPresent(errorMsg);
    }

}
