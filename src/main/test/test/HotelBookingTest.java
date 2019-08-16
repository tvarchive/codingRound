package test;
import com.sun.javafx.PlatformUtil;

import common.CommonMethods;
import common.DriverFactory;
import pom.FlightBookingPage;
import pom.HotelBookingPage;

import java.util.Properties;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HotelBookingTest {
	
	static WebDriver driver;
	HotelBookingPage hotelBookingPage;

	Properties prop = CommonMethods.prop;
	String userName = prop.getProperty("userName1");
	String password = prop.getProperty("password1");

	@BeforeClass
	public void setUp() {
		driver = DriverFactory.setUpDriver();
		hotelBookingPage = new HotelBookingPage(driver);
	}


    @Test
    public void shouldBeAbleToSearchForHotels() {
    	hotelBookingPage.hotelBooking();

    }

    @AfterClass
	public void closeBrowser(){
		driver.quit();
	}
   
}
