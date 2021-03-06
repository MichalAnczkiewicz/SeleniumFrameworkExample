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
    private String navigationMenuOptions = "//li/a[@href='#<option>']";


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

    public void selectNavigationOption(String option){
        By navOption = By.xpath(navigationMenuOptions.replace("<option>", option));
        wait.until(ExpectedConditions.visibilityOfElementLocated(navOption));
        driver.findElement(navOption).click();
        pageToReturn(option);
    }

    private Object pageToReturn(String option){
        switch(option){
            case "hotels": return new HotelsFormPage(driver);
            case "flights": return new FlightsFormPage(driver);
            case "tours": return new ToursFormPage(driver);
            case "cars": return new CarsFormPage(driver);
            case "visa": return new VisaFormPage(driver);
            default:
                return 0;
        }
    }


}
