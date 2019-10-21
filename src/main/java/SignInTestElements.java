import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInTestElements {
	WebDriver driver;
    By yourTrips = By.linkText("Your trips");
    By signIn = By.id("SignIn");
    By signInButton = By.id("signInButton");
    By errors = By.id("errors1");
    By iframe = By.name("modal_window");
    
	public SignInTestElements(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement yourTripsElement()
	{
		return driver.findElement(yourTrips);
	}
	
	public WebElement signInElement()
	{
		return driver.findElement(signIn);
	}
	public WebElement signInButtonElement()
	{
		return driver.findElement(signInButton);
	}
	public WebElement errorMsgElement()
	{
		return driver.findElement(errors);
	}
	public WebElement iFrameElement()
	{
		return driver.findElement(iframe);
	}
}
