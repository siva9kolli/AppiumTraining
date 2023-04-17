package com.appium.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.devtools.v85.input.model.TouchPoint;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ScrollingTestUsingDynamicCoordinates {
    AndroidDriver androidDriver;
    WebDriverWait wait;

    @Test
    public void verifyNaukriHomePage() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/shiva/Documents/AppiumTraining2.0/src/main/resources/naukri.apk");

        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

        wait = new WebDriverWait(androidDriver, Duration.ofSeconds(30));
        Thread.sleep(5000);
        jsScroll("Up");
        jsScroll("Down");
        jsSwipe("up");
//        scrollToBottom();
//        swipeRightToLeft();
    }

    public void jsScroll(String direction){
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direction);
        params.put("strategy", "-android uiautomator");
        params.put("selector", "text(\"Oppo\")");
        js.executeScript("mobile:scroll", params);
    }

    public void scrollGesture(){
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("percent", 1);
        androidDriver.executeScript("mobile: scrollGesture", params);
    }

    public void jsSwipe(String dir){
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", dir);
        js.executeScript("mobile: swipe", params);
    }


    public void scrollToBottom(){
        Dimension size = androidDriver.manage().window().getSize();

        int y1 = size.getHeight(); // 1000
        int x1 = size.getWidth(); //500

        System.out.println("height :: " + y1);
        System.out.println("width :: " + x1 );

        int startY = (int)(y1*(.30));
        int endY = (int)(y1*(.70));
        int x = (int) (x1/2);

        System.out.println("startY :: " + startY);
        System.out.println("endY :: " + endY);

        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction
                .press(PointOption.point(x,startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(x, endY))
                .release()
                .perform();
    }

    public void swipeRightToLeft(){
        Dimension size = androidDriver.manage().window().getSize();

        int startX = (int)(size.getWidth() * (0.8));
        int endX = (int)(size.getWidth() * (0.2));
        int y = (int)(size.getHeight()/2);

        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction
                .press(PointOption.point(startX, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(endX, y))
                .release()
                .perform();

    }
}
