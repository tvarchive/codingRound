package testPackage.flightBooking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FlightBookingPageObjects {
	WebDriver driver;

	@FindBy(id = "OneWay")
	WebElement oneway;

	@FindBy(id = "FromTag")
	WebElement FromTag;

	@FindBy(id = "ToTag")
	WebElement toTag;

	@FindBy(className = "calendar")
	WebElement calendar;
	
	@FindBy(xpath="//*[@id=\"ORtrip\"]/section[2]/div[1]/dl/dd/div/a/i")
	WebElement calendarIcon;

	@FindBy(id = "SearchBtn")
	WebElement SearchBtn;

	@FindBy(className = "searchSummary")
	WebElement searchSummary;

	public FlightBookingPageObjects(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void clickOneWay() {
		oneway.click();
	}

	public void setFromTag(String fromCity) {
		FromTag.clear();
		FromTag.sendKeys(fromCity);
	}

	public void setToTag(String toCity) {
		toTag.clear();
		toTag.sendKeys(toCity);
	}

	public void setCurrentCalenderDay() {
		
		calendarIcon.click();
		List<WebElement> columns = calendar.findElements(By.tagName("td"));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
		LocalDate localDate = LocalDate.now();
		String currentDate = dtf.format(localDate);
		for (WebElement cell : columns) {
			if (cell.getText().equals(currentDate)) {
				cell.findElement(By.linkText(currentDate)).click();
				break;
			}
		}
	}
	
	public void clickSearchBtn() {
		SearchBtn.click();
	}
	
	public void checkSearchSummaryAppreard() {
		Assert.assertTrue(isElementPresent(searchSummary));
	}
	
	private boolean isElementPresent(WebElement searchSummary) {
        try {
        	searchSummary.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
