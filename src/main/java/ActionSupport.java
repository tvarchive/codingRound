import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ActionSupport {

    WebDriver driver;
    WebElement element = null;
    Utility oUtility;

    public ActionSupport(WebDriver driver) {
        this.driver = driver;
        oUtility = new Utility(driver);

    }

    public void clickElement(By selector) {

        element = oUtility.getWebElement(selector);
        try {
            element.click();
        } catch (Exception e) {
            System.out.println("element could not be clicked, Exception - " + e.getMessage());
        }
    }

    public void setTextToInputfield(By selector, String text) {

        element = oUtility.getWebElement(selector);
        try {
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("The text - " + text + " could not be set to element due to exception - " + e.getMessage());
        }
    }

    public String getElementText(By selector) {
        element = oUtility.getWebElement(selector);
        return element.getText();
    }

    public void switchToFrameByWebElement(By selector) {
        element = oUtility.getWebElement(selector);
        try {
            driver.switchTo().frame(element);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectValueByTextInDropdown(By selector, String text) {
        try {
            Select dropdown = new Select(oUtility.getWebElement(selector));
            dropdown.selectByVisibleText(text);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
