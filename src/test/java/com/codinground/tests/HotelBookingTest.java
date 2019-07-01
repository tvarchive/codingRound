package com.codinground.tests;
import com.sun.javafx.PlatformUtil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest extends LoadableComponent<HotelBookingTest> {

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
    public void shouldBeAbleToSearchForHotels() throws InterruptedException {
        setDriverPath();
        
        driver = new ChromeDriver();
        driver.get("https://www.cleartrip.com/");
        PageFactory.initElements(driver, this);
     
        
        hotelLink.click();
        //driver.findElement(By.linkText("Hotels")).click();
String place = "Indiranagar, Bangalore";
for(int i = 0; i<place.length();i++) {
	String sb = new StringBuilder().append(place.charAt(i)).toString();
	localityTextBox.sendKeys(sb);	
}
       
        Thread.sleep(6000);
       List <WebElement> opt= driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
       opt.get(1).click();
       
       driver.findElement(By.xpath(("//*[@id='ui-datepicker-div']/child::div[1]/table/tbody/tr[5]/td[7]/a"))).click();
       Thread.sleep(3000);
       driver.findElement(By.xpath(("//*[@id='ui-datepicker-div']/child::div[1]/table/tbody/tr[1]/td[1]/a"))).click();
       Thread.sleep(3000);
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        driver.quit();

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

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

}
