package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageElements {

    WebDriver driver;
    private static final String ONE_WAY_RADIO_BUTTON = "OneWay";
    private static final String FROM_INPUT_TEXT_FIELD = "FromTag";
    private static final String FROM_SEARCH_RESULT = "//*[@id='ui-id-1']//li";
    private static final String TO_INPUT_TEXT_FIELD = "ToTag";
    private static final String TO_SEARCH_RESULT = "//*[@id='ui-id-2']//li";
    private static final String YOUR_TRIPS = "Your trips";
    private static final String SIGN_IN = "SignIn";
    private static final String SIGN_IN_BUTTON = "signInButton";
    private static final String SIGN_IN_ERROR1 = "errors1";
    private static final String FRAME_SIGN_IN_MODAL = "//div//iframe[@name='modal_window']";
    private static final String HOTELS_LINK = "Hotels";
    private static final String TAG = "Tags";

    public static By tags()
    {
        return By.xpath(TAG);
    }
    public static By hotels_link_text()
    {
        return By.linkText(HOTELS_LINK);
    }
    public static By frame_for_sign_in_modal()
    {
        return By.xpath(FRAME_SIGN_IN_MODAL);
    }
    public static By sign_in_error1()
    {
        return By.id(SIGN_IN_ERROR1);
    }
    public static By sign_in_button()
    {
        return By.id(SIGN_IN_BUTTON);
    }
    public static By sign_in()
    {
        return By.id(SIGN_IN);
    }
    public static By your_trips()
    {
        return By.linkText(YOUR_TRIPS);
    }
    public static By one_way_radio_button()
    {
        return By.id(ONE_WAY_RADIO_BUTTON);
    }

    public static By from_input_text_field()
    {
        return By.id(FROM_INPUT_TEXT_FIELD);
    }

    public static By from_search_result()
    {
        return By.xpath(FROM_SEARCH_RESULT);
    }
    public static By to_input_text_field()
    {
        return By.id(TO_INPUT_TEXT_FIELD);
    }

    public static By to_search_result()
    {
        return By.xpath(TO_SEARCH_RESULT);
    }




}
