package clearTrip.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import clearTrip.coreFramework.Utility;

public class HotelBookingPage {

	private WebDriver driver;

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@FindBy(className = "subTitle")
	private WebElement subTitleHotelsAd;

	@FindBy(xpath = "//h5[contains(text(),'Traveller rating')]")
	private WebElement nextPAgeTravellerRating;

	public HotelBookingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void clickHotelLink() {
		// TODO Auto-generated method stub
		hotelLink.click();

	}

	public void enterLocality(String city) {
		// TODO Auto-generated method stub
		localityTextBox.sendKeys(city);
		// wait for list of cities to be populated and then select first one

		Utility.waitUntilExpectedConditions(
				ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("ui-id-1"), By.tagName("li")),
				"check the city");
		List<WebElement> originOptions = (this.driver).findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(1).click();
	}

	public void selectTravellers(String travellers) {
		// TODO Auto-generated method stub
		new Select(travellerSelection).selectByVisibleText(travellers);

		subTitleHotelsAd.click();
		Utility.waitUntilExpectedConditions(ExpectedConditions.elementToBeClickable(searchButton),
				"search button still disabled after 20 seconds");

	}

	public void clickSearch() {
		// TODO Auto-generated method stub
		searchButton.click();
		Utility.waitUntilExpectedConditions(ExpectedConditions.visibilityOf(getNextPageTravellerRating()),
				"Can't find element in 20 seconds");
	}

	public WebElement getNextPageTravellerRating() {
		return nextPAgeTravellerRating;
	}

}