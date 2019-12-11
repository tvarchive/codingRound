import com.codoingRoiund.utils.WaitForElement;
import com.codoingRound.BasePage.BaseObject;
import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseObject {


    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws Exception {
    	
    	WaitForElement wait = new WaitForElement();

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        
        int size = driver.findElements(By.tagName("iframe")).size();
        wait.waitFor(5000);
        
        WebElement fr=driver.findElement(By.xpath("//iframe[@class='spinnerMedium']"));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(fr);
        
//      wait.elementToAppear("//button[@id='signInButton']", 20);
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        System.out.println("Error : "+errors1);
        Assert.assertEquals(errors1, "There were errors in your submission\nYour username is a required field\nYour account password is a required field");
    }

    


}
