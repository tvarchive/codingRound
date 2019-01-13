package codingRoundUtil;

import java.util.concurrent.TimeUnit;

import codingRoundBase.BaseClass;

public class TimeUtil extends BaseClass {
	 public static void waitFor(int durationInMilliSeconds) {
	        try {
	            
	        	driver.manage().timeouts().implicitlyWait(durationInMilliSeconds, TimeUnit.MILLISECONDS);
	        	driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.MILLISECONDS);//Thread.sleep(durationInMilliSeconds);
	        } catch (Exception e) {
	            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	        }
	    }

}
