import com.sun.javafx.PlatformUtil;
import com.sun.jna.Platform;

import testBase.TestBase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/* Login Page Test Cases Scenarios
   1.  Open our given website url
   2.  Then Navigate to the chrome browser
   3.  Wait for "Your trips" text present
   4.  Verify the Title
   5.  Click on the "Your Trips" link
   6.  And then Click on "Sign In" link
   7. Without enter any data click on the Submit (button).
 * */
public class SignInTest extends TestBase{

	 @FindBy(linkText = "Your trips")
	 static WebElement your_trips_txt;
	 
	 @FindBy(id="SignIn")
	 static WebElement signin_link;
	 
	 @FindBy(id="signInButton")
	 static WebElement signin_Btn;
	
	 @FindBy(id="errors1")
	 static WebElement error1_msg;
	 
	 @FindBy(xpath="//*[@id='ContentFrame']/div/div/h1")
	 static List<WebElement> popup_head_msg;
	 
	 int total=0;
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws IOException {
    	
    	//PageFactory Class has the method called initElements method It will return the all element which u r initialize in given class
        PageFactory.initElements(driver, SignInTest.class);
        /*waitFor(2000);*/
	        
        your_trips_txt.click();
        signin_link.click();
       
        waitFor(2000);
        find_and_IdentifyFrames();
        signin_Btn.click();
        
        String errors1 = error1_msg.getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
        // After test is done Switch back to the main page.
        driver.switchTo().defaultContent();
       
    }

    // In this method will find the frames and switched to the frames. 
	private int find_and_IdentifyFrames() {
		
		int size = driver.findElements(By.tagName("iframe")).size();
		for(total=0; total<=size; total++){
			driver.switchTo().frame(total);
			int test =popup_head_msg.size();
			System.out.println(test);
			if(test!=0)break;
			else driver.switchTo().defaultContent();
			    
		}
		return total;

		
	}

}
