package com.appium.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class SwitchToChromeFromNativeApp {
    AndroidDriver androidDriver;
    @Test
    public void verifyMessagesAndLinks() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability("appPackage","com.google.android.apps.messaging");
        desiredCapabilities.setCapability("appActivity","com.google.android.apps.messaging.ui.ConversationListActivity");
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

       // androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Got it\")")).click();
        //androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Agree\")")).click();

        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Start chat\")")).click();

       // androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Type a name, phone number, or email\")")).sendKeys("+919885952948");
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*recipient_text_view\")")).sendKeys("9885952948");
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));

        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Text message\")")).sendKeys("https://sivaprasadreddykolli.github.io/");
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceIdMatches(\".*send_message_button_icon\")")).click();

        List<WebElement> links = androidDriver.findElements(AppiumBy.accessibilityId("You said https://sivaprasadreddykolli.github.io/ ., Now, SMS"));
        System.out.println(links.size());

       // androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"https://sivaprasadreddykolli.github.io/\").instance(0)")).click();

//        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Accept & continue\")")).click();
//        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"No thanks\")")).click();

        Set<String> contexts = androidDriver.getContextHandles();
        System.out.println("contexts :: " + contexts);
        for(String c:contexts){
            if(c.contains("WEBVIEW")){
                androidDriver.context(c);
                System.out.println("Successfully switched to browser");
            }
        }

        String url = androidDriver.getCurrentUrl();
        System.out.println(url);
        Thread.sleep(5000);

        androidDriver.activateApp("com.google.android.apps.messaging");
        Thread.sleep(5000);
        Set<String> context1 = androidDriver.getContextHandles();
        System.out.println("context1 :: " + context1);
        for(String c:context1){
            if(c.contains("NATIVE")){
                androidDriver.context(c);
                System.out.println("Successfully switched to Native App");
            }
        }

        System.out.println(androidDriver.findElements(AppiumBy.xpath("//*[contains(@text,'github')]")).get(0).getText());


    }
}
