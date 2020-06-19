package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HotelsFormPage extends BasePage {
    public HotelsFormPage(WebDriver driver) {
        super(driver);
    }

    private By destinationInputSelector = By.xpath("//span[contains(text(), 'Search by Hotel')]/parent::a/following-sibling::input");
    private String destinationSelector = "//span[text() = '<destination>']";
    private By checkInSelector = By.xpath("//input[@id='checkin']");
    private By checkOutSelector = By.xpath("//input[@id='checkout']");
    private By divSearchSeletor = By.xpath("//div[contains(@class, 'hotelsearch')]");
    private By childrenInputSelector = By.xpath("//input[@name='children']");
    private By adultInputSelector = By.xpath("//input[@name='adults']");
    private By addChildrenSelector = By.xpath("//input[@name='children']/following-sibling::span/button[contains(@class, 'bootstrap-touchspin-up')]");
    private By addAdultSelector = By.xpath("//input[@name='adults']/following-sibling::span/button[contains(@class, 'bootstrap-touchspin-up')]");
    private By removeAdultSelector = By.xpath("//input[@name='adults']/following-sibling::span/button[contains(@class, 'bootstrap-touchspin-down')]");

    private By searchButtonSelector = By.xpath("//button[@type='submit' and contains(text(), 'Search')]");

    public HotelsFormPage selectDestination(String destination) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(destinationInputSelector));
        WebElement input = driver.findElement(destinationInputSelector);
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", input);
        input.sendKeys(destination);

        By cityDestinationSelector = By.xpath(destinationSelector.replace("<destination>", destination));

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cityDestinationSelector, 0));
        driver.findElement(cityDestinationSelector).click();
        return new HotelsFormPage(driver);
    }

    public HotelsFormPage enterCheckInDate(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkInSelector));
        driver.findElement(checkInSelector).clear();
        driver.findElement(checkInSelector).sendKeys(date);
        return new HotelsFormPage(driver);
    }

    public HotelsFormPage enterCheckOutDate(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutSelector));
        driver.findElement(checkOutSelector).clear();
        driver.findElement(checkOutSelector).sendKeys(date);
        return new HotelsFormPage(driver);
    }

    public HotelsFormPage selectNumberOfChildren(int numberOfChildren) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(childrenInputSelector));
        for (int i = 0; i < numberOfChildren; i++)
            driver.findElement(addChildrenSelector).click();
        return new HotelsFormPage(driver);
    }

    public HotelsFormPage selectNumberOfAdults(int numberOfAdults) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(adultInputSelector));
        int currentNumberOfAdults = getCurrentNumberOfAdults();
        if (currentNumberOfAdults == numberOfAdults)
            return new HotelsFormPage(driver);
        else if (currentNumberOfAdults < numberOfAdults) {
            for(int i = 0; i < numberOfAdults-currentNumberOfAdults; i++)
                driver.findElement(addAdultSelector).click();
            return new HotelsFormPage(driver);
        } else {
            for(int i = 0; i < currentNumberOfAdults - numberOfAdults; i++)
                driver.findElement(removeAdultSelector).click();
            return new HotelsFormPage(driver);
        }
    }

    public HotelsSearchResultPage clickSearchButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButtonSelector));
        driver.findElement(searchButtonSelector).click();
        return new HotelsSearchResultPage(driver);
    }

    public String getTextFromInput() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(divSearchSeletor));
        return driver.findElement(divSearchSeletor).getText();
    }

    private Integer getCurrentNumberOfAdults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(adultInputSelector));
        String currentNumberOfAdults = driver.findElement(adultInputSelector).getAttribute("value");
        return Integer.parseInt(currentNumberOfAdults);
    }

}
