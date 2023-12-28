package com.appium.tests;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class ClickElementBasedOnPercentage {

    AndroidDriver androidDriver;
    public WebDriverWait wait;

    @BeforeTest
    public void launchApplication() throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/mobileApps/app-alpha.apk");
        desiredCapabilities.setCapability("appWaitActivity", "com.wam.android.*");
      //  desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "1200");
       // desiredCapabilities.setCapability("ignoreHiddenApiPolicyError", true);

        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
        wait = new WebDriverWait(androidDriver, Duration.ofSeconds(30));
    }

    @Test
    public void verifyLink() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*helpCentreLink\")")));
        WebElement helpLink = androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*helpCentreLink\")"));
        //tapElementUsingXPercentage(helpLink, 0.8);
        clickOnBesideElement(helpLink, 0.7,0);
        androidDriver.navigate().back();
        WebElement tc = androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*legalText\")"));
        tapElementUsingXPercentage(tc, 0.5);
        // tapElementUsingXYPercentage(tc, 0.5, 0.5);
        //clickOnBesideElement(helpLink, 0.7,0);
        System.out.println("Test");
    }

    public void getCPUdata() {
        List<List<Object>> performanceData = androidDriver.getPerformanceData("com.flipkart.android", "cpuinfo", 5);
        System.out.println("performanceData========" + performanceData);
    }

    public void clickOnBesideElement(WebElement element, double xPct, double yPct) {
//        int x = element.getCenter().getX();
//        int y = element.getCenter().getY();

//        System.out.println("x- value" + x);
//        System.out.println("y- value" + y);

        Rectangle elRect = element.getRect();
        Point nPoint = new Point(
                elRect.x + (int)(elRect.getWidth() * xPct),
                elRect.y + (int)(elRect.getHeight() * yPct)
        );


        TouchAction action = new TouchAction(androidDriver).longPress(PointOption.point((int) (nPoint.x + xPct), nPoint.y)).release();

        action.perform();

        System.out.println("Pass");
    }



    protected void tapAtPoint(Point point) {

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), point.x, point.y));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofMillis(200)));
        tap.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        androidDriver.perform(ImmutableList.of(tap));
    }

    protected void tapElement(WebElement el) {
        tapElementAt(el, 0.8, 0.5);
    }

    protected void tapElementUsingXPercentage(WebElement el, double xpt) {
        tapElementAt(el, xpt, 0.5);
    }

    protected void tapElementUsingXYPercentage(WebElement el, double xpt, double ypt) throws InterruptedException {
        Thread.sleep(3000);
        tapElementAt(el, xpt, ypt);
    }

    protected void tapElementAt(WebElement el, double xPct, double yPct) {
        Rectangle elRect = el.getRect();
        Point point = new Point(
                elRect.x + (int)(elRect.getWidth() * xPct),
                elRect.y + (int)(elRect.getHeight() * yPct)
        );
        tapAtPoint(point);
    }
}
