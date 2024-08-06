package flighReservations;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightSelectionPage extends AbstractPage{
	
	@FindBy(name="departure-flight")
	private List<WebElement> departureFlights;
	
	@FindBy(name="arrival-flight")
	private List<WebElement> arrivalFlights;
	
	@FindBy(how=How.ID,using="confirm-flights")
	private WebElement confirmFlight;

	public FlightSelectionPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(confirmFlight));
		return confirmFlight.isDisplayed();
	}
	
	public void departureFlight_Select()
	{
		int num =ThreadLocalRandom.current().nextInt(0, departureFlights.size());
		driver.manage().window().maximize();
		departureFlights.get(num).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", arrivalFlights.get(num));
		arrivalFlights.get(num).click();
	}
	
	public void confirmFlights()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", confirmFlight);
//		confirmFlight.click();
	}
	

}
