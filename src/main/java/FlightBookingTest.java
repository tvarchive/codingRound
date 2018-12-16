import Utils.CommonFunctions;

import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import PageActions.FlightBooking;
public class FlightBookingTest {
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
    public void testThatResultsAppearForAOneWayJourney() {
    	FlightBooking  fb=new FlightBooking(driver);
    	driver.get("https://www.cleartrip.com/");
    	fb.searchItinarary();

    }
   }
