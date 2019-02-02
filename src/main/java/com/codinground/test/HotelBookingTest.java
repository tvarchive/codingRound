package com.codinground.test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.codinground.utils.SeleniumUtils;

public class HotelBookingTest extends SeleniumUtils{

    //WebDriver driver = new ChromeDriver();
	@BeforeClass
	public void init() {
		loadBrowser();
	}
		
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {

        driver.get("https://www.cleartrip.com/");
        PageFactory.initElements(driver, this);
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();
    }

    @AfterClass
    public void afterClass() {
    	tearDown();
    }

}
