import java.io.File;
import java.io.IOException;

import PageActions.FlightBooking;
import PageActions.HotelBooking;
import Utils.CommonFunctions;

import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver;
    @BeforeTest
   	public void setBrowser() {
   		CommonFunctions Utility = new CommonFunctions(driver);
   		Utility.setDriverPath();
   		ChromeOptions options= new ChromeOptions();
   		options.addArguments("--disable-notifications");
   		ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
   		ChromeDriverService srvc = builder
   				.usingDriverExecutable(new File("C:\\chromedriver.exe"))
   				.usingPort(63534).build();
   		
   		try {
   			srvc.start();
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
   		driver = new ChromeDriver(srvc,options);
   		driver.manage().deleteAllCookies();
   	}
    

    @Test
    public void shouldBeAbleToSearchForHotels() {
    	HotelBooking  hb=new HotelBooking(driver);
    	driver.get("https://www.cleartrip.com/");
    	hb.SearchHotels();

    }

}
