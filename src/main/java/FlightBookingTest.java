import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightBookingTest {

    WebDriver driver ;
    FlightBookingTestElements fbte ;
    WebDriverWait wait;
    String srcCity = "Bangalore";
    String destCity = "Delhi";
    String day = "27";
    String month = "October";
    String year = "2020";
    
    @BeforeTest
    public void beforeTest() {
    	setDriverPath();
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--disable-notifications");
    	driver = new ChromeDriver(options);
    	fbte = new FlightBookingTestElements(driver);
    	wait = new WebDriverWait(driver, 10);
    	
    }
    @Test
    public void testThatResultsAppearForAOneWayJourney() throws ParseException {

//        setDriverPath();
    	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://www.cleartrip.com/");
        driver.manage().window().fullscreen();
//        waitFor(2000);
        fbte.oneWayElement().click();

        fbte.srcElement().clear();
        fbte.srcElement().sendKeys(srcCity);
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(fbte.sourceOptions, fbte.liTag));
        

//        waitFor(5000);
        List<WebElement> originOptions = fbte.srcOptions();
        originOptions.get(0).click();
        
        waitFor(1000);
        
        fbte.destElement().clear();
        fbte.destElement().sendKeys(destCity);

        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(fbte.destOptions, fbte.liTag));
        //wait for the auto complete options to appear for the destination

        //waitFor(5000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = fbte.destOptions();
        destinationOptions.get(0).click();

//        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[4]/td[7]/a")).click();
        fbte.selectDate(year, month, day);
        //all fields filled in. Now click on search
        System.out.println(driver.findElement(fbte.calendar).getText());
        if(fbte.searchElement().isEnabled() && fbte.srcElement().getText()!=null && fbte.destElement()!=null && driver.findElement(fbte.calendar).getText()!=null )
        fbte.searchElement().click();

//        waitFor(5000);
        //verify that result appears for the provided journey search
        
        Assert.assertTrue(isElementPresent(fbte.seachSummary));
    }

    @AfterTest
    public void afterTest()
    {
    	driver.close();
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
        	wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
}
