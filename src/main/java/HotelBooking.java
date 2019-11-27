import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelBooking {


    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(xpath = "//input[@id='SearchHotelsButton']")
    private WebElement searchButton;


    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @FindBy(xpath = "//input[@id='CheckInDate']")
    private WebElement checkInDate;

    @FindBy(xpath = "//div[contains(@class,'monthBlock first')]//a[contains(@class,'ui-state-default')][contains(text(),'28')]")
    private WebElement date;


    @FindBy(xpath = "//tr[1]//td[7]//a[1]")
    private WebElement checkOutDate1;

    @FindBy(xpath = "//input[@id='CheckOutDate']")
    private WebElement checkOutDate;


    public HotelBooking(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void click() {
        hotelLink.click();
    }

    public void setLocality(String locality) {
        localityTextBox.sendKeys(locality);
    }

    public void selectCheckInAndCheckoutDate() {
        checkInDate.click();
        date.click();
        checkOutDate.click();
        checkOutDate1.click();
    }

    public void searchHotelclick() {

        searchButton.click();

    }


}
