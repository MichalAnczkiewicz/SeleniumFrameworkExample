package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightsFormPage extends BasePage {
    public FlightsFormPage(WebDriver driver) {
        super(driver);
    }

    private By inputFromDestinationSelector = By.xpath("//span[text()='NYC']/parent::a/following-sibling::input");
    private By fromDestinationSelector = By.xpath("//span[text() = 'Wroclaw']");

    public FlightsFormPage selectFromDestination(String destination) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(inputFromDestinationSelector, 0));
        WebElement input = driver.findElement(inputFromDestinationSelector);
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", input);

        driver.findElement(inputFromDestinationSelector).sendKeys(destination);
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromDestinationSelector));
        driver.findElement(fromDestinationSelector).click();
        return new FlightsFormPage(driver);
    }

}
