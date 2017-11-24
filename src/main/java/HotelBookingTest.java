import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver = new ChromeDriver();

    @FindBy(linkText = "Hotels")// @FindBy(Xpath="//span[contains(text(),'Hotels')]")
    private WebElement hotelLink;

    @FindBy(id = "Tags")// @FindBy(Xpath="//input[@id='Tags']")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")// @FindBy(Xpath="//input[@id='SearchHotelsButton']")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")// @FindBy(Xpath="//select[@id='travellersOnhome']")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");
     
        Select sel= new Select(travellerSelection);//Chanes made we have to Declare Select Class
         sel.selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        driver.quit();

    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");//Mention exact path for chrome Driver as in SigInTest class mentioned
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");//Mention exact path for chrome Driver
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");///Mention exact path for chrome Driver
        }
    }

}
