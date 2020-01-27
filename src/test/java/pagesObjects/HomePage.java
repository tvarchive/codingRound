package pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[3]/td[3]/a")
//    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[3]/td[7]/a")
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

    public HomePage(WebDriver driver) {
        super(driver);
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
        waitUtils.waitForElementVisibility(signInForm, 10, driver);
    }

    public void openUserAccountMenu() {
        userAccountMenu.click();
    }

    public ResultsPage searchFlights() {
        searchButton.click();
        return createPageAndWaitForDisplay(driver, ResultsPage.class);
    }

    public void selectRandomDepartureDate() {
        datePickerText.click();
    }

    public HotelsPage openHotelsSearch() {
        hotelLink.click();
        return createPageAndWaitForDisplay(driver, HotelsPage.class);
    }

    public void selectTripType(HomePage.TripType tripType) {
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
        selectFirstAutoCompleteOption(originOptions, driver);
    }

    public void enterDestination(String destination) {
        toTextBox.clear();
        toTextBox.sendKeys(destination);
        selectFirstAutoCompleteOption(destinationOptions, driver);
    }


}
