package codingRound.cleartrip;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import codingRound.commonLib.CommonFunctionsLib;
import codingRound.utility.Configurations;

public class SignIn {
	   @FindBy(linkText = "Your trips")
	    private WebElement tripsLink;

	    @FindBy(id = "SignIn")
	    private WebElement signIn;

	    @FindBy(id = "signInButton")
	    private WebElement signInButton;
	    
	    @FindBy(id="signinForm")
	    private WebElement signInForm;

	    @FindBy(id = "errors1")
	    private WebElement errorText;
	    
		public WebDriver driver;
		public ExtentTest logger; 
		
		public SignIn(WebDriver driver, ExtentTest logger) {
			this.driver = driver;
			this.logger = logger;
			PageFactory.initElements(driver, this);

		}
		
		public void signIn(Configurations testConfig, CommonFunctionsLib common) {
			tripsLink.click();
			signIn.click();
			String expectedErrorMsg="There were errors in your submission Your username is a required field Your account password is a required field";
			common.waitForPageLoaded(driver, logger);
			driver.switchTo().frame("modal_window");
			common.waitToBeClickable(signInButton, driver);
			signInButton.click();
			String signInError=errorText.getText();
			common.softAssert("Validating for invalid login", expectedErrorMsg, signInError, 
					common.compareString(expectedErrorMsg, signInError, true), logger);
			
		}
}
