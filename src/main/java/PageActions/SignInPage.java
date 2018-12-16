package PageActions;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utils.CommonFunctions;
public class SignInPage extends CommonFunctions{
	WebDriver driver=null;
	private String mnuYourTrips = "linkText|Your trips";
	private String LinkSignIn = "id|SignIn";
	private String btnSignIn = "xpath|//button[@id='signInButton']";
	private String txtUsername="id|email";
	private String txtPassword="id|password";
	private String lblError="id|errors1";
	private WebElement SignInbutton;
	public SignInPage(WebDriver driver2) {
		super(driver2);
		driver = driver2;
	}
	
	
	//To enter Username in username field 
	public void setUserName(String Email) {
		CommonFunctions f1= new CommonFunctions(driver);
		try {
			f1.input(txtUsername, Email);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	//To set password in password field
	public void setPassword(String Passw) {
		CommonFunctions f1= new CommonFunctions(driver);
		try {
			f1.input(txtPassword, Passw);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public boolean signIn(String UserName, String Password) {
		CommonFunctions f1= new CommonFunctions(driver);
		f1.click(mnuYourTrips);
		f1.click(LinkSignIn);
		boolean value = false;
		f1.waitFor(200);
		try {
			setUserName(UserName);
			setPassword(Password);
			f1.click(btnSignIn);

		} catch (Exception e) {

		}
		return value;
	}
	
	//to verify the error message upon invalid login
	public boolean chkErrorMsg(){
		CommonFunctions f1= new CommonFunctions(driver);
		f1.waitFor(20);
		
		f1.click(mnuYourTrips);
		f1.click(LinkSignIn);
		f1.waitFor(20);
		driver.switchTo().frame("modal_window");
		f1.waitFor(200);
		Actions act= new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//img[@src='//ui.cltpstatic.com/images/global/cleartrip_logo_medium.gif']")));
		SignInbutton=driver.findElement( By
				.xpath("//button[@id='signInButton']"));
		act.moveToElement(SignInbutton).build().perform();
		f1.click(btnSignIn);
		try{
			Assert.assertTrue(driver.findElement(f1.getLocator(lblError)).getText().contains("There were errors in your submission"));
			return true;
		}
		catch(Exception e){
			return false;
		}
		
	}
}
