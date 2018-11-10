package com.CodeTest1;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;


@SuppressWarnings("restriction")
public class HotelBookingTest
{

    WebDriver driver;       
    
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    
    @Test
    public void shouldBeAbleToSearchForHotels()
    {
    	PageFactory.initElements(driver, this);
        
        waitFor(5000);
        
        hotelLink.click();
        
        waitFor(5000);

        localityTextBox.sendKeys("Indiranagar, Bangalore");
        
        waitFor(5000);
        
        List<WebElement> originOptions = driver.findElements(By.id("ui-id-3"));

        // this are all the links you like to visit
        for (WebElement webElement : originOptions)
        {
            webElement.click();
        }
        
        waitFor(5000);        

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        
        searchButton.click();
        
        waitFor(5000);

        driver.quit();

    }
    
    protected void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    
	@BeforeClass
	public void setupApplication()
	{
		if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "CommonJarFiles\\chromedriver");
            
            driver = new ChromeDriver();
            
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "CommonJarFiles\\chromedriver.exe"); //Should provide path of the driver
            
            driver = new ChromeDriver();            
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "CommonJarFiles\\chromedriver_linux");
            
            driver = new ChromeDriver();
        }		
		
		driver.manage().window().maximize();
		
		driver.get("https://www.cleartrip.com/");		
	}
	
	
	@AfterClass
	public void closeApplication()
	{
		driver.quit();
		
	}

}
