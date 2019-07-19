import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {
	
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		//the driver config and launch has been taken care of in BaseTest class 
       
        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();

        //we have to switch to a particular frame in order to work on it's elements 
        driver.switchTo().frame("modal_window");
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
    }
	//unused methods removed 

}