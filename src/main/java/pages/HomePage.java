package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(linkText = "Flights")
    private WebElement flightsLink;

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "OneWay")
    private WebElement oneWayRadioButton;

    @FindBy(id = "RoundTrip")
    private WebElement roundTripRadioButton;

    @FindBy(id = "MultiCity")
    private WebElement multiCityRadioButton;

    @FindBy(id = "FromTag")
    private WebElement fromTextBox;

    @FindBy(id = "ToTag")
    private WebElement toTextBox;

    @FindBy(id = "SearchBtn")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[@id='ui-id-1']/li/a")
    private List<WebElement> originOptions;

    @FindBy(xpath = "//ul[@id='ui-id-2']/li/a")
    private List<WebElement> destinationOptions;

    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")
    private WebElement datePickerText;

    public enum TripType {
        ONE_WAY, ROUND_TRIP, MULTI_CITY;
    }

    public HomePage(App app) {
        super(app);
        PageFactory.initElements(getDriver(), this);
    }

    public void searchFlights() {
        clickElement(searchButton);
    }

    public void selectRandomDepartureDate() {
        clickElement(datePickerText);
    }

    @Override
    WebElement uniquePageIdentifier() {
        return flightsLink;
    }

    public void openHotelsSearch() {
        clickElement(hotelLink);
    }

    public void selectTripType(TripType tripType) {
        switch (tripType) {
            case ONE_WAY:
                clickElement(oneWayRadioButton);
                break;
            case ROUND_TRIP:
                clickElement(roundTripRadioButton);
                break;
            case MULTI_CITY:
                clickElement(multiCityRadioButton);
                break;
        }
    }

    public void enterOrigin(String origin) {
        fromTextBox.clear();
        fromTextBox.sendKeys(origin);
        selectFirstAutoCompleteOption(originOptions);
    }

    public void enterDestination(String destination) {
        toTextBox.clear();
        toTextBox.sendKeys(destination);
        selectFirstAutoCompleteOption(destinationOptions);
    }

    private void selectFirstAutoCompleteOption(List<WebElement> options) {
        waitForElementsVisibility(options, 2);
        clickElement(options.get(0));
    }
}
