package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesObjects.HomePage;
import utils.*;

public class SignInTest extends BaseTest {

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        DriverManager.withDriver(driver -> {
            HomePage homePage = new HomePage(driver);
            homePage.openWebsite();
            homePage.openUserAccountMenu();
            homePage.openSignInForm();
            homePage.clickSignIn();
            Assert.assertTrue(homePage.getSignInErrorText().contains("There were errors in your submission"));
        });
    }

    @Test
    public void testWithTwoDrivers() {
        DriverManager.withDrivers(new DriverManager.Drivers() {
            @Override
            public void accept(WebDriver driver1, WebDriver driver2) {
        driver1.get(PropertyUtils.getProperty("website_url"));
        driver2.get(PropertyUtils.getProperty("website_url"));
        Assert.assertTrue(true);
            }
        });
    }
}
