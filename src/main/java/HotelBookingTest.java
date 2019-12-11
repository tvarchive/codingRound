import com.codoingRoiund.utils.WaitForElement;
import com.codoingRoiund.utils.pageRepo;
import com.codoingRound.BasePage.BaseObject;
import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest extends BaseObject {
	pageRepo pr = new pageRepo();
	WaitForElement wait = new WaitForElement();
    
	@Test
    public void shouldBeAbleToSearchForHotels() {
		
		wait.elementToAppear("//li[contains(@class,'hotelApp')]//a[contains(text(),'Hotels')]", 20);
		pr.hotels().click();

        pr.hotelSearch().sendKeys("Indiranagar, Bangalore");

        new Select(pr.travellers()).selectByVisibleText("1 room, 2 adults");
        pr.searchHotels().click();

    }
}
