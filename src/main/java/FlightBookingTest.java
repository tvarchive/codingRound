import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends BaseTest {


    @Test
    public void testThatResultsAppearForAOneWayJourney() {


        driver.get(BASE_URL);
        oUtility.waitFor(2000);
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        oUtility.waitFor(2000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        driver.findElement(By.id("toTag")).clear();
        driver.findElement(By.id("toTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        oUtility.waitFor(2000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        oUtility.waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(oUtility.isElementPresent(By.className("searchSummary")));

        //close the browser
        driver.quit();

    }





}
