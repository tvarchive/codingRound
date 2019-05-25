package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Testbase.TestBase;

public class HotelBooking extends TestBase{

	   
	@FindBy(how = How.LINK_TEXT, using = "Hotels")
	WebElement hotelLink;
	
	@FindBy(how = How.ID, using = "Tags")
	WebElement localityTextBox;
	
	@FindBy(how = How.ID, using = "SearchHotelsButton")
	WebElement searchButton;
	
	@FindBy(how = How.ID, using = "travellersOnhome")
	WebElement travellerSelection;
	
	@FindBy(xpath = "//*[@id='ui-id-1']//following-sibling::li[@class='list']")
	List<WebElement> listOflocation;
	    
	    public HotelBooking() 
	    {
			PageFactory.initElements(driver, this);
     	}
	    
	    public void HotelLinkClick()
	    {
			new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(hotelLink)).click();
		}
	    
	    public void localityTextBox(String HotelLocationName) throws InterruptedException
	    {
	    	new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(localityTextBox)).sendKeys(HotelLocationName);
	    	locationlistSelected(HotelLocationName);
	    	Actions action = new Actions(driver);
	    	action.sendKeys(Keys.ESCAPE).build().perform();
	    	
	    }
	    
	    public void travellerSelection(String travelselectionName) 
	    {
	    	new Select(travellerSelection).selectByVisibleText(travelselectionName);
	    	waitFor(9000);
	        searchButton.click();
		}
	    
	    public void locationlistSelected(String HotelLocationName) throws InterruptedException {
			 for(int i=1;i<listOflocation.size();i++){
		            WebElement element=listOflocation.get(i);
		            if(element.getText().contains(HotelLocationName)){
		            	driver.findElement(By.xpath("(//*[@id='ui-id-1']//following-sibling::li[@class='list']//a)[" + i + "]")).click();
		                break;
		            }
		        }	
		}
}
