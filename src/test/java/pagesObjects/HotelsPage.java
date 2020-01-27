package pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HotelsPage extends BasePage {

    @FindBy(id = "Tags")
    private WebElement locationTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @FindBy(xpath = "//ul[@id='ui-id-1']/li/a")
    private List<WebElement> locationSuggestions;

    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[5]/a")
    private WebElement fromDate;

    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[3]/a")
    private WebElement toDate;

    public HotelsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    WebElement uniquePageIdentifier() {
        return locationTextBox;
    }

    public void enterLocality(String s) {
        locationTextBox.sendKeys(s);
        selectFirstAutoCompleteOption(locationSuggestions, driver);
    }

    public void enterDateDetails() {
        fromDate.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        toDate.click();
    }

    public void selectTravellers(String s) {
        new Select(travellerSelection).selectByVisibleText(s);
    }

    public ResultsPage searchHotels() {
        searchButton.click();
        return createPageAndWaitForDisplay(driver, ResultsPage.class);
    }
}
