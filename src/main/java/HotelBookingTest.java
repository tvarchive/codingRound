import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver = new ChromeDriver();

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        driver.get("https://www.cleartrip.com/");
        hotelLink.click();
        localityTextBox.sendKeys("Bangalore");

    }

}
