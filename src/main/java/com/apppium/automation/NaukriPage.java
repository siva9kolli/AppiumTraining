package com.apppium.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class NaukriPage {
    AndroidDriver androidDriver;

    public NaukriPage(AndroidDriver androidDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
        this.androidDriver = androidDriver;
    }

    @AndroidFindBy(xpath = "//*[@text='Log in']")
    WebElement loginButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceIdMatches(\".*et_email\")")
    WebElement emailInputBox;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"password\")")
    WebElement passwordInputBox;

    @AndroidFindBy(id = "bt_login")
    WebElement signButton;



    public void login() throws InterruptedException {
        Thread.sleep(4000);
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
        loginButton.click();
        emailInputBox.sendKeys("test@yopmail.com");
        passwordInputBox.sendKeys("test@1234");
        signButton.click();
    }

}
