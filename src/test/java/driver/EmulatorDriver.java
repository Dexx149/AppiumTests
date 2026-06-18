package driver;

import org.openqa.selenium.Capabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class EmulatorDriver {
    public static AndroidDriver createAndroidDriver() {
        Capabilities options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:automationName", "UiAutomator2")
                .amend("appium:deviceName", "WinTestEmulator")
                .amend("appium:app", "C:\\Users\\user\\Desktop\\Diplom\\fmh\\app\\build\\outputs\\apk\\debug\\app-debug.apk")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Неверный URL для Appium", e);
        }
    }
}

