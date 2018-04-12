package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage {
    WebDriver driver;

    @FindBy(linkText = "Your trips")
    WebElement yourTrips;

    @FindBy(id = "SignIn")
    WebElement signIn;

    @FindBy(id = "signInButton")
    WebElement signInButton;

    @FindBy(id = "errors1")
    WebElement fetchErrors;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Click on login button
    public void clickLogin() {
        waitAndClick(yourTrips);
        waitAndClick(signIn);
        waitAndClick(signInButton);
    }


    //Get the title of Login Page
    public String getLoginTitle() {
        return driver.getTitle();
    }

    public void login() {
        this.clickLogin();
    }

    public String fetchErrors() {
        return fetchErrors.getText();
    }

    private void waitAndClick(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            WebElement elem1 = wait.until(ExpectedConditions.elementToBeClickable(element));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='4px groove green'", elem1);
            Thread.sleep(1000);
            js.executeScript("arguments[0].style.border=''", elem1);

            Actions builder = new Actions(driver);
            builder.moveToElement( elem1 ).click( elem1 );
            builder.perform();

            Thread.sleep(2000);
        }

        catch(Exception e) {
            e.getStackTrace();
        }
    }
}
