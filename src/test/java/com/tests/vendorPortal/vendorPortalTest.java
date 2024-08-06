package com.tests.vendorPortal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import abstract_Package.AbstractTest;
import util.Constants;
import util.JsonUtil;
import util.config;
import vendorPortal.DashboardPage;
import vendorPortal.LoginPage;

public class vendorPortalTest extends AbstractTest {
	private VendorPortalTestData testData;
	private LoginPage loginpage;
	private DashboardPage dp;
	
	@Parameters({"testDataPath"})
	@BeforeTest
	public void intializePages(String testDataPath)
	{
		loginpage = new LoginPage(driver);
		dp = new DashboardPage(driver);
		testData = JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
	}
	
	@Test
	public void loginTest()
	{
		driver.get(config.get(Constants.VENDOR_PORTAL_URL));
		Assert.assertTrue(loginpage.isAt());
		loginpage.login(testData.getUserName(),testData.getPassword());
		
	}
	
	@Test(dependsOnMethods = "loginTest")
	public void dashBoardTest()
	{
		Assert.assertTrue(dp.isAt());
		//finance metrics validation
		Assert.assertEquals(dp.getMonthlyEarnings(), testData.getMonthlyEarning());
		Assert.assertEquals(dp.getAnnualEarnings(), testData.getAnnualEarning());
		Assert.assertEquals(dp.getProfitMargin(), testData.getProfitMargin());
		Assert.assertEquals(dp.getAvailableInventory(),testData.getAvailableInventory());
		
		//order history search
		dp.search(testData.getSearchKeyword());
		Assert.assertEquals(dp.getsearchResultCount(),testData.getSearchResultsCount());	
	}
	
	@Test(dependsOnMethods="dashBoardTest")
	public void logout() throws Exception
	{
		dp.logout();
		Assert.assertTrue(loginpage.isAt());
		
	}

}
