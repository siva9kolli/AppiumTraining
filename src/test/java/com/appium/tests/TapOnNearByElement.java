package com.appium.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.input.model.TouchPoint;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TapOnNearByElement {
    AndroidDriver androidDriver;
    WebDriverWait wait;

    @Test
    public void verifyNaukriHomePage() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/shiva/Documents/AppiumTraining2.0/src/main/resources/naukri.apk");

        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4726"), desiredCapabilities);

        wait = new WebDriverWait(androidDriver, Duration.ofSeconds(30));
        Thread.sleep(4000);
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));


        WebElement registerButton = androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*tv_register\")"));
        System.out.println(registerButton.getSize());
        System.out.println(registerButton.getLocation());
        System.out.println(registerButton.getRect().getX());
        System.out.println(registerButton.getRect().getY());

        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction.press(PointOption.point(registerButton.getRect().getX()+400, registerButton.getRect().getY()))
                .release()
                .perform();

    }



}
