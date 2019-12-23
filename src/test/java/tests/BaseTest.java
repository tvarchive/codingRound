package tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import application.App;

public class BaseTest {
    private App app;

    BaseTest() {
        this.app = App.getInstance();
    }

    @BeforeMethod
    public void setUp() {
        app.launch();
    }

    @AfterSuite
    public void tearDown() {
        app.close();
    }

    App getApp() {
        return app;
    }
}
