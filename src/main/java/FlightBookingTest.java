
import com.sun.javafx.PlatformUtil;

import testBase.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class FlightBookingTest extends TestBase{

	@FindBy(id = "OneWay")
	 private static WebElement oneway_radio_btn;

	@FindBy(id = "FromTag")
	 private static WebElement from_txt_box;
	
	@FindBy(id = "ToTag")
	 private static WebElement to_txt_box;
	
	
	@FindBy(id = "ui-id-1")
	 private static WebElement from_cities;
	
	@FindBy(id = "ui-id-2")
	 private static WebElement to_cities;
	
	@FindBy(id="SearchBtn")
	private static WebElement search_Btn;
	
	 @FindBy(xpath="//*[@id='ui-datepicker-div']/div[1]/div/div")
	    private static WebElement monthYear1;
	    
	    @FindBy(xpath="//*[@id='ui-datepicker-div']/div[2]/div/div")
	    private static WebElement monthYear2;
	    
	    
	    @FindBy(xpath="//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr/td/a")
	    private static List<WebElement> days;
	    
	    @FindBy(xpath="//*[@id='ui-datepicker-div']/div[2]/div/a")
	    private static List<WebElement> datepicker_Nextbtn;
	    
	    @FindBy(xpath="//*[@id='ui-datepicker-div']/div[2]/div/a")
	    private static WebElement next_btn;
	    
	   @BeforeClass
	   public void init() throws IOException{
			 
			commonsetup();
			//PageFactory Class has the method called initElements method It will return the all element which u r initialize in given class
			PageFactory.initElements(driver, FlightBookingTest.class);
		        /*waitFor(2000);*/
			 
		 }
	
    @Test
    public void testThatResultsAppearForAOneWayJourney() throws IOException {
    	
        oneway_radio_btn.click();

        from_txt_box.clear();
        from_txt_box.sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin
        waitFor(2000);
      
        selectcities(from_cities);
        
        to_txt_box.clear();
        to_txt_box.sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        waitFor(2000);
        //select the first item from the destination auto complete list
     
        selectcities(to_cities);
        
        // select the date from the date picker
        waitFor(2000);
        datepickers("12-January 2018",monthYear1,monthYear2,days,datepicker_Nextbtn,next_btn);
        /*driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();*/

        //all fields filled in. Now click on search
        search_Btn.click();

        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));


    }


    public void selectcities(WebElement element) {
    	  List<WebElement> originOptions =   element.findElements(By.tagName("li"));
    	  System.out.println(originOptions.get(0).getText());
          originOptions.get(0).click();
		
	}


	private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
   
    
}
