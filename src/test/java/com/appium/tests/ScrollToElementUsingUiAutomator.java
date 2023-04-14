package com.appium.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ScrollToElementUsingUiAutomator {
    AndroidDriver androidDriver;
    WebDriverWait wait;

    @Test
    public void verifyNaukriHomePage() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"/mobileApps/generalStore.apk");

        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

        wait = new WebDriverWait(androidDriver, Duration.ofSeconds(30));
        Thread.sleep(5000);
        androidDriver.findElement(AppiumBy.className("android.widget.Spinner")).click();
        scrollToElement();

    }

    public void scrollToElement(){
        androidDriver.findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"xzu\"))"));
    }
}
