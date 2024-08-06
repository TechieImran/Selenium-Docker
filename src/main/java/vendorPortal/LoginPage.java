package vendorPortal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import flighReservations.AbstractPage;

public class LoginPage extends AbstractPage {
	
	@FindBy(how=How.ID, using="username")
	private WebElement userNameInput;
	
	@FindBy(how=How.ID,using="password")
	private WebElement passwordInput;
	
	@FindBy(how=How.ID,using="login")
	private WebElement loginBtn;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(userNameInput));
		return userNameInput.isDisplayed();
	}
	
	public void goTo(String url)
	{
		driver.get(url);
	}
	public void login(String userName,String password)
	{
		userNameInput.sendKeys(userName);
		passwordInput.sendKeys(password);
		loginBtn.click();
	}

}
