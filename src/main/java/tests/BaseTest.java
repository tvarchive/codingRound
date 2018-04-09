package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.App;

public class BaseTest {
    private App app;

    public BaseTest() {
        this.app = new App();
    }

    @BeforeMethod
    public void setUp() {
        app.launch();
    }

    @AfterMethod
    public void tearDown() {
        app.close();
    }

    protected App getApp() {
        return app;
    }
}
