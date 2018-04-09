
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.App;
import pages.HomePage;

public class SignInTest {
    private App app;
    private HomePage homePage;

    public SignInTest() {
        app = new App();
        homePage = new HomePage(app);
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        app.launch();
        homePage.openUserAccountMenu();
        homePage.openSignInForm();
        homePage.clickSignIn();
        Assert.assertTrue(homePage.getSignInErrorText().contains("There were errors in your submission"));
        app.close();
    }
}
