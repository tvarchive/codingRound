package pagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultsPage extends BasePage {

    @FindBy(id = "modifySearchLink")
    private WebElement modifySearchButton;

    @FindBy(xpath = "//nav[@class='hotelsList']/ul/li//ul//a")
    private List<WebElement> hotelNames;

    @FindBy(xpath = "//nav[@class='listViewNav']/ul/li/table//small")
    private List<WebElement> airlineNames;

    private By searchSummary = By.xpath("//div[@class='searchSummary']");

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    WebElement uniquePageIdentifier() {
        return modifySearchButton;
    }

    public boolean isSearchSummaryPresent() {
        return isElementPresent(searchSummary);
    }

    public boolean areHotelResultsDisplayed() {
        for (WebElement element : hotelNames) {
            if (element.getText() == null) {
                return false;
            }
        }
        return true;
    }

    public boolean areFlightsResultsDisplayed() {
        for (WebElement element : airlineNames) {
            if (element == null) {
                return false;
            }
        }
        return true;
    }
}
