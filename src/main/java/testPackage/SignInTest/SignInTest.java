package testPackage.SignInTest;

import org.testng.annotations.Test;

import testUtils.TestConfig;

public class SignInTest extends TestConfig {

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		SignInPageObjects spo = new SignInPageObjects(getdriver());

		getdriver().get("https://www.cleartrip.com/");

		spo.clickYourTripLink();
		spo.clickSignInlink();
		spo.clickSignInbtn();
		spo.checkErrorText("There were errors in your submission");

	}

}
