package com.coding.test;

import org.testng.annotations.Test;
import com.coding.pages.SignInPage;

public class SignInTest extends SignInPage {

	@Test(description = "Login without credientials")
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws Exception {
		try {
			loginInVerifyErrorMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		switchToHomePage();
	}
}
