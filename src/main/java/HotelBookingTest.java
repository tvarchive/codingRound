


import testBase.TestBase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HotelBookingTest extends TestBase{
    

    @FindBy(linkText = "Hotels")
    private static WebElement hotelLink;

    @FindBy(id = "Tags")
    private static WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private static WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private static WebElement travellerSelection;
    
    @FindBy(id="ui-id-1")
    private static WebElement cities;
    
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
		PageFactory.initElements(driver, HotelBookingTest.class);
	        /*waitFor(2000);*/
		 
	 }
    
    @Test
    public void shouldBeAbleToSearchForHotels() throws IOException, InterruptedException {
  
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");
        waitFor(2000);
        selectcities(cities);
        
        datepickers("10-December 2017",monthYear1,monthYear2,days,datepicker_Nextbtn,next_btn);
        datepickers("12-January 2018",monthYear1,monthYear2,days,datepicker_Nextbtn,next_btn);
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        /*driver.quit();*/

    }
    
    public void selectcities(WebElement element) {
  	  List<WebElement> originOptions =   element.findElements(By.tagName("a"));
  	  System.out.println(originOptions.get(0).getText());
        originOptions.get(0).click();
		
	}
   
}
