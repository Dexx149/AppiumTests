package pages;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import pages.components.MainMenu;
import pages.components.OurMissionButton;

public class BaseLoggedPage extends BasePage<BaseLoggedPage> {


    private MainMenu mainMenu;
    private OurMissionButton ourMissionButton;

    public BaseLoggedPage(AppiumDriver driver) {
        super(driver);
        mainMenu = new MainMenu(driver);
        ourMissionButton = new OurMissionButton(driver);
    }

    public MainMenu openMainMenu() {
        return mainMenu.click();
    }

    public
    OurMissionPage ourMissionClick() {
        return ourMissionButton.clickOurMission();
    }

}
