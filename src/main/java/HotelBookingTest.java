import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {

    static WebDriver driver = new ChromeDriver();

    @FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(css = "ul#ui-id-1>li>a")
	private List<WebElement> autosuggestionlocalityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        setDriverPath();

     // Maximizing window
     		driver.manage().window().maximize();

     		// Initialize the page objects.
     		PageFactory.initElements(driver, this);

     		hotelLink.click();

     		localityTextBox.sendKeys("Indiranagar, Bangalore");

     		// Create a method to select value from auto-suggested values populated by Where text box.
     		selectAutoSuggestedValue(autosuggestionlocalityTextBox, "Indiranagar, Bangalore");

     		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
     		searchButton.click();

        driver.quit();

    }
    
    private static void selectAutoSuggestedValue(List<WebElement> elements, String partialLinkName) {
		try {
			explicitWait_till_ElementsVisibility(elements);
			int lnkCnt = elements.size();
			for (int i = 0; i < lnkCnt; i++) {
				WebElement link = elements.get(i);
				if (link.getText().contains(partialLinkName)) {
					link.click();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	static void explicitWait_till_ElementsVisibility(List<WebElement> elements) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

}
