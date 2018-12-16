package Utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sun.javafx.PlatformUtil;

public class CommonFunctions {
	WebDriver driver;

	public CommonFunctions(WebDriver driver2) {
		driver = driver2;
	}
	public void waitFor(int durationInMilliSeconds) {
        try {
//            Thread.sleep(durationInMilliSeconds);
        	driver.manage().timeouts().implicitlyWait(durationInMilliSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
        
    }
	public By getLocator(String Element) {
		By Value = null;
		String[] element = Element.split("\\|");
		String IDType = element[0];
		String elementvalue = element[1];
		if (IDType.contains("id")) {
			Value = By.id(elementvalue);
		} else if (IDType.contains("xpath")) {
			Value = By.xpath(elementvalue);
		} else if (IDType.contains("linkText")) {
			Value = By.linkText(elementvalue);
		} else if (IDType.contains("cssSelector")) {
			Value = By.cssSelector(elementvalue);
		} else if (IDType.contains("className")) {
			Value = By.className(elementvalue);
		} else if (IDType.contains("name")) {
			Value = By.name(elementvalue);
		} else if (IDType.contains("partialLinkText")) {
			Value = By.partialLinkText(elementvalue);
		} else if (IDType.contains("tagName")) {
			Value = By.tagName(elementvalue);
		}
		return Value;

	}

	public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	public void input(String Element, String Value) {
		try {
			driver.findElement(getLocator(Element)).clear();
			driver.findElement(getLocator(Element)).sendKeys(Value);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void click(String Element) {
		try {
			driver.findElement(getLocator(Element)).click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void SelectListOption(String Element, String OptionName) {
		try {
			List<WebElement> values = driver.findElements(getLocator(Element));
			for (int i = 0; i < values.size(); i++) {
				String option = values.get(i).getText();
				if (option.contains(OptionName)) {
					values.get(i).click();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public String getAttributeofElement(WebDriver driver,WebElement element, String attributename)
	{
		String strAttributeValue="";
		try
		{
			if(attributename.equalsIgnoreCase("getText"))
			{
				strAttributeValue=element.getText().trim();
			}			
			else
			{
				strAttributeValue=element.getAttribute(attributename).trim();
			}			
			if(strAttributeValue.isEmpty())
			{
				strAttributeValue="";
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return strAttributeValue;
	}
}
