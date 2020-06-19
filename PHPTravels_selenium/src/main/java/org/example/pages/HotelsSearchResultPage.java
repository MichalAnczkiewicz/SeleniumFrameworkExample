package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HotelsSearchResultPage extends BasePage {
    public HotelsSearchResultPage(WebDriver driver) {
        super(driver);
    }

    private By headingTitleSelector = By.xpath("//h3[@class='heading-title']");
    private By parkPlazaSelector = By.xpath("//div[@id='listing']/ul[@id='LIST']/li[1]/*//a");
    private By rightSidePriceRangeSliderSelector = By.xpath("//span[@class='irs-slider to']");
    private String maxPriceRangeDestination = "//span[@style='left: <destination>']";
    private String starGrade = "//div[contains(@class, 'rating-item')]/*//label[@for='<stars>']";

    public String getHeadingTitleText() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(headingTitleSelector, 0));
        return driver.findElement(headingTitleSelector).getText();
    }

    public String getFirstHotelName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(parkPlazaSelector));
        return driver.findElement(parkPlazaSelector).getText();
    }

    public HotelsSearchResultPage adjustPriceRangeSlider(String priceDestination){
        WebElement priceRangeDestination = driver.findElement(By.xpath(maxPriceRangeDestination.replace("<destination>", priceDestination )));
        wait.until(ExpectedConditions.visibilityOfElementLocated(rightSidePriceRangeSliderSelector));
        actions = new Actions(driver);
        WebElement slider = driver.findElement(rightSidePriceRangeSliderSelector);
        actions.clickAndHold(slider).moveToElement(priceRangeDestination).build().perform();
        return new HotelsSearchResultPage(driver);
    }

    public HotelsSearchResultPage selectStarGrade(String stars){
        By starGradeSelector = By.xpath(starGrade.replace("<stars>", stars));
        wait.until(ExpectedConditions.visibilityOfElementLocated(starGradeSelector));
        driver.findElement(starGradeSelector).click();
        return new HotelsSearchResultPage(driver);
    }



}
