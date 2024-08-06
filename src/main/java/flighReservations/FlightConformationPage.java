package flighReservations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConformationPage extends AbstractPage {
	
	public static Logger log = LoggerFactory.getLogger(FlightConformationPage.class);
	
	@FindBy(how=How.CSS,using="#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
	private WebElement flightConfirmationNo;
	
	@FindBy(how=How.CSS,using="#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
	private WebElement totalPrice;

	public FlightConformationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(flightConfirmationNo));
		
		return flightConfirmationNo.isDisplayed();
	}
	
	public String getPrice()
	{
		String confirmationText= flightConfirmationNo.getText();
		String price = totalPrice.getText();
		log.info("Flight confirmation : {}", confirmationText);
		log.info("Total price is : {}",price);
		return price;
	}

}
