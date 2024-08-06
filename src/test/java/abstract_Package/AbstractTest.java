package abstract_Package;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.google.common.util.concurrent.Uninterruptibles;

import util.Constants;
import util.config;
import vendorPortal.DashboardPage;
import vendorPortal.LoginPage;

@Listeners({listeners.TestListener.class})
public abstract class AbstractTest {
	
	
	protected WebDriver driver;
	
	@BeforeSuite
	public void setupConfig()
	{
		config.initialize();
	}
	@BeforeTest
	public void setDriver(ITestContext txt) throws Exception
	{
		if(Boolean.parseBoolean(config.get(Constants.GRID_ENABLED)))
//		if(Boolean.getBoolean("selenium.grid.enabled"))
			getRemoteDriver();
		else
			getLocalDriver();
		txt.setAttribute(Constants.DRIVER, driver);
	}
	
	public void getRemoteDriver() throws MalformedURLException
	{
		Capabilities capabilities = new ChromeOptions();
		 if(System.getProperty("browser").equalsIgnoreCase("firefox"))
			capabilities = new FirefoxOptions();
		
		String urlFormat = config.get(Constants.GRID_URL_FORMAT);
		String hubHost = config.get(Constants.GRID_HUB_HOST);
		String url = String.format(urlFormat, hubHost);
		
//		DesiredCapabilities dc = new DesiredCapabilities();
//		dc.setCapability(CapabilityType.BROWSER_NAME,System.getProperty("browser"));
//		driver = new RemoteWebDriver(new URL("http://localhost:4444"),dc);
		driver = new RemoteWebDriver(new URL(url),capabilities);
		
	}
	
	public void getLocalDriver()
	{
		if(System.getProperty("browser").equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if(System.getProperty("browser").equalsIgnoreCase("chrome"))
			driver = new FirefoxDriver();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	@AfterMethod
	public void sleep()
	{
		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
	}


}
