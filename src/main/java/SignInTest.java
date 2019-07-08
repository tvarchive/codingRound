import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import codingRound.Pages.SignIn_Page;
import codingRound.Utilities.Page;

public class SignInTest extends Page{

    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	new Page().homePage();
        
        
        new SignIn_Page().signIn();
        
        new SignIn_Page().verifySubmission();
        
    }

    @AfterClass
    public void endTest() {
    	 driver.quit();
    }

   


}
