package pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HotelsPage extends BasePage {

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    public HotelsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    WebElement uniquePageIdentifier() {
        return localityTextBox;
    }

    public void enterLocality(String s) {
        localityTextBox.sendKeys(s);
    }

    public void selectTravellers(String s) {
        new Select(travellerSelection).selectByVisibleText(s);
    }

    public void searchHotels() {
        searchButton.click();
    }
}
