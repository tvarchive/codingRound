package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;

import Utils.DriverFactoy;

public class HotelBookingPageObject extends DriverFactoy {
	
	public void clickHotelLink() {
		driver.findElement(By.cssSelector("a[title='Find hotels in destinations around the world'] > span.productIcon.hotels")).click();
	}

	public void sendLocalityTextBox(String locality) {
		driver.findElement(By.id("Tags")).sendKeys(locality);
		
	}

	public void selectTravellerSection(String text) {
		WebElement tavellerSelection=driver.findElement(By.id("travellersOnhome"));
		tavellerSelection.click();
		new Select(tavellerSelection).selectByVisibleText(text);
	}

	public void clickonSearch() {
		// TODO Auto-generated method stub
		driver.findElement(By.id("SearchHotelsButton")).click();

	}
}
