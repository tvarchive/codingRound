package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;
import utils.CommonFunctions;
import utils.Constants;

public class SignInPage extends TestBase {
	@FindBy(linkText = "Your trips")
	private WebElement YourTripsLnk;

	@FindBy(id = "SignIn")
	private WebElement SignInLnk;

	@FindBy(id = "signInButton")
	private WebElement SignInBtnonFrame;

	@FindBy(id = "errors1")
	private WebElement ErrorsDisplayed;

	public SignInPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnYourTripsLink() {
		CommonFunctions.explicitWaitForElement(YourTripsLnk, Constants.EXPLICIT_WAIT, driver);
		CommonFunctions.clickOnElement(YourTripsLnk);
	}

	public void clickSignInLink() {
		CommonFunctions.clickOnElement(SignInLnk);
	}

	public void switchToFrame() {
		CommonFunctions.switchToFrame(Constants.FRAME_1, driver);
	}

	public void clickSignInButton() {
		CommonFunctions.clickOnElement(SignInBtnonFrame);
	}

	public boolean verifyErrorDisplayed(String verificationText) {
		CommonFunctions.explicitWaitForElement(ErrorsDisplayed, Constants.EXPLICIT_WAIT, driver);
		if (ErrorsDisplayed.getText().contains(verificationText))
			return true;
		else
			return false;
	}
}
