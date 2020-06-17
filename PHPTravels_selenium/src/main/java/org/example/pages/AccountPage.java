package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage {
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    private By welcomeTextSelector = By.xpath("//h3[contains(text(), 'Hi')]");

    public String getWelcomeText(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeTextSelector));
        return driver.findElement(welcomeTextSelector).getText();
    }

}
