package Pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    WebDriver driver;
    BasePage(WebDriver driver)
    {
        this.driver=driver;
    }


    public void loadPage(String url) {
        driver.get(url);
    }

    public void click(WebElement elem)
    {
        elem.click();
    }
    public void enterValue(WebElement elem,String text)
    {
        elem.sendKeys(text);
    }
    public void enterValue(WebElement elem,String text,Boolean clearBeforeEnter)
    {
        if (clearBeforeEnter)
            clear(elem);
        enterValue(elem,text);
    }
    public void clear(WebElement elem)
    {
        elem.clear();
    }


    public void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public void waitForElemPresence(By by)
    {
        WebDriverWait wait=new WebDriverWait(driver,4);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public void closeBrowser()
    {
        driver.quit();
    }

    public void waitForPageToLoad()
    {
        try {
            boolean found = false;
            int timeInSec = 0;
            while (found == false && timeInSec++ < 10) {
                JavascriptExecutor js=(JavascriptExecutor) driver;
                 String readyState = (String)js.executeScript("return document.readyState");
                if (readyState.equals("complete"))
                    found = true;
                if (!found)
                    Thread.sleep(1000);
            }
        }
        catch(Exception ex) {
            System.out.println("exception in pageload:" + ex);
            ex.printStackTrace();
        }
    }
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}



