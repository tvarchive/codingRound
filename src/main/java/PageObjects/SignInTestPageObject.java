package PageObjects;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;

import Utils.DriverFactoy;

public class SignInTestPageObject extends DriverFactoy {

	public String signInErrorMsg() {
		// TODO Auto-generated method stub\
		return driver.findElement(By.id("errors1")).getText();

	}

	public void clickOnSignInButton() {
		// TODO Auto-generated method stub
		driver.findElement(By.id("SignIn")).click();
		waitFor(2000);
		driver.switchTo().frame("modal_window");
		driver.findElement(By.id("signInButton")).click();

	}

	public void clickOnTripsButton() {
		// TODO Auto-generated method stub
		driver.findElement(By.linkText("Your trips")).click();

	}

	
}
