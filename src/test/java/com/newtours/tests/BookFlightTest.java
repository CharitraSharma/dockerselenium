package com.newtours.tests;

import com.newtours.pages.*;
import com.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest extends BaseTest {

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupParameter(String noOfPassengers,String expectedPrice){
        this.noOfPassengers=noOfPassengers;
        this.expectedPrice=expectedPrice;
    }

    @Test
    public void registrationPageTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium","docker");
        registrationPage.enterUserCredentials("selenium","password");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightBookingPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightDetailsTest(){
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightDetailsTest")
    public void flightFlightTest(){
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.goToFlightConfirmationsPage();
    }

    @Test(dependsOnMethods = "flightFlightTest")
    public void flightConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualprice = flightConfirmationPage.getPrice();
        Assert.assertEquals(actualprice,expectedPrice);
    }

}
