import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        driver.get("https://www.cleartrip.com/");
        findElementWithTimeout(By.linkText("Your trips")).click();
        findElementWithTimeout(By.id("SignIn")).click();

        WebElement loginFrame = findElementWithTimeout(By.id("modal_window"));

        driver.switchTo().frame(loginFrame);
        findElementWithTimeout(By.id("signInButton")).click();

        String errors1 = findElementWithTimeout(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }
}
