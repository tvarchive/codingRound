package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends base {

    @FindBy(linkText = "Your trips")
    private WebElement yourTripLink;

    @FindBy(id = "SignIn")
    private WebElement signIn;

    @FindBy(id = "signInButton")
    private WebElement signInButton;

    @FindBy(id = "errors1")
    private WebElement errorText;

    String frameModal = "modal_window";

    WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void clickYourTripLink() {
        yourTripLink.click();
    }

    public boolean isYourTripLinkLoaded() {
        waitForVisible(signIn);
        if (signIn.isDisplayed()) {
            return true;
        }
        return false;
    }

    public void clickSignIn() {
        signIn.click();
    }

    public Boolean isSignInPageLoaded() {
        switchToFrame();
        waitForVisible(signInButton);
        if (signInButton.isDisplayed()) {
            return true;
        }
        return false;
    }

    public void clickSignInButton() {
        waitForVisible(signInButton);
        signInButton.click();
    }

    public String getErrorText() {
        waitForVisible(errorText);
        return errorText.getText();
    }

    public void switchToFrame() {
        driver.switchTo().frame(frameModal);
    }

}
