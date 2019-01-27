package testPackage.hotelBooking;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class HotelBookingPageObjects {

	WebDriver driver;

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;
	
	@FindBy(className="list")
	List<WebElement> list;
	
	@FindBy (xpath="//*[@id=\"ui-id-1\"]/li[2]")
	WebElement autoCompleteOption;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;
	
	@FindBy(className="summary")
	WebElement summary;
	
	
	public HotelBookingPageObjects(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	public void clickHotelLink() {
		hotelLink.click();
	}
	
	public void serachHotelByLocality(String localityName) {
		localityTextBox.sendKeys(localityName);
		autoCompleteOption.click();
	}
	
	public void selectTraveller(String traveller) {
		  new Select(travellerSelection).selectByVisibleText(traveller);
	}
	
	public void clickSearchBtn() {
	
		searchButton.click();
	}
	
	public void checkSearchSummaryAppreard() {
		Assert.assertTrue(isElementPresent(summary));
	}
	
	private boolean isElementPresent(WebElement searchSummary) {
        try {
        	searchSummary.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
