package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeTest;

import Utils.DriverFactoy;

public class FlightBookingPageObject extends DriverFactoy {


	public void clickoneWay() {
		driver.findElement(By.id("OneWay")).click();

	}

	public void fillFromField(String fromCity) {
		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys(fromCity);
		
		waitFor(5000);
		// wait for the auto complete options to appear for the origin

		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();

	}

	public void fillToField(String toCity) {
		// TODO Auto-generated method stub
		driver.findElement(By.cssSelector("#ToTag")).clear();
		driver.findElement(By.cssSelector("#ToTag")).sendKeys(toCity);
		// wait for the auto complete options to appear for the destination

		waitFor(5000);
		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

	}

	public void clickOnSearch() {
		// TODO Auto-generated method stub
		driver.findElement(By.id("SearchBtn")).click();

	}

	public void pickDate() {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();
	}
}
