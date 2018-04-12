package tests;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FlightBookingTest {

    static WebDriver driver;

    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        setDriverPath();
        driver = new ChromeDriver();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        chooseToHaveAOneWayJourney();

        enterOriginAs("Bangalore");
        selectTheFirstAvailableAutoCompleteOptionForOrigin();

        enterDestinationAs("Delhi");
        selectTheFirstAvailableAutoCompleteOptionForDestination();

        enterDepartureDate();

        //all fields filled in. Now click on search
        searchForTheJourney();
        waitForSearchResultsToAppear();

        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

        //close the browser
        driver.quit();

    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
    }
    private void selectTheFirstAvailableAutoCompleteOptionForOrigin() {
        waitFor(2000);
        List<WebElement> optionsList = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        optionsList.get(0).click();

    }

    private void searchForTheJourney() {
        driver.findElement(By.id("SearchBtn")).click();
    }

    public void waitForSearchResultsToAppear() {
        waitFor(5000);
    }

    private void enterDepartureDate() {
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

    }

    private void enterDestinationAs(String destination) {
        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys(destination);
    }


    private void enterOriginAs(String origin) {
        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).click();
        waitFor(1000);
        driver.findElement(By.id("FromTag")).sendKeys(origin);
    }


    private void chooseToHaveAOneWayJourney() {
        driver.findElement(By.id("OneWay")).click();
    }


    private void selectTheFirstAvailableAutoCompleteOptionForDestination() {
        waitFor(2000);
        List<WebElement> optionsList = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        optionsList.get(0).click();
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
