package org.example.tests.login;

import org.example.pages.AccountPage;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    private String username = "user@phptravels.com";
    private String correctPassword = "demouser";
    private String wrongPassword = "wrongPassword";
    private String expectedLoginError = "Invalid Email or Password";
    private String expectedWelcomeText = "Hi, Demo User";

    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountPage accountPage;

    @Test(priority = 0)
    public void should_display_error_when_wrong_password() {
        mainPage = new MainPage(driver);
        mainPage.clickOnMyAccountDropdown().clickOnLoginButton();

        loginPage = new LoginPage(driver);
        loginPage.enterUsername(username).enterPassword(wrongPassword).clickOnLoginButton();

        String actualLoginError = loginPage.getInvalidLoginError();
        Assert.assertEquals(actualLoginError, expectedLoginError, "Wrong error message!");
    }

    @Test(priority = 1)
    public void should_correct_login() {
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(username).enterPassword(correctPassword).clickOnLoginButton();

        accountPage = new AccountPage(driver);

        String actualWelcomeText = accountPage.getWelcomeText();
        Assert.assertEquals(actualWelcomeText, expectedWelcomeText, "Wrong welcome text!");
    }
}
