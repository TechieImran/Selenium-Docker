package flighReservations;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends AbstractPage {
	
	@FindBy(id="firstName")
	private WebElement firstNameInput;
	
	@FindBy(id="lastName")
	private WebElement lastNameInput;
	
	@FindBy(id="email")
	private WebElement emailInput;
	
	@FindBy(id="password")
	private WebElement passwordInput;
	
	@FindBy(name="street")
	private WebElement streetInput;
	
	@FindBy(how=How.NAME, using="city")
	private WebElement cityInput;
	
	@FindBy(how=How.ID,using="inputState")
	private WebElement stateInput;
	
	@FindBy(how=How.NAME,using="zip")
	private WebElement zipcodeInput;
	
	@FindBy(xpath="//button[text()='Register']")
	private WebElement registerButton;
	
	public RegistrationPage(WebDriver driver)
	{
		super(driver);
//		PageFactory.initElements(driver, this);
	}
	
	public void goTo(String url)
	{
		this.driver.get(url);
		driver.manage().window().maximize();
		
	}
	
	public void enterUserDetails(String firstName,String lastName)
	{
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		
	}
	
	public void enterUserCredentials(String email,String password)
	{
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
	}
	
	public void enterAddress(String street,String city,String zip)
	{
		streetInput.sendKeys(street);
		cityInput.sendKeys(city);
		zipcodeInput.sendKeys(zip);
		
	}
	
	public void register()
	{
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView()", registerButton);
		registerButton.click();
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(firstNameInput));
		
		return firstNameInput.isDisplayed();
	}
	
}
