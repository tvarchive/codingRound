import com.sun.javafx.PlatformUtil;

import Pages.SignIn;
import Testbase.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest extends TestBase {

    SignIn Sig;
	@BeforeMethod
	public void setupHomePage()
	{
		BrowserUrllaunch();
	}


  @Test
  public void shouldThrowAnErrorIfSignInDetailsAreMissing()
  {
   	  Sig = new SignIn();
  	  waitFor(2000);
      Sig.Yourtripclick();
      waitFor(2000);
      Sig.SignInclick();
      waitFor(3000);
      Sig.framename(prop.getProperty("FrameID"));
      Sig.signInButtonclick();
      String errors1 = Sig.errorGetText();
      Assert.assertTrue(errors1.contains("There were errors in your submission"));
  }
 

  @AfterMethod
  public void  tearDown()
  {
 	driver.quit(); 
  }

}

© 2019 GitHub, Inc.
