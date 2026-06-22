package pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

public class OurMissionPage extends BaseLoggedPage{

    private final By header = AppiumBy.id("ru.iteco.fmhandroid:id/our_mission_title_text_view");
    public OurMissionPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Check that OurMission page is loaded")
    public OurMissionPage checkThatPageLoaded() {
        assertThat(isElementDisplayed(header))
                .isTrue();
        return this;
    }
}
