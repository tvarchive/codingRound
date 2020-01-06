package pagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage extends BasePage {
    private By searchSummary = By.xpath("//div[@class='searchSummary']");

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    WebElement uniquePageIdentifier() {
        return getElement(searchSummary);
    }

    public boolean isSearchSummaryPresent() {
        return isElementPresent(searchSummary);
    }
}
