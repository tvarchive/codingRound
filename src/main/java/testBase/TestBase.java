package testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.sun.jna.Platform;
/*Test Base Class is Useful for following things
 * Check for the platform and It will open the Browser
 * All properties files declared here. in this class read the properties file value and return values
 * Close the Browser
 * */
public class TestBase {

	//Now driver is common for all test case classes
	public static WebDriver driver;
	
	public Properties prop;
	public FileInputStream fileinputstream;


	public void commonsetup() throws IOException{
		setDriverPath();
		loadproperties();
        driver.get(prop.getProperty("url"));
	}
	
	// This method is used to identify the current system platform and set the property for chrome driver class;
	 public void setDriverPath() {
	    	// Platform is class it has the method all operating system (It will check current system os and return true or false
	    	if (Platform.isMac()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver");
	        }
	        if (Platform.isWindows()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	        }
	        if (Platform.isLinux()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
	        }
	        
	        driver = new ChromeDriver();
	        implicitwait_method(20);
	    }

	public void loadproperties() throws IOException {
		prop = new Properties();

		try {
			fileinputstream = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\pageLocators\\config.properties");
			prop.load(fileinputstream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void implicitwait_method(long timeunitforwait){
		driver.manage().timeouts().implicitlyWait(timeunitforwait, TimeUnit.SECONDS);
	}

	
	public void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
	
	// Common method for all date pickers in our given website.
	 public void datepickers(String dateValue, WebElement monthYear1, WebElement monthYear2, List<WebElement> days, List<WebElement> datepicker_Nextbtn, WebElement next_btn){
	    	String day = dateValue.split("-")[0];
			String month_year = dateValue.split("-")[1];
			System.out.println(day);
			System.out.println(month_year);
			waitFor(1000);
			
			String websiteLeftsiteMonthYear= monthYear1.getText();
			System.out.println(websiteLeftsiteMonthYear);
			String websiteRightsiteMonthYear= monthYear2.getText();
			System.out.println(websiteRightsiteMonthYear);
			if(month_year.toLowerCase().equals(websiteLeftsiteMonthYear.toLowerCase())){
				List<WebElement> websiteDatelist = days;
			
				for(int i=0; i<websiteDatelist.size();i++){
					if(websiteDatelist.get(i).getText().equals(day)){
						System.out.println("Selected Date:"+websiteDatelist.get(i).getText());
						
						websiteDatelist.get(i).click();
						break;
					}
				}
			}
			else if(month_year.toLowerCase().equals(websiteRightsiteMonthYear.toLowerCase())){
				List<WebElement> websiteDatelist = days;
				
				for(int i=0; i<websiteDatelist.size();i++){
					if(websiteDatelist.get(i).getText().equals(day)){
						System.out.println("Selected Date:"+websiteDatelist.get(i).getText());
						
						websiteDatelist.get(i).click();
						break;
					}
				}
			}
			else if(datepicker_Nextbtn.size()!=0){
				waitFor(2000);
				next_btn.click();
				datepickers(dateValue,monthYear1,monthYear2, days, datepicker_Nextbtn, next_btn);
			}
		}
	@AfterClass
	public void close_Browser(){
		driver.quit();
	}
}