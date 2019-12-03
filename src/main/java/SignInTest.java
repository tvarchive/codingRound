import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePageElements;

public class SignInTest extends BaseTest{


    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        driver.get(BASE_URL);

        oUtility.waitForElementToBeVisible(HomePageElements.your_trips());

        actions.clickElement(HomePageElements.your_trips());

        actions.clickElement(HomePageElements.sign_in());

        actions.switchToFrameByWebElement(HomePageElements.frame_for_sign_in_modal());
        oUtility.waitForElementToBeVisible(HomePageElements.sign_in_button());
        actions.clickElement(HomePageElements.sign_in_button());


        String text = actions.getElementText(HomePageElements.sign_in_error1());
        Assert.assertTrue(text.contains(text));

    }
}
