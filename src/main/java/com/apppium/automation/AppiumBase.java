package com.apppium.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumBase {
    public AndroidDriver androidDriver;

    @BeforeSuite
    public void openApplication() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
       // desiredCapabilities.setCapability("chromedriverExecutableDir", "src/main/resources");
        //desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"/src/main/resources/naukri.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"/mobileApps/teleDoc.apk");
        //desiredCapabilities.setCapability("chromedriver_autodownload", true);
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
}
