package com.appium.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class SwitchToBrowserAndComeback {
AndroidDriver androidDriver;
    @Test
    public void switchToChrome() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability("appPackage","com.google.android.apps.messaging");
        desiredCapabilities.setCapability("appActivity","com.google.android.apps.messaging.home.HomeActivity");
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        try{
            androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Agree\")")).click();
        }catch (Exception e){

        }

        androidDriver.findElement(AppiumBy.accessibilityId("Start chat")).click();

        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*recipient_text_view\")")).sendKeys("9885952948");

        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));

        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Text message\")")).sendKeys("https://sivaprasadreddykolli.github.io/");
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*send_message_button_icon\")")).click();

        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"https://sivaprasadreddykolli.github.io/\").instance(0)")).click();
        Thread.sleep(5000);

        switchToWebView();

       /* try {
            //androidDriver.findElement(AppiumBy.id("terms_accept")).click();
            try {
                androidDriver.findElement(AppiumBy.xpath("//*[@text='Accept & continue']")).click();
            }catch (Exception e1){

            }
            androidDriver.findElement(AppiumBy.xpath("//*[@text='No thanks']")).click();
            //androidDriver.findElement(AppiumBy.id("negative_button")).click();
        } catch (Exception e){

        }*/
        androidDriver.activateApp("com.android.chrome");
        Thread.sleep(5000);
        switchToWebView();
        String url = androidDriver.getCurrentUrl();
        System.out.println("URL --- " + url);

        androidDriver.findElement(By.cssSelector("a[href*='linkedin']")).click();
       // androidDriver.findElement(By.id("email-or-phone")).sendKeys("9885952948");
        androidDriver.close();
        Thread.sleep(1000);
        androidDriver.activateApp("com.google.android.apps.messaging");
        Thread.sleep(10000);
        System.out.println("After switching to Message App");
        switchToNativeView();
        String msg = androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"https://sivaprasadreddykolli.github.io/\").instance(0)")).getText();
        System.out.println(msg);
    }


    public void switchToWebView() {
        Set<String> contexts = androidDriver.getContextHandles();
        for (String s : contexts) {
            System.out.println("context name=======" + contexts);
            if (s.contains("WEBVIEW")) {
                System.out.println("Mobile Web View found");
                androidDriver.context(s);
            } else if(s.contains("NATIVE_APP")){
                System.out.println("Mobile Native View found");
                androidDriver.context(s);
            }
        }
    }

    public void switchToNativeView(){
        Set<String> contexts = androidDriver.getContextHandles();
        for (String s : contexts) {
            System.out.println("context name=======" + contexts);
            if (s.contains("NATIVE")) {
                System.out.println("Mobile Native View found");
                androidDriver.context(s);
            }
        }
    }
}
