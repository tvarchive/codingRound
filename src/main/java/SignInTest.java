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
import org.testng.annotations.DataProvider;
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
	 
	 @FindBy(id="email")
	 static WebElement email_id;
	 
	 @FindBy(id="password")
	 static WebElement password;
	 
	 @FindBy(id="signInButton")
	 static WebElement signin_Btn;
	
	 @FindBy(id="errors1")
	 static WebElement error1_msg;
	 
	 @FindBy(xpath="//*[@id='ContentFrame']/div/div/h1")
	 static List<WebElement> popup_head_msg;
	 
	 int total=0;
	 
	
    @Test(dataProvider="getTestData")
    public void shouldThrowAnErrorIfSignInDetailsAreMissing(String data1, String data2) throws IOException {
    	
    	//PageFactory Class has the method called initElements method It will return the all element which u r initialize in given class
        PageFactory.initElements(driver, SignInTest.class);
        /*waitFor(2000);*/
        your_trips_txt.click();
        signin_link.click();
       
        waitFor(2000);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='https://www.cleartrip.com/signin?popup=yes&service=/']")));
        // validation for email and password fields:
        email_id.clear();
        email_id.sendKeys(data1);
        
        password.clear();
        password.sendKeys(data2);
        signin_Btn.click();
        
        String errors1 = error1_msg.getText();
        	  Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
        // After test is done Switch back to the main page.
        driver.switchTo().defaultContent();
       
    }

	@DataProvider
	public Object[][] getTestData(){
		
		Object[][] data = new Object[4][2];
		
		// Both email and password is blank;
		data[0][0] = "";
		data[0][1] = "";
		
		//Email is Blank and Password is filled;
		data[1][0] = "";
		data[1][1] = "12345678";
		
		//Email is filled and Password is Blank;
		data[2][0] = "email@yopmail.com";
		data[2][1] = "";
		
		//Email and password both is invalid 
		data[3][0] = "email@yopmail.com";
		data[3][1] = "12345678";
		
		//Email and password both is valid 
		
		return data;
	
	}

}
