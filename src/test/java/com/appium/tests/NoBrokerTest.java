package com.appium.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class NoBrokerTest {
    AndroidDriver androidDriver;
    WebDriverWait wait;

    @BeforeTest
    public void verifyNaukriHomePage() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"/mobileApps/NoBroker.apk");
        desiredCapabilities.setCapability("appPackage", "com.nobroker.app");
        desiredCapabilities.setCapability("appWaitActivity", "com.nobroker.app.activities.NBLauncherActivity");
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        wait = new WebDriverWait(androidDriver, Duration.ofSeconds(30));
        Thread.sleep(5000);
    }

    @Test
    public void searchLocation(){

        wait
                .until(ExpectedConditions
                        .elementToBeClickable(AppiumBy
                                .xpath("//*[ends-with(@resource-id,'yesPhoneState')]")))
                .click();

        //*[@text='Allow']
        wait
                .until(ExpectedConditions
                        .elementToBeClickable(AppiumBy
                                .androidUIAutomator("new UiSelector().text(\"While using the app\")")))
                .click();
        wait
                .until(ExpectedConditions
                        .elementToBeClickable(AppiumBy
                                .androidUIAutomator("new UiSelector().text(\"Allow\")")))
                .click();

        wait
                .until(ExpectedConditions
                        .elementToBeClickable(AppiumBy
                                .androidUIAutomator("new UiSelector().text(\"Allow\")")))
                .click();

                wait
                .until(ExpectedConditions
                .elementToBeClickable(AppiumBy
                        .id("searchEditHome")))
                .click();

                wait
                .until(ExpectedConditions
                .elementToBeClickable(AppiumBy
                        .className("android.widget.Spinner")))
                        .click();

        wait
                .until(ExpectedConditions
                        .elementToBeClickable(AppiumBy
                                .androidUIAutomator("new UiSelector().text(\"Hyderabad\")")))
                .click();

        wait
                .until(ExpectedConditions
                        .elementToBeClickable(AppiumBy
                                .androidUIAutomator("new UiSelector().resourceId(\"com.nobroker.app:id/localityAutoCompleteTxt\")")))
                .sendKeys("Dallas Centre");

        androidDriver.pressKey(new KeyEvent(AndroidKey.TAB));
        //androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
