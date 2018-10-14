package Pages;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingPage extends BasePage{

    public FlightBookingPage(WebDriver driver)
    {
        super(driver);
    }


    BasePage basePage=new BasePage(driver);
    public void load_FlightBookingPage()
    {
        basePage.loadPage("https://www.cleartrip.com/");
        basePage.waitForPageToLoad();
    }


    public void click_OneWay()
    {
        basePage.click(driver.findElement(By.id("OneWay")));
    }

    public void enter_source(String source)
    {
        basePage.enterValue( driver.findElement(By.id("FromTag")),source,true);
       basePage.waitForElemPresence(By.id("ui-id-1"));
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        basePage.click(originOptions.get(0));
    }

    public void enter_destination(String destination)
    {
        basePage.enterValue( driver.findElement(By.id("ToTag")),destination,true);
        basePage.waitForElemPresence(By.id("ui-id-2"));
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        basePage.click(originOptions.get(0));
    }

    public void click_date()
    {
        basePage.click( driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")));
    }

    public void click_search()
    {
        basePage.click( driver.findElement(By.id("SearchBtn")));
    }

    public void validate_isDisplayedSearchResults(){
        basePage.waitForPageToLoad();
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

    }
}
