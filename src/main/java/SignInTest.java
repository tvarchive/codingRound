import com.testvagrant.utils.MiscConstants;
import com.testvagrant.utils.PropertFileManager;
import com.testvagrant.utils.TestCaseTemplate;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends TestCaseTemplate {

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		// Navigating to signIn popup
		logger.log("Navigating to signIn popup");
		library.clickElement(library.getByObject("yourTrips")); // Click on Your Trips link
		library.clickElement(library.getByObject("signInLink")); // Click on Sign In link

		// Switching to iframe in Login popup
		logger.log("Switching to iframe in Login popup");
		library.switchToFrame(library.getByObject("loginFrame"));

		// CLick on Sign in button before entering login details for getting login error
		logger.log("CLick on Sign in button before entering login details for getting login error");
		library.clickElement(library.getByObject("signInBtn"));

		// Verify Error message after login without credentials
		logger.log("Verify Error message after login without credentials");
		By byLocErrorPanel = library.getByObject("errorMsg");
		library.waitForElementToDisplay(byLocErrorPanel);
		String errorMessage = driver.findElement(byLocErrorPanel).getText();
		Assert.assertTrue(errorMessage
				.contains(PropertFileManager.getPropertyFromFile("loginErrorMessage", MiscConstants.PROP_MISC)));

	}
}
