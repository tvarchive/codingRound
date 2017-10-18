import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest extends BaseTest {
    private By hotelLink = By.linkText("Hotels");
    private By localityTextBox = By.id("Tags");
    private By resultLst = By.cssSelector("ul[id='ui-id-1']>li[class='list']");
    private By searchButton = By.id("SearchHotelsButton");
    private By travellerSelection = By.id("travellersOnhome");
    private By default_startDate = By.cssSelector("td[data-handler='selectDay']");

    @Test
    public void shouldBeAbleToSearchForHotels() {
        PageFactory.initElements(driver, this);
        driver.get("https://www.cleartrip.com/");
        findElementWithTimeout(hotelLink).click();

        findElementWithTimeout(localityTextBox).sendKeys("Indiranagar, Bangalore");
        findElementWithTimeout(resultLst).click();
        findElementWithTimeout(default_startDate).click();
        waitFor(1000);
        findElementWithTimeout(default_startDate).click();
        new Select(findElementWithTimeout(travellerSelection)).selectByVisibleText("1 room, 2 adults");
        findElementWithTimeout(searchButton).click();
        driver.quit();
    }
}
