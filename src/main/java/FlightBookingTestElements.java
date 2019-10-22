import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightBookingTestElements {
	WebDriver driver;
	By oneWay = By.id("OneWay");
	By source = By.id("FromTag");
	By destination = By.name("destination");
	By sourceOptions = By.id("ui-id-1");
	By destOptions = By.id("ui-id-2");
	By liTag = By.tagName("li");
	By searchBt = By.id("SearchBtn");
	By seachSummary = By.className("searchSummary");
	By calendar = By.xpath("//*[@id='ui-datepicker-div']");

	public FlightBookingTestElements(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement oneWayElement()
	{
		return driver.findElement(oneWay);
	}
	public WebElement srcElement()
	{
		return driver.findElement(source);
	}
	public WebElement destElement()
	{
		return driver.findElement(destination);
	}
	public List<WebElement> srcOptions()
	{
		return driver.findElement(sourceOptions).findElements(liTag);
	}
	public List<WebElement> destOptions()
	{
		return driver.findElement(destOptions).findElements(liTag);
	}
	public WebElement searchElement()
	{
		return driver.findElement(searchBt);
	}
	public WebElement searchSumElement()
	{
		return driver.findElement(seachSummary);
	}
	public void selectDate(String year, String monthName, String day) throws ParseException
	{
		driver.findElement(calendar).click();
		
		String currentYear= driver.findElement(By.xpath("//div[@class='monthBlock first']//div[@class='title']/span[@class='ui-datepicker-year']")).getText();
//		System.out.println(currentYear);
		if(!currentYear.equals(year))
		{
			do{
				driver.findElement(By.xpath("//a[@class='nextMonth ']")).click();
			}while(!driver.findElement(By.xpath("//div[@class='monthBlock first']//div[@class='title']/span[@class='ui-datepicker-year']")).getText().equals(year));
			
		}
		
		String currentMonth= driver.findElement(By.xpath("//div[@class='monthBlock first']//div[@class='title']/span[@class='ui-datepicker-month']")).getText();
//		System.out.println("mont;"+currentMonth);
		if(!currentMonth.equalsIgnoreCase(monthName))
		{
			do{
				driver.findElement(By.xpath("//a[@class='nextMonth ']")).click();
			}while(!driver.findElement(By.xpath("//div[@class='monthBlock first']//div[@class='title']/span[@class='ui-datepicker-month']")).getText().trim().equalsIgnoreCase(monthName));
			
		}
		
		int javaMonthInt= getMonthJavaInt(monthName);
		
		
		List<WebElement> allDateOfDesiredMonth= driver.findElements(By.xpath("//div[@class='monthBlock first']/table/tbody[1]//td[(contains(@class,'undefined') and @data-month='"+javaMonthInt+"')]"));
		for(WebElement d:allDateOfDesiredMonth )
		{
			
			if(d.getText().trim().equals(day))
			{
				System.out.println("date:"+d.getAttribute("data-month"));
				System.out.println(d.findElement(By.className("ui-state-default")).getText());
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", d);
				break;
			}
		}		
		
	}
	
	public int getMonthJavaInt(String monthName) throws ParseException 
	{

		Date date = new SimpleDateFormat("MMMM").parse(monthName);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

}
