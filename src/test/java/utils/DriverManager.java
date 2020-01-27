package utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DriverManager {

    private Set<DriverEntry> driverPool;
    private static DriverManager driverManager = null;

    private DriverManager(int threadCount) {
        driverPool = new HashSet<>(threadCount);
    }

    public static DriverManager getInstance() {
        if (driverManager == null) {
            driverManager = new DriverManager(getThreadCount());
        }
        return driverManager;
    }

    public static WebDriver getDriver() {
        for (int i = 0; i < 5; i++) {
            WebDriver driver = getDriverInternal();
            if (driver != null) {
                return driver;
            } else if (getInstance().driverPool.size() < getThreadCount()) {
                return createWebDriver();
            } else {
                sleep();
            }
        }
        throw new RuntimeException("Could not find a driver to return");
    }

    public static void withDriver(Consumer<WebDriver> function) {
        WebDriver driver = DriverManager.getDriver();
        function.accept(driver);
        DriverManager.returnDriver(driver);
    }

    public interface Drivers {
        void accept(WebDriver driver1, WebDriver driver2);
    }

    public static void withDrivers(Drivers function) {
        WebDriver driver1 = DriverManager.getDriver();
        WebDriver driver2 = DriverManager.getDriver();
        try {
            function.accept(driver1, driver2);
        } finally {
            DriverManager.returnDriver(driver1);
            DriverManager.returnDriver(driver2);
            System.out.println("Returned");
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void returnDriver(WebDriver webDriver) {
        for (DriverEntry entry : DriverManager.getInstance().driverPool) {
            if (entry.getDriver() == webDriver) {
                assert entry.isAvailable() == false : "Cannot return a driver which is already available";
                entry.toggleAvailability();
            }
        }
    }

    private static WebDriver getDriverInternal() {
        for (DriverEntry entry : DriverManager.getInstance().driverPool) {
            if (entry.isAvailable()) {
                entry.toggleAvailability();
                return entry.getDriver();
            }
        }
        return null;
    }

    private static URL getHubURL() {
        try {
            return new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Supplier<WebDriver>> browserCreator() {
        Map<String, Supplier<WebDriver>> browserCreator = new HashMap<>();
        browserCreator.put("Chrome", () -> new ChromeDriver(getChromeOptions()));
        browserCreator.put("Firefox", () -> new FirefoxDriver());
        browserCreator.put("RemoteWithChrome", () -> new RemoteWebDriver(getHubURL(), DesiredCapabilities.chrome()));
        browserCreator.put("RemoteWithFirefox", () -> new RemoteWebDriver(getHubURL(), DesiredCapabilities.firefox()));
        return browserCreator;
    }


    private static WebDriver createWebDriver() {
        String browser = System.getProperty("browser", "Chrome");

        Supplier<WebDriver> driverSupplier = browserCreator().get(browser);
        if (driverSupplier == null) {
            throw new RuntimeException("Unknown browser type, supported types are:" + browserCreator().keySet());
        }
        WebDriver driver = driverSupplier.get();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getInstance().driverPool.add(new DriverEntry(driver, false));
        return driver;
    }

    private static Capabilities getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");
        return options;
    }

    private static int getThreadCount() {
        return 5;
    }

    public static void quitAllDrivers() {
        for (DriverEntry entry : DriverManager.getInstance().driverPool) {
            entry.getDriver().quit();
        }
    }

    public static class DriverEntry {
        private WebDriver driver;
        private boolean isAvailable;

        DriverEntry(WebDriver driver, boolean isAvailable) {
            this.driver = driver;
            this.isAvailable = isAvailable;
        }

        WebDriver getDriver() {
            return driver;
        }

        boolean isAvailable() {
            return isAvailable;
        }

        void toggleAvailability() {
            isAvailable = !isAvailable;
        }
    }
}
