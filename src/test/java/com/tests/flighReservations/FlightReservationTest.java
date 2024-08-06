package com.tests.flighReservations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tests.vendorPortal.VendorPortalTestData;

import abstract_Package.AbstractTest;
import flighReservations.FlightConformationPage;
import flighReservations.FlightSearchPage;
import flighReservations.FlightSelectionPage;
import flighReservations.RegistrationConfirmationPage;
import flighReservations.RegistrationPage;
import util.Constants;
import util.JsonUtil;
import util.config;

public class FlightReservationTest extends AbstractTest{
	
	FlightReservationTestData testData;
	@Parameters({"testDataPath"})
	@BeforeTest()
	public void setParameters(String testDataPath)
	{
		testData = JsonUtil.getTestData(testDataPath,FlightReservationTestData.class);
	}
	
	@Test
	public void userRegistrationTest()
	{
		RegistrationPage registartionPage = new RegistrationPage(driver);
		registartionPage.goTo(config.get(Constants.FLIGHT_RESERVATION_URL));
		Assert.assertTrue(registartionPage.isAt());
		
		registartionPage.enterUserDetails(testData.getFirstName(),testData.getLastName());
		registartionPage.enterUserCredentials(testData.getEmail(),testData.getPassword());
		registartionPage.enterAddress(testData.getStreet(),testData.getCity(), testData.getZip());
		registartionPage.register();
	}
	
	@Test(dependsOnMethods = "userRegistrationTest")
	public void registrationConfirmationTest()
	{
		RegistrationConfirmationPage registartionConfirmPage = new RegistrationConfirmationPage(driver);
		Assert.assertTrue(registartionConfirmPage.isAt());     	
		registartionConfirmPage.goToFlightsSearch();
	}
	
	@Test(dependsOnMethods = "registrationConfirmationTest")
	public void flightSearchTest()
	{
		FlightSearchPage fsp = new FlightSearchPage(driver);
		Assert.assertTrue(fsp.isAt());
		fsp.passengersSelect(testData.getPassengersCount());
//		fsp.serviceSelect();
		fsp.searchFlights();
	}
	
	@Test(dependsOnMethods = "flightSearchTest")
	public void flightSelectionTest()
	{
		FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
		Assert.assertTrue(flightSelectionPage.isAt());
		flightSelectionPage.departureFlight_Select();
		flightSelectionPage.confirmFlights();
	}
	
	@Test(dependsOnMethods = "flightSelectionTest")
	public void flightConfirmationTest()
	{
		FlightConformationPage fcp = new FlightConformationPage(driver);
		Assert.assertTrue(fcp.isAt());
		Assert.assertEquals(fcp.getPrice(),testData.getExpectedPrice());
		
		
	}
}
