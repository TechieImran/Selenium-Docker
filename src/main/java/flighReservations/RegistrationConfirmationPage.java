package flighReservations;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {
	WebDriver driver;
	@FindBy(id="go-to-flights-search")
	private WebElement goTo_Flights_Search_Btn;
	
	public RegistrationConfirmationPage(WebDriver driver)
	{
		
		super(driver);
	}
	
	public void goToFlightsSearch()
	{
		goTo_Flights_Search_Btn.click();
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(goTo_Flights_Search_Btn));
		return goTo_Flights_Search_Btn.isDisplayed();
	}

}
