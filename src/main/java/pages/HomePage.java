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

    @FindBy(linkText = "Your trips")
    private WebElement userAccountMenu;

    @FindBy(id = "SignIn")
    private WebElement signInLink;

    @FindBy(id = "signinForm")
    private WebElement signInForm;

    @FindBy(id = "modal_window")
    private WebElement signInFormFrame;

    @FindBy(id = "signInButton")
    private WebElement signInButton;

    @FindBy(id = "errors1")
    private WebElement errorText;

    public enum TripType {
        ONE_WAY, ROUND_TRIP, MULTI_CITY;

    }

    public HomePage(App app) {
        super(app);
        PageFactory.initElements(getDriver(), this);
    }

    @Override
    WebElement uniquePageIdentifier() {
        return flightsLink;
    }

    public String getSignInErrorText() {
        return errorText.getText();
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public void openSignInForm() {
        signInLink.click();
        switchToFrame(signInFormFrame);
        waitForElementVisibility(signInForm, 2);
    }

    public void openUserAccountMenu() {
        userAccountMenu.click();
    }

    public void searchFlights() {
        searchButton.click();
    }

    public void selectRandomDepartureDate() {
        datePickerText.click();
    }

    public void openHotelsSearch() {
        hotelLink.click();
    }

    public void selectTripType(TripType tripType) {
        switch (tripType) {
            case ONE_WAY:
                oneWayRadioButton.click();
                break;
            case ROUND_TRIP:
                roundTripRadioButton.click();
                break;
            case MULTI_CITY:
                multiCityRadioButton.click();
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
        options.get(0).click();
    }
}
