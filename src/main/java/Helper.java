import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {

    public static WebElement explicitWait(WebDriver driver, int time, By locator){
        return (new WebDriverWait(driver, time)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForFrameAndSwitch(WebDriver driver, int time, String frame){
        (new WebDriverWait(driver, time)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }
}
