package org.example.tests.hotels;

import org.example.pages.FlightsFormPage;
import org.example.pages.HotelsFormPage;
import org.example.pages.HotelsSearchResultPage;
import org.example.pages.MainPage;
import org.example.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelsTests extends BaseTest {

    private String option = "hotels";
    private String expectedSearchDivText = "Search By Hotel Or City Name";
    private String city = "Wroclaw";
    private String checkInDate = "26-07-2020";
    private String checkOutDate = "02-08-2020";
    private String priceRange = "20%";
    private String starGrade = "3";
    private String expectedHeadingTitle = "Hotels Wroclaw";
    private String expectedFirstHotelName = "Hotel HP Park Plaza Wroclaw";
    private String expectedHotelNameAfterPriceRangeAdjust = "Park Hotel Diament Wroclaw";
    private String expectedHotelNameAfterStarGradeAdjust = "Hotel Polonia";
    private Integer numberOfChildren = 3;
    private Integer numberOfAdults = 4;
    private MainPage mainPage;
    private HotelsFormPage hotelsFormPage;
    private HotelsSearchResultPage hotelsSearchResultPage;

    @Test
    public void shouldCorrectlyOpenHotelsTab() {
        mainPage = new MainPage(driver);
        mainPage.selectNavigationOption(option);
        hotelsFormPage = new HotelsFormPage(driver);
        String actualSearchDivText = hotelsFormPage.getTextFromInput();
        Assert.assertEquals(actualSearchDivText, expectedSearchDivText, "Wrong text!");
    }

    @Test(priority = 1)
    public void shouldCorrectlySearchForHotelInSpecifiedCity() {
        hotelsFormPage = new HotelsFormPage(driver);
        hotelsFormPage.selectDestination(city).enterCheckInDate(checkInDate)
                .enterCheckOutDate(checkOutDate).selectNumberOfChildren(numberOfChildren)
                .selectNumberOfAdults(numberOfAdults).clickSearchButton();
        hotelsSearchResultPage = new HotelsSearchResultPage(driver);
        String actualHeadingTitle = hotelsSearchResultPage.getHeadingTitleText();
        Assert.assertEquals(actualHeadingTitle, expectedHeadingTitle, "Wrong title!");
    }

    @Test(priority = 2)
    public void shouldCorrectlyDisplayHotelParkPlazaAtFirstPlace() {
        hotelsSearchResultPage = new HotelsSearchResultPage(driver);
        String actualFirstHotelName = hotelsSearchResultPage.getFirstHotelName();
        Assert.assertEquals(actualFirstHotelName, expectedFirstHotelName, "Wrong hotel name!");
    }

    @Test(priority = 3)
    public void shouldCorrectlyDisplayHotelsAfterPriceRangeChange() {
        hotelsSearchResultPage = new HotelsSearchResultPage(driver);
        hotelsSearchResultPage.adjustPriceRangeSlider(priceRange);
        String actualHotelName = hotelsSearchResultPage.getFirstHotelName();
        Assert.assertEquals(actualHotelName, expectedHotelNameAfterPriceRangeAdjust, "Wrong hotel name!");
    }

    @Test(priority = 4)
    public void shouldCorrectlyDisplayHotelAfterStarGradeChange() {
        hotelsSearchResultPage = new HotelsSearchResultPage(driver);
        hotelsSearchResultPage.selectStarGrade(starGrade);
        String actualHotelName = hotelsSearchResultPage.getFirstHotelName();
        Assert.assertEquals(actualHotelName, expectedHotelNameAfterStarGradeAdjust, "Wrong hotel name!");
    }


}
