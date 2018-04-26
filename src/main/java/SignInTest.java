import com.resources.ActionDriver;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends ActionDriver{

    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	waitForElementVisible(driver, By.linkText("Your trips"));
        clickOnObject(By.linkText("Your trips"));
        waitForElementVisible(driver, By.id("SignIn"));
        clickOnObject(By.id("SignIn"));

        driver.switchTo().frame("modal_window");
        System.out.println("frame");
        waitForElementVisible(driver, By.id("signInButton"));
        clickOnObject(By.id("signInButton"));

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
        driver.switchTo().defaultContent();
    }

}
