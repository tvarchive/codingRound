package test;
import common.CommonMethods;
import common.DriverFactory;
import pom.FlightBookingPage;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Properties;

public class FlightBookingTest {
	
	
	static WebDriver driver;
	FlightBookingPage flightBookingPage;

	Properties prop = CommonMethods.prop;
	String userName = prop.getProperty("userName1");
	String password = prop.getProperty("password1");

	@BeforeClass
	public void setUp() {
		driver = DriverFactory.setUpDriver();
		flightBookingPage = new FlightBookingPage(driver);
	}


    @Test
    public void test1() {
    	
    	flightBookingPage.searchforFlights();
    	flightBookingPage.clickonSearchButton();
    	Assert.assertTrue(flightBookingPage.isSummaryPresent());
    }
    
    @AfterClass
	public void closeBrowser(){
		driver.quit();
	}
        
    
}
