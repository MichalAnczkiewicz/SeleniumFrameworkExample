package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By usernameInputSelector = By.xpath("//input[@name='username']");
    private By passwordInputSelector = By.xpath("//input[@name='password']");
    private By errorSelector = By.xpath("//div[@class='resultlogin']/div[@class='alert alert-danger']");
    private By loginButtonSelector = By.xpath("//button[text()='Login']");

    public LoginPage enterUsername(String username){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(usernameInputSelector, 0));
        driver.findElement(usernameInputSelector).clear();
        driver.findElement(usernameInputSelector).sendKeys(username);
        return new LoginPage(driver);
    }
    public LoginPage enterPassword(String password){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(passwordInputSelector, 0));
        driver.findElement(passwordInputSelector).clear();
        driver.findElement(passwordInputSelector).sendKeys(password);
        return new LoginPage(driver);
    }

    public AccountPage clickOnLoginButton(){
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(loginButtonSelector, 0));
        driver.findElement(loginButtonSelector).click();
        return new AccountPage(driver);
    }

    public String getInvalidLoginError(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorSelector));
        return driver.findElement(errorSelector).getText();
    }

}
