package pages.components;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import pages.MainPage;
import pages.OurMissionPage;

public class OurMissionButton extends BaseComponent<OurMissionButton> {


    public OurMissionButton(AppiumDriver driver) {
        super(driver, AppiumBy.id("ru.iteco.fmhandroid:id/our_mission_image_button"));

    }

    public OurMissionPage clickOurMission() {
        getSelf().click();
        return new OurMissionPage(driver);
    }
}
