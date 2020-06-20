package org.example.tests.flights;

import org.example.pages.FlightsFormPage;
import org.example.pages.MainPage;
import org.example.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightsTest extends BaseTest {

    private String option = "flights";
    private String expectedFlightClassText = "Economy";
    private String flightClass = "Business";
    private String fromDestination = "Warsaw";
    private String toDestination = "LAX";
    private Integer numberOfAdults = 2;
    private MainPage mainPage;
    private FlightsFormPage flightsFormPage;

    @Test
    public void shouldCorrectlyOpenFlightsTab() {
        mainPage = new MainPage(driver);
        mainPage.selectNavigationOption(option);
        flightsFormPage = new FlightsFormPage(driver);
        String actualFlightClassText = flightsFormPage.getFlightClassText();
        Assert.assertEquals(actualFlightClassText, expectedFlightClassText, "Wrong text!");
    }

    @Test(priority = 1)
    public void shouldCorrectlySearchForFlightWithSpecifiedCities() {
        flightsFormPage = new FlightsFormPage(driver);
        flightsFormPage.chooseFlightClass(flightClass)
                .selectFromDestination(fromDestination)
                .selectToDestination(toDestination)
                .selectNumberOfAdults(numberOfAdults)
                .clickSearchButton();
    }

}
