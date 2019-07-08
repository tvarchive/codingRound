
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class FlightBookingPage extends SetUp {

	By oneWay = By.id("OneWay");
	By fromField = By.id("FromTag");
	By toField = By.id("ToTag");
	By datePicker = By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a");
	By searchButton = By.id("SearchBtn");
	By searchSummary = By.className("searchSummary");
	
	WebDriver driver = null;
	WebDriverWait wait;

	public FlightBookingPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void selectOneWay() {
		driver.findElement(oneWay).click();
	}

	public void selectFromCity() {
		driver.findElement(fromField).clear();
		driver.findElement(fromField).sendKeys("Bangalore");
	}

	public void selectToCity() {
		driver.findElement(toField).clear();
		driver.findElement(toField).sendKeys("Delhi");
	}

	public void selectDatePicker() {
		driver.findElement(datePicker).click();
	}
	
	public void clickOnSearch() {
		driver.findElement(searchButton).click();
	}
	
	
	public void selectFirstDestination() {
	List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
	destinationOptions.get(0).click();
}
	
}
