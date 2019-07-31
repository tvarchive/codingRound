import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class FlightBookingTest {

    WebDriver driver;
    WebDriverManager webDriverManager;
    
    @BeforeTest
	public void setup()
	{
		webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
	}
    
    @Test
    public void testThatResultsAppearForAOneWayJourney() {
    	        
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        driver.manage().window().maximize();
        driver.findElement(By.id("OneWay")).click();
        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");
        //wait for the auto complete options to appear for the origin
        waitFor(3000);        
        driver.findElement(By.xpath("//*[@id='ui-id-1']//*[@class='list']//child::a")).click();      

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");
        //wait for the auto complete options to appear for the destination
        waitFor(2000);        
        driver.findElement(By.xpath("//*[@id='ui-id-2']//*[@class='list']//child::a")).click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[3]/td[4]/a")).click();
        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();
        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

        

    }
    
    @AfterTest
	public void tearDown()
	{
		
    	webDriverManager.closeDriver();
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
