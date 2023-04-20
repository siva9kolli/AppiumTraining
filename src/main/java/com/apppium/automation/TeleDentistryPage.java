package com.apppium.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class TeleDentistryPage {

    AndroidDriver androidDriver;

    public TeleDentistryPage(AndroidDriver androidDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
        this.androidDriver = androidDriver;
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"While using the app\")")
    WebElement whileusingAppPopUpButton;

    @FindBy(css = "input[type='email']")
    WebElement emailInputBox;

    public void login(){
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(androidDriver.getContext());
        whileusingAppPopUpButton.click();


        System.out.println(androidDriver.getContext());
        System.out.println(androidDriver.getContextHandles());

        Set<String> contexts = androidDriver.getContextHandles();

        if(contexts.contains("WEBVIEW_com.tela.app")){
            androidDriver.context("WEBVIEW_com.tela.app");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailInputBox.sendKeys("test@yopmail.com");
    }

}
