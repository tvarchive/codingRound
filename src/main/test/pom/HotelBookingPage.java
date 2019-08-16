package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import common.CommonMethods;

public class HotelBookingPage {
	
	WebDriver driver;
	CommonMethods cm;
	boolean flag;
	//WebDriverWait wait = new WebDriverWait(driver, 20000);

	public HotelBookingPage(WebDriver driver) {
		this.driver = driver;
		cm = new CommonMethods(driver);
	}

	@FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    
    public void hotelBooking() {
       
        hotelLink.click();
        localityTextBox.sendKeys("Indiranagar, Bangalore");
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();
}
}
