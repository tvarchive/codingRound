package codingRound.cleartrip;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import codingRound.commonLib.CommonFunctionsLib;
import codingRound.utility.Configurations;

public class FlightBooking {
	
	@FindBy(id="OneWay")
	public static WebElement onewayRadiobtn;
	
	@FindBy(id="FromTag")
	public static WebElement fromTag;
	
    @FindBy(id="ToTag")
    public static WebElement toTag;
    
    @FindBy(xpath="//input[@class='keyValue span span24 required arabicChars ui-autocomplete-input']")
    public static WebElement fromInput;

    @FindBy(xpath="//input[@id='ToTag' and @class='keyValue span span24 required arabicChars ui-autocomplete-input']")
    public static WebElement toInput;
    @FindBy(id="ui-id-1")
    public static WebElement originOptions;
    
    @FindBy(id="ui-id-2")
    public static WebElement destinationOptions;
    
    @FindBy(xpath="//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")
    public static WebElement datePickerEle;
    
    @FindBy(id="SearchBtn")
    public static WebElement searchBtn;
    
    @FindBy(className="searchSummary")
    public static WebElement searchSummary;
    
	public WebDriver driver;
	public ExtentTest logger; 
	
	public FlightBooking(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);

	}
	public void flightBooking(Object[][] bookingDetails,Configurations testConfig, CommonFunctionsLib common) {
		String from=bookingDetails[0][0].toString();
		String to=bookingDetails[0][1].toString();
		onewayRadiobtn.click();
		common.sendingKeys(fromTag, from, driver);
		common.waitForPageLoaded(driver, logger);
		common.checkPresenceOfElement(fromInput, driver);
		originOptions.click();
		//common.waitForPageLoaded(driver, logger);
		common.sendingKeys(toTag, to, driver);
		common.checkPresenceOfElement(destinationOptions, driver);

    //select the first item from the destination auto complete list

    destinationOptions.click();
    datePickerEle.click();
    searchBtn.click();

    common.waitForPageLoaded(driver, logger);
    //verify that result appears for the provided journey search

	}
	
	public void isResultAppeared(Configurations testConfig, CommonFunctionsLib common) {
		if(common.checkPresenceOfElement(searchSummary, driver));
		common.softAssert("Verify the search result"," search summary should be present","Search summary is displayed", true, logger);
	}
}
