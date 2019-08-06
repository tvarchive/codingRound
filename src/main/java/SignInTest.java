import com.sun.javafx.PlatformUtil;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    static WebDriver driver = new ChromeDriver();
    
    @FindBy(linkText = "Your trips")
	private WebElement lnk_yourTrips;
    
    @FindBy(id = "SignIn")
	private WebElement btn_signIn;
    
    @FindBy(id = "modal_window")
	private WebElement iframe_signInPopup;
    
    @FindBy(id = "signInButton")
	private WebElement btn_signInOnPopup;
    
    @FindBy(id = "errors1")
	private WebElement str_submissionError;

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing(String childStr) {

    	childStr="There were errors in your submission";
    	
    	setDriverPath();

        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        
        //Initialize page objects
        PageFactory.initElements(driver, this);
        
        //Don't use static waits.Use explicit wait
        explicitWaitTillElementVisibility(lnk_yourTrips);

        lnk_yourTrips.click();
        btn_signIn.click();

        //switching to frame
        switchToFrame(iframe_signInPopup);
        btn_signInOnPopup.click();

        String errors1 = str_submissionError.getText();
        //Don't hardcode constants.Taking value from interface
        verifyChildStringInParentString(errors1, childStr);
        driver.quit();
    }

    static void explicitWaitTillElementVisibility(WebElement element) {
    	WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    private void verifyChildStringInParentString(String parentStr,String childStr) {
    	Assert.assertTrue(parentStr.contains(childStr));
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    
    private void switchToFrame(WebElement frame) {
    	driver.switchTo().frame(frame);
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
