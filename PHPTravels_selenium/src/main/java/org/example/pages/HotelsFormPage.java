package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HotelsFormPage extends BasePage {
    public HotelsFormPage(WebDriver driver) {
        super(driver);
    }

    private By destinationInputSelector = By.xpath("//span[contains(text(), 'Search by Hotel')]/parent::a/following-sibling::input");
    private String destinationSelector = "//span[text() = '<destination>']";
    private By checkInSelector = By.xpath("//input[@id='checkin']");
    private By checkOutSelector = By.xpath("//input[@id='checkout']");

    public HotelsFormPage selectDestination(String destinaion) {


        wait.until(ExpectedConditions.visibilityOfElementLocated(destinationInputSelector));
        WebElement input = driver.findElement(destinationInputSelector);
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", input);
        input.sendKeys(destinaion);

        By cityDestinationSelector = By.xpath(destinationSelector.replace("<destination>", destinaion));

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cityDestinationSelector, 0));
        driver.findElement(cityDestinationSelector).click();
        return new HotelsFormPage(driver);
    }

}
