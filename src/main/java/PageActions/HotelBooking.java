package PageActions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Utils.CommonFunctions;

public class HotelBooking extends CommonFunctions {
	WebDriver driver=null;
	private String Hotelslink = "linkText|Hotels";
	private String localityTextBox = "id|Tags";
	private String searchButton = "id|SearchHotelsButton";
	private String travellerSelection="id|travellersOnhome";
	private String btnSearch="id|SearchHotelsButton";
	private String chkinDate="xpath|//input[@id='CheckInDate']";
	private String selchkinDate="xpath|//a[contains(@class,'ui-state-default ui-state-highlight ui-state-active')]";
	private String chkoutDate="xpath|//input[@id='CheckOutDate']";
	private String selchkoutDate="xpath|//a[contains(@class,'ui-state-default ui-state-active')]";
	private String searchResult="id|searchSummary";
	public HotelBooking(WebDriver driver2) {
		super(driver2);
		driver = driver2;
	}
	
	public boolean SearchHotels(){
		Boolean value=false;
		try{
			
		CommonFunctions f1=new CommonFunctions(driver);
		f1.click(Hotelslink);
		f1.input(localityTextBox, "Indiranagar, Bangalore");
		f1.waitFor(10);
		 List<WebElement> locationOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		 locationOptions.get(0).click();
		 f1.waitFor(10);
		driver.switchTo().defaultContent();
		
		f1.click(chkinDate);
		f1.click(selchkinDate);
		driver.switchTo().defaultContent();
		
		f1.click(chkoutDate);
		f1.click(selchkoutDate);
		driver.switchTo().defaultContent();
		
		f1.SelectListOption(travellerSelection, "1 room, 2 adults");
		driver.switchTo().defaultContent();
		
		f1.click(btnSearch);
		Assert.assertTrue(f1.isElementPresent(By.className("searchSummary")));
	     value=true;
	 	return value;
		}
		catch (Exception e){
			return value;
		}
	}
}
