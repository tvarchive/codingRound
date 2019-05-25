package Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Testbase.TestBase;

public class SignIn extends TestBase {

	
	@FindBy(how = How.ID, using = "userAccountLink")
	WebElement Yourtrips;
	
	@FindBy(how = How.ID, using = "SignIn")
	WebElement SignIn;
	
	@FindBy(how = How.ID, using = "signInButton")
	WebElement signInButton;
	
	@FindBy(how = How.ID, using = "errors1")
	WebElement errors;
	
	@FindBy(how = How.TAG_NAME, using = "iframe")
	WebElement Iframe;
		    

	    public SignIn() 
	    {
			PageFactory.initElements(driver, this);
  	    }
	    
	    public  void Yourtripclick()
	    {
	    	 Yourtrips.click();
	    }
	    
	    public  void SignInclick()
	    {
	    	SignIn.click();	    	 
	    }
	    
	    public  void signInButtonclick()
	    {
	    	
	    	 signInButton.click();	 
	    }
	    
	    public  String errorGetText()
	    {
	    	return errors.getText();
	    }
	
	    public void framename(String framename)
	    {
	    	driver.switchTo().frame(framename);
	    }	    
}
