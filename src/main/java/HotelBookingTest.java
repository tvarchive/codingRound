import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver;
    WebDriverManager webDriverManager;
    
    
    
    @FindBy(xpath = "//*[@id='Home']/div/aside[1]/nav/ul[1]/li[2]/a[1]")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    @FindBy(xpath = "//*[@id='ui-id-1']//*[@class='list']//child::a")
    private List<WebElement> locations;
  
    
    @BeforeTest
	public void setup()
	{
		webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
	}

    @Test
    public void shouldBeAbleToSearchForHotels() {
        
        PageFactory.initElements(driver, this);
        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        hotelLink.click();
        waitFor(5000);
        localityTextBox.sendKeys("Indiranagar, Bangalore");
        waitFor(5000);
        locations.get(0).click();
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");        
        searchButton.click();
        

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

}
