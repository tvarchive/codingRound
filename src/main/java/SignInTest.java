import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SignInTest {

	    
	WebDriver driver;
	WebDriverManager webDriverManager;
	
	@BeforeTest
	public void setup()
	{
		webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
	}

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	
    	driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        driver.findElement(By.linkText("Your trips")).click();
        waitFor(2000);
        driver.findElement(By.id("SignIn")).click();
        waitFor(2000);
        //switching to frames        
	    driver.switchTo().frame("modal_window"); // Switching to innerframe
        waitFor(2000);
        driver.findElement(By.id("signInButton")).click();
        waitFor(2000);
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
    }
    
    @AfterTest
	public void tearDown()
	{
		
    	webDriverManager.closeDriver();
	}

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    


}
