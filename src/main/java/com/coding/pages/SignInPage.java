package com.coding.pages;

import com.coding.base.BaseExecution;
import com.coding.objectrepository.SignInOR;
import com.coding.uicontroller.Click;
import com.coding.uicontroller.Loggers;
import com.coding.uicontroller.TextBox;
import com.coding.uicontroller.Wait;

public class SignInPage extends BaseExecution {
	Click click = new Click();
	TextBox textbox = new TextBox();
	Wait wait = new Wait();

	public void loginInVerifyErrorMessage() throws Exception {
		click.buttonClick(SignInOR.userLink);
		Loggers.info("Click on User Profile Link");
		click.buttonClick(SignInOR.link_signIn);
		Loggers.info("Click on SignIn Link");
		getDriver().switchTo().frame("modal_window");
		wait.waitForElementToDisplay(SignInOR.btn_signIn);
		click.buttonClick(SignInOR.btn_signIn);
		assertEqual(textbox.getTextValue(SignInOR.error_message), getProperties().getProperty("errorMessage"));
		getDriver().switchTo().defaultContent();
		click.buttonClick(SignInOR.btn_close);
		Loggers.info("Click on cross Icon");
	}
}
