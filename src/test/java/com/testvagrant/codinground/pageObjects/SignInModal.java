package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class SignInModal extends BasePage{

	public SignInModal(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(id="#ModalFrame #modal_window")
	private WebElement sign_in_modal_frame;
	
	@FindBy(id="signInButton")
	private WebElement btn_sign_in;
	
	@FindBy(xpath="//div[@id='errors1' and contains(@style,'block')]//span")
	private WebElement section_error_message;
	
	public void isSignInModalPresent(){
		wait.explicitWaitForElement(sign_in_modal_frame);
	}
	
	public String blankFormSignIn(){
		switchToFrame(sign_in_modal_frame);
		
		wait.explicitWaitForElement(btn_sign_in);
		btn_sign_in.click();
		wait.explicitWaitForElement(section_error_message);
		
	
		return section_error_message.getText();
		
	}
	
	
	
}
