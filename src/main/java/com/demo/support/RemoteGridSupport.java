package com.demo.support;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.demo.base.DriverScript;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RemoteGridSupport extends DriverScript {
    public static String GRID_REMOTE_URL = null;

    public static void setUp(String browser, String methodName) {
        GRID_REMOTE_URL = "http://18.220.45.215:4444/wd/hub";
        DesiredCapabilities cap = new DesiredCapabilities();

        if (browser.toUpperCase().equals("CHROME")) {
            WebDriverManager.chromedriver().setup();
            cap.setCapability("browserName", "chrome");
        } else if (browser.toUpperCase().equals("FIREFOX")) {
            WebDriverManager.firefoxdriver().setup();
            cap.setCapability("browserName", "firefox");
        }
        try {
            driver = new RemoteWebDriver(new URL(GRID_REMOTE_URL), cap);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.get(baseUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}