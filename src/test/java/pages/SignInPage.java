package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import supportlibraries.ScriptHelper;

public class SignInPage extends PageBase {

	@FindBy(id = "userAccountLink")
	WebElement span_YourTripLinkText;
	
	@FindBy(id = "SignIn")
	WebElement buttont_SignIn;

	@FindBy(id = "signInButton")
	WebElement button_PopupSignInButton;

	@FindBy(id = "errors1")
	WebElement err_PopupErrorMessage;
	
	@FindBy(id = "modal_window")
	WebElement frame_signInModelWindow;

	public SignInPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver, this);
	}

	public void clickYourTripLink() {
		waitForElement(span_YourTripLinkText, 30);
		click(span_YourTripLinkText);
	}
	
	public void selectSignInButton() {
		waitForElement(buttont_SignIn, 30);
		click(buttont_SignIn);
	}
	
	public void selectPopupSignInButton() {
		waitForElement(button_PopupSignInButton, 30);
		click(button_PopupSignInButton);
	}
	
	
	public boolean verifySignInErrorMessage(String error_Message) {
		waitForElement(err_PopupErrorMessage, 30);

		return err_PopupErrorMessage.getText().toLowerCase().contains(error_Message.toLowerCase());
	}
	
	public void switchToFrame() {
		switchToiFrameById(frame_signInModelWindow);
	}
}
