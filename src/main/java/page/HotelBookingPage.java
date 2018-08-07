package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HotelBookingPage extends base {

    WebDriver driver = new ChromeDriver();

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @FindBy(xpath = "//ul[@class='autoComplete']/li[2]")
    private WebElement autoCompleteText;

    @FindBy(xpath = "//div[@class='monthBlock first']//tr[3]/td[3]")
    private WebElement checkInDate;

    @FindBy(xpath = "//div[@class='monthBlock last']//tr[3]/td[3]")
    private WebElement checkOutDate;

    public HotelBookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickHotelLink() {
        waitForVisible(hotelLink);
        hotelLink.click();
    }

    public void enterLocalitySearchText(String text) {
        localityTextBox.sendKeys(text);
        waitForVisible(autoCompleteText);
        autoCompleteText.click();
    }

    public void clickCheckInDate() {
        waitForVisible(checkInDate);
        checkInDate.click();
    }

    public void clickCheckOutDate() {
        waitForVisible(checkOutDate);
        checkOutDate.click();
    }

    public void selectTravellers(String numbers) {
        new Select(travellerSelection).selectByVisibleText(numbers);
    }

    public void clickSearchButton() {
        waitForVisible(searchButton);
        searchButton.click();
    }
}
