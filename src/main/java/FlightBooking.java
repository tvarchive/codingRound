package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Testbase.TestBase;

public class FlightBooking extends TestBase{

	@FindBy(how = How.ID, using = "OneWay")
	WebElement OneWayTrip;
	
	@FindBy(how = How.ID, using = "FromTag")
	WebElement FromLocation;
	
	@FindBy(how = How.ID, using = "ToTag")
	WebElement ToLocation;
	
	@FindBy(how = How.ID, using = "DepartDate")
	WebElement DepartDate;
	
	@FindBy(how = How.ID, using = "SearchBtn")
	WebElement SearchBtn;
	
	@FindBy(xpath = "//*[@id='ui-id-1']/child::li")
	List<WebElement> listOfFromlocation;
	
	@FindBy(xpath = "//*[@id='ui-id-2']/child::li")
	List<WebElement> listOfTOlocation;
	
	@FindBy(how = How.XPATH, using = "//div[@id='ui-datepicker-div']/div/div/div/span[1]")
	List<WebElement> NOofMonth;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ui-datepicker-div']//table/tbody/tr/td/a")
	List<WebElement> Days;
		
	@FindBy(how = How.XPATH, using = "//*[@class='nextMonth ']")
	WebElement NextBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@class='searchSummary']")
	WebElement Elementsearch;
	
	
	
	
		
	 public FlightBooking() 
	 {
			PageFactory.initElements(driver, this);
  	 }
	 
	 public  void oneWayClick()
	 {
		 if(!OneWayTrip.isSelected())
	    	OneWayTrip.click();	    	 
	 }
	 
	 public  void fromLocationSelected(String FromLocationName) throws InterruptedException
	    {
		  FromLocation.sendKeys(FromLocationName);
		  waitFor(2000);
		  fromLocationComparing(FromLocationName);
	    }
	 
	 public  void toLocationSelected(String ToLocationName) throws InterruptedException
	    {
		  ToLocation.sendKeys(ToLocationName);	
		  waitFor(2000);
		  toLocationComparing(ToLocationName);
	    }
	 
	 public  void searchBtnClick()
	    {
		  SearchBtn.click();		    	 
	    }
	 
	 public void fromLocationComparing(String location) throws InterruptedException {
		 for(int i=1;i<listOfFromlocation.size();i++){
	            WebElement element=listOfFromlocation.get(i);
	            if(element.getText().contains(location)){
	            	driver.findElement(By.xpath("(//*[@id='ui-id-2']//li[@class='list']//a)[" + i + "]")).click();
	                break;
	            }
	        }	
	}
	 
	 public void toLocationComparing(String location) throws InterruptedException
	 {
		 for(int i=1;i<listOfTOlocation.size();i++){
	            WebElement element=listOfTOlocation.get(i);
	            if(element.getText().contains(location )){
	            	driver.findElement(By.xpath("(//*[@id='ui-id-1']//li[@class='list']//a)[" + i + "]")).click();
	                break;
	            }
	        }	
	}
	 
	 private void selectDate(String monthyear, String Selectday) throws InterruptedException
	 {		
		 for (int i=0; i<NOofMonth.size();i++)
			{
				System.out.println(NOofMonth.get(i).getText());
				//Selecting the month
				if(NOofMonth.get(i).getText().equals(monthyear))
				{				
					//Selecting the date				
					for (WebElement d:Days)
					{					
						System.out.println(d.getText());
						if(d.getText().equals(Selectday))
						{
							d.click();
							Thread.sleep(10000);
							return;
						}
					}								
					
				}			
						
			}
			NextBtn.click();
			selectDate(monthyear,Selectday);
		}
	 
	 public void spiltdateandSelectdate(String date) throws InterruptedException
	 {
		 DepartDate.click();
		 String splitter[] = date.split("-");
			String month_year = splitter[1];
			String day = splitter[0];	
			selectDate(month_year, day);
			
	 }
	 
	    public boolean isElementPresent() {
	        try {
	        	Elementsearch.isDisplayed();
	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }
	 
	 
}
