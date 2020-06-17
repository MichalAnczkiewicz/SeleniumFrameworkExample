package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }
    private static Logger log = LogManager.getLogger(MainPage.class.getName());

    private By myAccountDropdownSelector = By.xpath("//ul/li[3]/*//a[@id='dropdownCurrency']");
    private By loginButtonSelector = By.xpath("//a[text() = 'Login']");

    public MainPage clickOnMyAccountDropdown() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(myAccountDropdownSelector, 0));
        driver.findElement(myAccountDropdownSelector).click();
        return new MainPage(driver);
    }

    public LoginPage clickOnLoginButton(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginButtonSelector));
        driver.findElement(loginButtonSelector).click();
        return new LoginPage(driver);
    }


}
