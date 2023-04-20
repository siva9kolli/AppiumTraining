package com.appium.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ScrollUsingJavaScriptExecutor {

    public void jsScroll(String direction){
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        Map<String, Object> params = new HashMap<>();
        //params.put("direction", direction);
        params.put("strategy", "-android uiautomator");
        params.put("selector", "text(\"Learn more\")");
        js.executeScript("mobile:scroll", params);
    }

    public void scrollGesture(){
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        Map<String, Object> params = new HashMap<>();
       // params.put("direction", "down");
        params.put("fromX", 0);
        params.put("fromY", 600);
        params.put("toX", 0);
        params.put("toY", 300);
        params.put("percent", 1);
        androidDriver.executeScript("mobile: scrollGesture", params);
    }


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
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
       /* for(int k=0; k<5; k++)
        {
            try{
                jsScroll("down");
            }catch (NoSuchElementException n){

            }
        }*/
        scrollGesture();

    }


}
