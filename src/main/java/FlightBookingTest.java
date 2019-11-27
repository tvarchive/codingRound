import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends TestBase {



    @Test
    public void testThatResultsAppearForAOneWayJourney() {


        driver.get("https://www.cleartrip.com/");

        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();

        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

       // explicitWait(By.xpath("//li[@class='list']"));

        explicitWait(By.xpath("//ul[@id='ui-id-1']"));

        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));

        originOptions.get(0).click();

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        explicitWait(By.xpath("//*[@id=\"ui-id-2\"]"));

        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));


        destinationOptions.get(0).click();
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"DepartDate\"]"));

        element1.sendKeys("29/11/2019");



        driver.findElement(By.id("SearchBtn")).click();

        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

        //close the browser
        driver.quit();

    }


    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
