import com.sun.javafx.PlatformUtil;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver;

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
  

    @Test
    public void shouldBeAbleToSearchForHotels() {
        setDriverPath();
        driver = new ChromeDriver();
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
        driver.quit();

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
    
    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
