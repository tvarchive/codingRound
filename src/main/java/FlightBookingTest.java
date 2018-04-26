import com.BusinessFunctions.FlightsPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class FlightBookingTest extends FlightsPage {



	private String todayDate = ".//*[@id='ui-datepicker-div']/div[1]/table//a[@class='ui-state-default ui-state-highlight ui-state-active']";


	@Test
	public void testThatResultsAppearForAOneWayJourney() throws InterruptedException {
		waitForElementVisible(driver, By.id("OneWay"));
		clickOnObject(By.id("OneWay"));

		selectValueAjax("FromTag", "ui-id-1", "Bangalore");

		selectValueAjax("toTag", "ui-id-2", "Delhi");

		// select current date
		waitForElementVisible(driver, By.xpath(todayDate));
		clickOnObject(By.xpath(todayDate));

		//all fields filled in. Now click on search
		waitForElementVisible(driver, By.id("SearchBtn"));
		clickOnObject(By.id("SearchBtn"));

		waitForElementVisible(driver, By.className("searchSummary"));

		//verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

	}











}
