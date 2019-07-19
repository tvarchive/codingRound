package clearTrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

	private WebDriver driver;

	private By yourTripsLink = By.linkText("Your trips");
	
	private By signInLink = By.id("SignIn");
	private By signInButtonInOverlayWindow = By.id("signInButton");

	public SignInPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}


	public void clickYourTripsLink() {
		// TODO Auto-generated method stub
        driver.findElement(yourTripsLink).click();

		
	}


	public void clickSignInLink() {
		// TODO Auto-generated method stub
        driver.findElement(signInLink).click();

	}


	public void switchToFrameModalWindow() {
		// TODO Auto-generated method stub
		//we have to switch to a particular frame in order to work on it's elements 
        driver.switchTo().frame("modal_window");
       
	}


	public void clickSignInButtonInOverlayWindow() {
		// TODO Auto-generated method stub
        driver.findElement(signInButtonInOverlayWindow).click();

	}


	public String getErrors() {
		// TODO Auto-generated method stub
		String errors = driver.findElement(By.id("errors1")).getText();
		return errors;
	}

}
