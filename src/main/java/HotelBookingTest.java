import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class HotelBookingPage {
    WebDriver driver;

    @FindBy(xpath = ".//a[contains(@title,'Find hotels')]")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    public void HotelBookingPage(WebDriver driver){
        this.driver = driver;
    }

    public void shouldBeAbleToSearchForHotels() {

        PageFactory.initElements(driver, this);
        hotelLink.click();

        PageFactory.initElements(driver, this);
        localityTextBox.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();
    }
}
