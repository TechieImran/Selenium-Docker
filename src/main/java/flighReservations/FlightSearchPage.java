package flighReservations;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {
	
	@FindBy(id="passengers")
	private WebElement passengersCountSelect;
	
	@FindBy(xpath="//input[@value=\"business\"]")
	private WebElement service_businessClass;
	
	@FindBy(id="search-flights")
	private WebElement searchFlightsBtn;

	public FlightSearchPage(WebDriver driver) {
		super(driver);
		
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(passengersCountSelect));
		return passengersCountSelect.isDisplayed();
	}
	
	public void passengersSelect(String passengerSelect)
	{
		Select select = new Select(passengersCountSelect);
		select.selectByValue(passengerSelect);
	}
	
	public void serviceSelect()
	{
		service_businessClass.click();
	}
	
	public void searchFlights()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", searchFlightsBtn);
//		searchFlightsBtn.click();
	}

}
