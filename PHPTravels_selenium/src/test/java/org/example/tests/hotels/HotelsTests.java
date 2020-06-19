package org.example.tests.hotels;

import org.example.pages.FlightsFormPage;
import org.example.pages.HotelsFormPage;
import org.example.pages.MainPage;
import org.example.utils.BaseTest;
import org.testng.annotations.Test;

public class HotelsTests extends BaseTest {

    private String option = "hotels";

    private MainPage mainPage;
    private HotelsFormPage hotelsFormPage;

    @Test
    public void shouldCorrectlyOpenHotelsTab(){
        mainPage = new MainPage(driver);
        mainPage.selectNavigationOption(option);
        hotelsFormPage = new HotelsFormPage(driver);
        hotelsFormPage.selectDestination("Wroclaw");
    }



}
