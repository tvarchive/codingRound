import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelBookingTest extends BaseTest {
	
	private String city = "Indiranagar, Bangalore";
	private String travellers = "1 room, 1 adult";
	
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    @FindBy(className = "subTitle")
    private WebElement subTitleHotelsAd;
    
    @Test
    public void shouldBeAbleToSearchForHotels() throws InterruptedException {
		//the driver config and launch has been taken care of in BaseTest class 

    	//initialization of page factory method was not done
    	PageFactory.initElements(this.driver, this);

        hotelLink.click();

        localityTextBox.sendKeys(city);
        
        //wait for list of cities to be populated and then select first one 
		waitUntilExpectedConditions(ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("ui-id-1"), By.tagName("li")), "check the city");
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(1).click();
        new Select(travellerSelection).selectByVisibleText(travellers);

        subTitleHotelsAd.click();
        waitUntilExpectedConditions(ExpectedConditions.elementToBeClickable(searchButton), "search button still disabled after 20 seconds");

        searchButton.click();
        
        //there should be a check on whether we were able to search for hotels or not
        Assert.assertTrue((driver.getCurrentUrl().contains("results")));
        
    }
	//unused methods removed 

}