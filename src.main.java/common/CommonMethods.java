package common;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import utils.ReadProperty;

public class CommonMethods {
	WebDriver driver;
	public static String pfileName = "testData.properties";
	public static Properties  prop = new ReadProperty().readPropertyFile(pfileName);
	
	
	public CommonMethods (WebDriver driver){
		this.driver = driver;
	}
	
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	
	public void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  
        }
    }
	
}
