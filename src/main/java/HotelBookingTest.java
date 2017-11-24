import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {
   // static WebDriver driver;
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
     //   setDriverPath();
    //    driver=new ChromeDriver();

        driver.get("https://www.cleartrip.com/");
        clickOnElement(hotelLink);

        localityTextBox.sendKeys("Indiranagar, Bangalore");

        Select select=new Select(travellerSelections)
        select.selectByVisibleText("1 room, 2 adults");
        clickOnElement(searchButton);

        driver.quit();

    }

//     private void setDriverPath() {
//         if (PlatformUtil.isMac()) {
//             System.setProperty("webdriver.chrome.driver", "chromedriver");
//         }
//         if (PlatformUtil.isWindows()) {
//             System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//         }
//         if (PlatformUtil.isLinux()) {
//             System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
//         }
//     }

}
