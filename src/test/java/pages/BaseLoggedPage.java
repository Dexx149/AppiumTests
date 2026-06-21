package pages;

import io.appium.java_client.AppiumDriver;
import pages.components.MainMenu;

public class BaseLoggedPage extends  BasePage<BaseLoggedPage>{


    private MainMenu mainMenu;
    public BaseLoggedPage(AppiumDriver driver) {
        super(driver);
        mainMenu= new MainMenu(driver);
    }

    protected MainMenu getMainMenu(){
        return mainMenu;
    }
}
