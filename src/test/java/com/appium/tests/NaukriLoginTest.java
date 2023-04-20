package com.appium.tests;

import com.apppium.automation.AppiumBase;
import com.apppium.automation.NaukriPage;
import com.apppium.automation.TeleDentistryPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NaukriLoginTest extends AppiumBase {
    public NaukriPage naukriPage;
    public TeleDentistryPage teleDentistryPage;
    @BeforeClass
    public void initializePages(){
        naukriPage = new NaukriPage(androidDriver);
        teleDentistryPage = new TeleDentistryPage(androidDriver);
    }

    @Test(enabled = false)
    public void login() throws InterruptedException {
        naukriPage.login();
    }

    @Test(enabled = true)
    public void loginAsAdmin() throws InterruptedException {
        teleDentistryPage.login();
    }
}
