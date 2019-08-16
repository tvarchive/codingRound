package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import common.CommonMethods;



public class LoginPage {
	
	WebDriver driver;
	CommonMethods cm;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		cm = new CommonMethods(driver);
	}
	
    
    By your_Trip = By.linkText("Your trips");
	By btn_SignIn = By.id("signInButton");
	By sigin_aftertopic = By.id("SignIn");
	
	public void clickYourtrip(){
		driver.findElement(your_Trip).click();
	}
	
	public void clicksignIn() {
		driver.findElement(sigin_aftertopic).click();
	}
	
	public void clickSignInButton(){
		driver.findElement(btn_SignIn).click();
	}

	
	public void loginerror() {
		try {
		clickYourtrip();
		clicksignIn();
		cm.waitFor(2000);
		clickSignInButton();
		}catch(Exception e) {
			System.out.println("Login Error verification fail ");
		}
		
	}

}
