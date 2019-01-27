package testPackage.SignInTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignInPageObjects {

	WebDriver driver;

	@FindBy(id = "userAccountLink")
	WebElement tripLink;

	@FindBy(id = "SignIn")
	WebElement signInLink;

	@FindBy(id = "signInButton")
	WebElement signInBtn;

	@FindBy(id = "errors1")
	WebElement errorText;

	public SignInPageObjects(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	public void clickYourTripLink() {
		tripLink.click();
	}

	public void clickSignInlink() {
		signInLink.click();
	}

	public void clickSignInbtn() {
		driver.switchTo().frame("modal_window");
		signInBtn.click();
	}

	public void checkErrorText(String expectedText) {
		Assert.assertTrue(errorText.getText().contains(expectedText));
	}

}
