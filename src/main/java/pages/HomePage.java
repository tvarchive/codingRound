package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(linkText = "Flights")
    private WebElement flightsLink;

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    public HomePage(App app) {
        super(app);
        PageFactory.initElements(getDriver(), this);
    }

    @Override
    WebElement uniquePageIdentifier() {
        return flightsLink;
    }

    public void openHotelsSearch() {
        clickElement(hotelLink);
    }

}
