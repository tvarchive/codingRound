package codingRound.utility;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

	private static ThreadLocal<ISuite> ACCESS = new ThreadLocal<ISuite>();

	public static ISuite getAccess() {
		return ACCESS.get();
	}

	public void onStart(ISuite suite) {
		ACCESS.set(suite);
	}

	public void onFinish(ISuite suite) {
		ACCESS.set(null);
	}
}
