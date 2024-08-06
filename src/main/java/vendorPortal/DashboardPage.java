package vendorPortal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flighReservations.AbstractPage;

public class DashboardPage extends AbstractPage{

	@FindBy(id="monthly-earning")
	private WebElement monthlyEarningElement;
	
	@FindBy(id="annual-earning")
	private WebElement annualEarningElement;
	
	@FindBy(id="profit-margin")
	private WebElement profitMarginElement;
	
	@FindBy(how=How.ID,using="available-inventory")
	private WebElement availableInventoryElement;
	
	@FindBy(how=How.CSS,using="#dataTable_filter input")
	private WebElement searchInput;
	
	@FindBy(id="dataTable_info")
	private WebElement searchResultCountElement;
	
	@FindBy(css="img.img-profile")
	private WebElement userProfilePictureElement;
	
	@FindBy(how=How.LINK_TEXT,using="Logout")
	private WebElement logoutElement;
	
	@FindBy(xpath="//div[@id='logoutModal']//a[text()='Logout']")
	private WebElement modalLogoutElement;
	
	Logger log = LoggerFactory.getLogger(DashboardPage.class);
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		
		wait.until(ExpectedConditions.visibilityOf(annualEarningElement));
		return annualEarningElement.isDisplayed();
	}
	
	public String getMonthlyEarnings()
	{
		return monthlyEarningElement.getText();
	}
	
	public String getAnnualEarnings()
	{
		return annualEarningElement.getText();
	}
	
	public String getProfitMargin()
	{
		return profitMarginElement.getText();
	}
	
	public String getAvailableInventory()
	{
		return availableInventoryElement.getText();
	}
	
	public void search(String searchKeyword)
	{
		searchInput.sendKeys(searchKeyword);
	}
	
	public int getsearchResultCount()
	{
		String text = searchResultCountElement.getText();
		String[] str = text.split(" ");
		int count = Integer.parseInt(str[5]);
		log.info("count is : {}",count);
		return Integer.parseInt(str[5]);
	}
	
	public void logout() throws Exception
	{
		userProfilePictureElement.click();
		logoutElement.click();
		wait.until(ExpectedConditions.visibilityOf(modalLogoutElement));
//		Thread.sleep(3000);
		modalLogoutElement.click();
	}

}
