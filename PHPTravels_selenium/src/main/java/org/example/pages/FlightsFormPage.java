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
    private String fromDestination = "//span[text() = '<city>']";

    private By inputToDestinationSelector = By.xpath("//span[text()='MIA']/parent::a/following-sibling::input");
    private String toDestination = "//span[text()= '<city>']";

    private By flightClassDivSelector = By.xpath("//div[contains(@class , 'flightclass')]");
    private String flightClassOption = "//li[text()='<flightClass>']";

    private By departInputSelector = By.xpath("//input[@id='FlightDateStart']");

    private By adultInputSelector = By.xpath("//input[@name='fadults']");
    private By addAdultSelector = By.xpath("//input[@name='fadults']/following-sibling::span/button[contains(@class, 'bootstrap-touchspin-up')]");
    private By removeAdultSelector = By.xpath("//input[@name='fadults']/following-sibling::span/button[contains(@class, 'bootstrap-touchspin-down')]");

    private By searchButtonSelector = By.xpath("//button[@type='submit' and contains(text(), 'Search')]");

    public FlightsFormPage selectFromDestination(String destination) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(inputFromDestinationSelector, 0));
        WebElement input = driver.findElement(inputFromDestinationSelector);
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", input);

        By destinationCitySelector = By.xpath(fromDestination.replace("<city>", destination));

        driver.findElement(inputFromDestinationSelector).sendKeys(destination);
        wait.until(ExpectedConditions.visibilityOfElementLocated(destinationCitySelector));
        driver.findElement(destinationCitySelector).click();
        return new FlightsFormPage(driver);
    }

    public FlightsFormPage selectToDestination(String destination) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(inputToDestinationSelector, 0));
        WebElement input = driver.findElement(inputToDestinationSelector);
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", input);

        By destinationCitySelector = By.xpath(toDestination.replace("<city>", destination));

        driver.findElement(inputToDestinationSelector).sendKeys(destination);
        wait.until(ExpectedConditions.visibilityOfElementLocated(destinationCitySelector));
        driver.findElement(destinationCitySelector).click();
        return new FlightsFormPage(driver);
    }

    public FlightsFormPage chooseFlightClass(String flightClass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(flightClassDivSelector));
        driver.findElement(flightClassDivSelector).click();

        By flightClassSelector = By.xpath(flightClassOption.replace("<flightClass>", flightClass));
        wait.until(ExpectedConditions.visibilityOfElementLocated(flightClassSelector));
        driver.findElement(flightClassSelector).click();
        return new FlightsFormPage(driver);
    }

    public FlightsFormPage enterDepartDate(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(departInputSelector));
        driver.findElement(departInputSelector).click();

        return new FlightsFormPage(driver);
    }

    public FlightsFormPage selectNumberOfAdults(int numberOfAdults) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(adultInputSelector));
        int currentNumberOfAdults = getCurrentNumberOfAdults();
        if (currentNumberOfAdults == numberOfAdults)
            return new FlightsFormPage(driver);
        else if (currentNumberOfAdults < numberOfAdults) {
            for(int i = 0; i < numberOfAdults-currentNumberOfAdults; i++)
                driver.findElement(addAdultSelector).click();
            return new FlightsFormPage(driver);
        } else {
            for(int i = 0; i < currentNumberOfAdults - numberOfAdults; i++)
                driver.findElement(removeAdultSelector).click();
            return new FlightsFormPage(driver);
        }
    }
    public HotelsSearchResultPage clickSearchButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButtonSelector));
        driver.findElement(searchButtonSelector).click();
        return new HotelsSearchResultPage(driver);
    }


    public String getFlightClassText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(flightClassDivSelector));
        return driver.findElement(flightClassDivSelector).getText();
    }

    private Integer getCurrentNumberOfAdults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(adultInputSelector));
        String currentNumberOfAdults = driver.findElement(adultInputSelector).getAttribute("value");
        return Integer.parseInt(currentNumberOfAdults);
    }

}
