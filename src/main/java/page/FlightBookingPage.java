package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FlightBookingPage extends base {

    @FindBy(id = "OneWay")
    private WebElement oneWayButton;

    @FindBy(id = "FromTag")
    private WebElement fromTextBox;

    @FindBy(id = "ToTag")
    private WebElement toTextBox;

    @FindBy(xpath = "//*[@id='ui-id-1']/li")
    private List<WebElement> originOptions;

    @FindBy(xpath = "//*[@id='ui-id-2']/li")
    private List<WebElement> destinationOptions;

    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")
    private WebElement date;

    @FindBy(id = "SearchBtn")
    private WebElement searchButton;

    @FindBy(className = "searchSummary")
    private WebElement searchSummary;

    public FlightBookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOneWayOption() {
        waitForVisible(oneWayButton);
        oneWayButton.click();
    }

    public void enterFromText(String text) {
        waitForVisible(fromTextBox);
        fromTextBox.clear();
        fromTextBox.sendKeys(text);
        selectOption(originOptions);
    }

    public void enterToText(String text) {
        waitForVisible(toTextBox);
        toTextBox.clear();
        toTextBox.sendKeys(text);
        selectOption(destinationOptions);
    }

    public void selectOption(List<WebElement> options) {
        waitForVisible(options.get(0));
        options.get(0).click();
    }

    public void selectDate() {
        waitForVisible(date);
        date.click();
    }

    public void clickSearchButton() {
        waitForVisible(searchButton);
        searchButton.click();
    }

    public Boolean isSearchSummaryPresent() {
        return searchSummary.isDisplayed();
    }
}
