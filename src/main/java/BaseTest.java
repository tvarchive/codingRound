import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    /**
     * this function wail wait for the locator to be visible for 40 sec max.WebDriverWait will call ExpectedCondition every 500 milliseconds until it returns successfully
     * @param element
     * @param locator
     * @return
     */
    private WebDriverWait wait;
    WebDriver driver = new ChromeDriver();

    public boolean waitForElementToBeVisible( String element, String locator)
    {
        try
        {
            wait  = new WebDriverWait(driver, 40);
            if (locator.equalsIgnoreCase("id"))
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(element))));
            else if(locator.equalsIgnoreCase("xpath"))
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(element))));
            else if(locator.equalsIgnoreCase("className"))
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className(element))));
            else if(locator.equalsIgnoreCase("cssSelector"))
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(element))));


        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }
}
