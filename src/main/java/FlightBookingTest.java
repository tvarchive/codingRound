import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePageElements;
import pom.SearchSummaryPOM;

import java.util.List;

public class FlightBookingTest extends BaseTest {


    @Test
    public void testThatResultsAppearForAOneWayJourney() {


        driver.get(BASE_URL);

        oUtility.waitForElementToBeVisible(HomePageElements.one_way_radio_button());
        actions.clickElement(HomePageElements.one_way_radio_button());

        oUtility.waitForElementToBeVisible(HomePageElements.from_input_text_field());
        actions.setTextToInputfield(HomePageElements.from_input_text_field(), propObj.getProperty("STARTING_DESTINATION"));

        //wait for the auto complete options to appear for the origin
        oUtility.waitForElementToBeVisible(HomePageElements.from_search_result());
        actions.clickElement(HomePageElements.from_search_result());


        actions.setTextToInputfield(HomePageElements.to_input_text_field(), propObj.getProperty("END_DESTINATION"));

        //wait for the auto complete options to appear for the origin
        oUtility.waitForElementToBeVisible(HomePageElements.to_search_result());
        actions.clickElement(HomePageElements.to_search_result());

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        // remove the hard pause and introduced explicit wait

        //oUtility.waitFor(5000);
        oUtility.waitForElementToBeVisible(SearchSummaryPOM.search_summary());

        //verify that result appears for the provided journey search
        Assert.assertTrue(oUtility.isElementPresent(SearchSummaryPOM.search_summary()));


    }


}
