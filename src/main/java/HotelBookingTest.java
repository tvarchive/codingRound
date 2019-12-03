import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import pom.HomePageElements;
import pom.HotelsPOM;

public class HotelBookingTest extends BaseTest {

    @Test
    public void shouldBeAbleToSearchForHotels() {

        driver.get(BASE_URL);

        actions.clickElement(HomePageElements.hotels_link_text());

        actions.setTextToInputfield(HotelsPOM.location_inputfield(), propObj.getProperty("HOTEL_INPUT_TEXT"));
        oUtility.waitForElementToBeVisible(HotelsPOM.first_search_result());
        actions.clickElement(HotelsPOM.first_search_result());

        actions.clickElement(HotelsPOM.start_date());
        oUtility.waitForElementToBeVisible(HotelsPOM.end_date());
        actions.clickElement(HotelsPOM.end_date());
        actions.selectValueByTextInDropdown(HotelsPOM.travellers_section(), "1 room, 2 adults");

        actions.clickElement(HotelsPOM.search_button());

    }

}
