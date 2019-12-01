package codingRound.cleartrip;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

import codingRound.commonLib.CommonFunctionsLib;
import codingRound.utility.Configurations;

public class HotelBooking {

	   @FindBy(linkText = "Hotels")
	    private WebElement hotelLink;

	    @FindBy(id = "Tags")
	    private WebElement localityTextBox;

	    @FindBy(id = "SearchHotelsButton")
	    private WebElement searchButton;

	    @FindBy(id = "travellersOnhome")
	    private WebElement travellerSelection;
		public WebDriver driver;
		public ExtentTest logger; 
		
		public HotelBooking(WebDriver driver, ExtentTest logger) {
			this.driver = driver;
			this.logger = logger;
			PageFactory.initElements(driver, this);

		}
		public void hotelBooking(Configurations testConfig, CommonFunctionsLib common) {
		       hotelLink.click();
		       common.waitForPageLoaded(driver, logger);
		       localityTextBox.click();
		       common.sendingKeys(localityTextBox, "Indiranagar, Bangalore", driver);

		        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		        searchButton.click();

		}

}
