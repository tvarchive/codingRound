package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitUtils {
    public WebDriverWait waitForElementsVisibility(List<WebElement> element, int duration, final WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        return wait;
    }

    public WebDriverWait waitForElementVisibility(WebElement element, int duration, final WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOf(element));
        return wait;
    }
}
