import com.sun.javafx.PlatformUtil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver;
    
    @BeforeTest
	public void driverInitialization() {
	setDriverPath();
	driver = new ChromeDriver();
	}
    
    @FindBy(xpath = "//*[@class='hotelApp ']/a")
    public WebElement hotelLink;
    
    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[4]")
    public WebElement checkInDate;
    
    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[6]")
    public WebElement checkOutDate;
    
    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        
    	setDriverPath();
    	PageFactory.initElements(driver, this);
        
    	driver.get("https://www.cleartrip.com/");
        hotelLink.click();

        waitFor(2000);
        localityTextBox.sendKeys("Indiranagar, Bangalore");

        //wait for the auto complete options to appear for the destination
        waitFor(5000);
        
        //select the first item from the destination auto complete list
        List<WebElement> localityOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("a"));
        localityOptions.get(0).click();
        
        
        driver.findElement(By.id("CheckInDate")).click();
        checkInDate.click();
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        
        driver.findElement(By.id("CheckOutDate")).click();
        checkOutDate.click();
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");

        waitFor(2000);
        searchButton.click();

    }
    
    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
    
    @AfterTest
    public void quit() {
    	//close the browser
        driver.quit();
    }

}
