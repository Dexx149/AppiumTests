package driver;

import org.openqa.selenium.Capabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class EmulatorDriver {
    private static final Properties props = loadProperties();

    private static Properties loadProperties() {
        Properties p = new Properties();
        try (FileInputStream fis = new FileInputStream("local.properties")) {
            p.load(fis);
        } catch (IOException e) {
            System.err.println("local.properties не найден");
        }
        return p;
    }

    static String appiumDeviceName = props.getProperty("deviceName");
    static String appiumUrl = props.getProperty("appiumUrl");
    static String appiumPlatformName = props.getProperty("appiumPlatformName");
    static String appiumApp = props.getProperty("appiumApp");
    static String appiumAutomationName = props.getProperty("appiumAutomationName");

    public static AndroidDriver createAndroidDriver() {
        Capabilities options = new BaseOptions()
                .amend("platformName", appiumPlatformName)
                .amend("appium:automationName", appiumAutomationName)
                .amend("appium:deviceName", appiumDeviceName)
                .amend("appium:app", appiumApp + "\\app\\build\\outputs\\apk\\debug\\app-debug.apk")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        try {
            return new AndroidDriver(new URL(appiumUrl), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Неверный URL для Appium", e);
        }
    }
}

