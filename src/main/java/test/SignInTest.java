package test;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.SignInPage;
import page.base;

import java.io.IOException;

public class SignInTest extends base {

    SignInPage signInPage;
    String errorText = "There were errors in your submission";

    @BeforeTest
    public void setUpDriver() throws IOException {
        setUp();
        loadHomePage();
        signInPage = new SignInPage(driver);
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        signInPage.clickYourTripLink();
        Assert.assertTrue(signInPage.isYourTripLinkLoaded());
        signInPage.clickSignIn();
        Assert.assertTrue(signInPage.isSignInPageLoaded());
        signInPage.clickSignInButton();
        String error = signInPage.getErrorText();
        Assert.assertTrue(error.contains("There were errors in your submission"));
    }
}
