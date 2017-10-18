import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FlightBookingTest extends BaseTest {


    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        driver.get("https://www.cleartrip.com/");

        selectOriginDestination(findElementWithTimeout(By.id("FromTag")), "Bangalore", 1);
        selectOriginDestination(findElementWithTimeout(By.id("ToTag")), "Delhi", 2);
        //Selection of default date
        findElementWithTimeout(By.cssSelector("td[data-handler='selectDay']")).click();
        findElementWithTimeout(By.id("SearchBtn")).click();
        assertThat(findElementWithTimeout(By.className("searchSummary")).isDisplayed(), is(true));
        //close the browser
        driver.quit();

    }

    private boolean isElementPresent(By by) {
        try {
            findElementWithTimeout(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void selectOriginDestination(WebElement elm, String cityName, Integer listType) {
        elm.click();
        elm.sendKeys(cityName);
        waitFor(1000);
        findElementWithTimeout(By.cssSelector("ul[id='ui-id-" + listType + "']>li[class='list']")).click();
        waitFor(500);
    }
}
