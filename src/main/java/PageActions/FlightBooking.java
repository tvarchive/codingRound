package PageActions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utils.CommonFunctions;

public class FlightBooking extends CommonFunctions{
	WebDriver driver=null;
	private String optOneway = "xpath|//input[@id='OneWay']";
	private String txtFrom = "xpath|//input[@id='FromTag']";
	private String txtTo = "xpath|//input[@id='ToTag']";
	private String departDate="xpath|//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a";
	private String btnSearch="id|SearchBtn";
	private String searchResult="id|searchSummary";
	public FlightBooking(WebDriver driver2) {
		super(driver2);
		driver = driver2;
	}
	
	public boolean searchItinarary() {
		boolean value = false;
		try{
		CommonFunctions f1= new CommonFunctions(driver);
			
			f1.click(optOneway);
			f1.input(txtFrom, "Bangalore");
			f1.waitFor(20);
			 List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		        originOptions.get(0).click();
		        driver.switchTo().defaultContent();
		        f1.input(txtTo, "Delhi");
		        f1.waitFor(20);
		        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		        destinationOptions.get(0).click();
		        driver.switchTo().defaultContent();
		     f1.click(departDate);
		     f1.click(btnSearch);
		     Assert.assertTrue(f1.isElementPresent(By.className("searchSummary")));
		     value=true;
		 	return value;
		}
		catch(Exception e){
			return value;
		}
	}
	
}
