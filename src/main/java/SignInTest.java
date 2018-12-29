
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.SignInPage;
import testbase.TestBase;

import utils.Constants;

public class SignInTest extends TestBase {

	SignInPage oSignPage;

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		oSignPage = new SignInPage();

		oSignPage.clickOnYourTripsLink();

		oSignPage.clickSignInLink();

		oSignPage.switchToFrame();

		oSignPage.clickSignInButton();

		Assert.assertTrue(oSignPage.verifyErrorDisplayed(Constants.ERROR_STRING_TO_VALIDATE));

	}

}
