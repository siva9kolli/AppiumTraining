package com.appium.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class ExecuteTestOnBrowserStack {
    AndroidDriver androidDriver;
    WebDriverWait wait;
    AppiumDriver appiumDriver;

    /**
     * To upload file to Browserstack
     * curl -u "abc:xyz" -X POST "https://api-cloud.browserstack.com/app-automate/upload" -F "file=@/Users/shiva/Documents/AppiumTraining2.0/src/main/resources/naukri.apk"
     * @throws MalformedURLException
     * @throws InterruptedException
     */

    @Test
    public void verifyNaukriHomePage() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Samsung Galaxy S22");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "bs://168121ca4650d8f0633fab33a80497c26bfd55b4");

        desiredCapabilities.setCapability("name", "AAA Test");
        desiredCapabilities.setCapability("project", "AAA Assignment");
        desiredCapabilities.setCapability("build", "AAA Automation");
        desiredCapabilities.setCapability("interactiveDebugging", true);


        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("appiumVersion", "2.0.0");
        desiredCapabilities.setCapability("bstack:options", browserstackOptions);

        androidDriver = new AndroidDriver(new URL("https://abs:xyz@hub.browserstack.com/wd/hub"), desiredCapabilities);

        wait = new WebDriverWait(androidDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("naukriApp.appModules.login:id/textViewLabel")));
        String naukriStartPageHeading = androidDriver.findElement(AppiumBy.id("naukriApp.appModules.login:id/textViewLabel")).getText();
        System.out.println(naukriStartPageHeading);
        Assert.assertEquals(naukriStartPageHeading, "Get started on Naukri");
        Thread.sleep(1000);
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
        androidDriver.findElement(AppiumBy.id("tv_log_in")).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter password\")")));
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*et_email\")")).sendKeys("siva@yopmail@.com");
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter password\")")).sendKeys("AppiumTest");
    }

}
