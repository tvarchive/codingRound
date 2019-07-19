import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest extends BaseTest {
	
	String city = "Indiranagar, Bangalore";
	String fromDate = "21/07/2019";
	String toDate = "23/07/2019";
	
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    @Test(dependsOnMethods = "launchWebsite")
    public void shouldBeAbleToSearchForHotels() {
    	PageFactory.initElements(this.driver, this);

        hotelLink.click();

        localityTextBox.sendKeys(city);
		waitUntilExpectedConditions(ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("ui-id-1"), By.tagName("li")), "check the city");
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();
        new Select(travellerSelection).selectByVisibleText("1 room, 1 adult");
        searchButton.click();
    }

}