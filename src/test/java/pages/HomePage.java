package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.java.framework.FWUtils;

/**
 * HomePage.java
 * 
 * Contains all webelements and methods 
 * to be used to perform actions on Home page
 *
 */
public class HomePage {
	
	WebDriver driver;
	FWUtils utils = new FWUtils();
	
	@FindBy(linkText = "Your trips")
	WebElement yourTripsLink;
	
	@FindBy(id = "SignIn")
	WebElement signInBtn;
	
	@FindBy(id = "modal_window")
	WebElement signInFrame;
	
	@FindBy(id = "signInButton")
	WebElement signInButtonInFrame;
	
    @FindBy(linkText = "Hotels")
    WebElement hotelLink;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void openSignInModal() {
		// utils.waitFor(2000);
		yourTripsLink.click();
		signInBtn.click();
		driver.switchTo().frame("modal_window");
	}

	public void signInWithoutCredentials() {
		signInWithCTAccount(null, null, false);
		
	}
	
	public void signInWithCTAccount(String username, String password, boolean rememberMe) {
		
		if(username != null) {
			// set username in username field
		}
		
		if(password != null) {
			// set password in password field
		}
		
		if(rememberMe) {
			// update default value of remember me checkbox
		}
		
		signInButtonInFrame.click();
	}
	
	public void signInWithFBAccount(String fb_account_parameters) {
		// sign-in using fb account
	}

	public String getSignInErrors() {
        String errors1 = driver.findElement(By.id("errors1")).getText();
		return errors1;
	}

	public void goToHotelsBooking() {
		hotelLink.click();
		utils.waitFor(3000);
	}

	
	
}
