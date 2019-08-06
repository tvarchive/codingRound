import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {

   static WebDriver driver = new ChromeDriver();
    
    @FindBy(id = "OneWay")
	private WebElement radio_oneWay;
	
	@FindBy(id = "FromTag")
	private WebElement txtbox_from;
	
	@FindBy(css = "ul#ui-id-1>li.list>a")
	private List<WebElement> lnk_autoSuggestionsOriginTxtBx;
	
	@FindBy(css = "ul#ui-id-2>li.list>a")
	private List<WebElement> lnk_autoSuggestionsDestinationTxtBx;
	
	@FindBy(id = "ToTag")
	private WebElement txtbox_to;
	
	
	@FindBy(id = "DepartDate")
	private WebElement txt_departCalendar;
	
	@FindBy(id = "SearchBtn")
	private WebElement btn_searchFlight;
	
	@FindBy(className = "searchSummary")
	private WebElement str_searchSummary;


    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        setDriverPath();
        driver.get("https://www.cleartrip.com/");
        PageFactory.initElements(driver, this);
        waitFor(2000);
        radio_oneWay.click();

        txtbox_from.clear();
        txtbox_from.sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        HotelBookingTest.explicitWait_till_ElementsVisibility(lnk_autoSuggestionsOriginTxtBx);
        select_autosuggestion_value_by_index(lnk_autoSuggestionsOriginTxtBx , 0);

        txtbox_to.clear();
        txtbox_to.sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        HotelBookingTest.explicitWait_till_ElementsVisibility(lnk_autoSuggestionsDestinationTxtBx);
        //select the first item from the destination auto complete list
        select_autosuggestion_value_by_index(lnk_autoSuggestionsDestinationTxtBx , 0);
        
        txt_departCalendar.click();
        
        selectdate(txt_departCalendar,29,9,2019);

        //all fields filled in. Now click on search
        btn_searchFlight.click();

        SignInTest.explicitWaitTillElementVisibility(str_searchSummary);
        
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

        //close the browser
        driver.quit();

    }
    
    private void selectdate(WebElement element,int date,int month,int year) {
    	String reqDate=date+"";
    	String reqMonth=month+"";
    	String reqYear=year+"";
    	element.sendKeys(reqDate+"/"+reqMonth+"/"+reqYear);
    }


    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    public void select_autosuggestion_value_by_index(List<WebElement> elements,int indx) {
    	try {
    		elements.get(indx).click();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
}
