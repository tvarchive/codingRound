package codingRound.commonLib;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CommonFunctionsLib {

		public void waitForPageLoaded(WebDriver driver, ExtentTest logger) {
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};
			try {
				Thread.sleep(1000);
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(expectation);
			} catch (Throwable error) {
				logger.log(LogStatus.FAIL, "Timeout waiting for Page Load Request to complete.");
			}
		}

		public Object[][] readExcel(String filepath, String sheetname) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(filepath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			HSSFWorkbook wb = null;
			try {
				wb = new HSSFWorkbook(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HSSFSheet sheet = wb.getSheet(sheetname);
			int rowcount = sheet.getLastRowNum();
			HSSFRow row = sheet.getRow(0);
			int colcount = row.getLastCellNum();

			Object data[][] = new Object[rowcount][colcount];
			for (int i = 1; i <= rowcount; i++) {
				HSSFRow row_individual = sheet.getRow(i);
				for (int j = 0; j <= colcount - 1; j++) {
					data[i - 1][j] = row_individual.getCell(j);
				}
			}
			return data;
		}

		public void softAssert(String stepname, String expected, String actual, boolean result, ExtentTest logger) {
			if (Boolean.TRUE.equals(result)) {
				logger.log(LogStatus.PASS, stepname,
						"Expected value : " + expected + "</br>is equal to</br>actual value : " + actual);
			} else {
				logger.log(LogStatus.ERROR, stepname,
						"Expected value : " + expected + "</br>is not equal to</br>actual value : " + actual);
			}
		}

		public void hardAssert(String stepname, String expected, String actual, boolean result, ExtentTest logger) {
			if (Boolean.TRUE.equals(result)) {
				logger.log(LogStatus.PASS, stepname,
						"Expected value : " + expected + "</br>is equal to</br>actual value : " + actual);
			} else {
				logger.log(LogStatus.FAIL, stepname,
						"Expected value : " + expected + "</br>is not equal to</br>actual value : " + actual);
				Assert.fail();
			}
		}

		public boolean compareString(String expected, String actual, boolean ignorecase) {
			if ((ignorecase && expected.trim().equalsIgnoreCase(actual.trim())) || expected.trim().equals(actual.trim())) {
				return true;
			} else {
				return false;
			}
		}

		public void sendingKeys(WebElement we, String text, WebDriver driver) {
			if (checkPresenceOfElement(we, driver)) {
				we.click();
				we.clear();
				we.sendKeys(text);
			}
		}

		public void scrollToWebElement(WebElement we, WebDriver driver) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
		}

		public void scrollDown(WebDriver driver) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		}

		public void scrollUp(WebDriver driver) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,-document.body.scrollHeight);");
		}

		public void MouseOver(WebElement we, WebDriver driver) {
			Actions actObj = new Actions(driver);
			actObj.moveToElement(we).build().perform();
		}

		public boolean checkPresenceOfElement(WebElement we, WebDriver driver) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(we));
			if (we.isDisplayed() && we.isEnabled()) {
				return true;
			}
			return false;
		}

		public void waitToBeClickable(WebElement we, WebDriver driver) {
			WebDriverWait wait = new WebDriverWait(driver, 35);
			wait.until(ExpectedConditions.elementToBeClickable(we));
		}

		public List<String> returnHandleList(WebDriver driver) {
			Set<String> handles = driver.getWindowHandles();
			List<String> listHandle = new ArrayList<String>(handles);
			return listHandle;
		}

		public void selectFromDropdown(WebElement we, String text) {
			Select oSelect = new Select(we);
			oSelect.selectByVisibleText(text);
		}

		public long getCurrentDateAndTime() {
			long epoch = System.currentTimeMillis() / 1000;
			return epoch;
		}

		public void uploadFile(WebElement we, String filename, WebDriver driver) throws Exception {
			StringSelection s = new StringSelection(filename);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
			Robot robot = new Robot();

			robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
			robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
			robot.keyPress(java.awt.event.KeyEvent.VK_V);
			robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
		}

		public Boolean isImageDisplayed(WebElement ImageFile, WebDriver driver) {
			Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					ImageFile);
			return ImagePresent;

		}

		public void performDragAndDrop(WebElement source, WebElement destination, WebDriver driver) {
			Actions act = new Actions(driver);
			act.dragAndDrop(source, destination).build().perform();
		}

		public void getAllFiles(File dir, List<File> fileList) {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList.add(file);
				if (file.isDirectory()) {
					getAllFiles(file, fileList);
				}
			}
		}

		public String writeZipFile(File directoryToZip, List<File> fileList) {

			String zipFile = directoryToZip + ".zip";
			try {

				FileOutputStream fos = new FileOutputStream(zipFile);
				ZipOutputStream zos = new ZipOutputStream(fos);

				for (File file : fileList) {
					if (!file.isDirectory()) { // we only zip files, not directories
						addToZip(directoryToZip, file, zos);
					}
				}
				zos.close();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return zipFile;
		}

		public void addToZip(File directoryToZip, File file, ZipOutputStream zos)
				throws FileNotFoundException, IOException {

			FileInputStream fis = new FileInputStream(file);
			String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
					file.getCanonicalPath().length());
			ZipEntry zipEntry = new ZipEntry(zipFilePath);
			zos.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zos.write(bytes, 0, length);
			}
			zos.closeEntry();
			fis.close();
		}

		public LogEntries getConsoleErrors(WebDriver driver) throws InterruptedException {
			Logs logs = driver.manage().logs();
			Thread.sleep(5000);
			return logs.get(LogType.BROWSER);
		}

		public void categorizeConsoleErrors(LogEntries logEntries, ExtentTest logger) {
			List<String> severem = new ArrayList<String>();
			List<String> warningm = new ArrayList<String>();
			for (LogEntry logEntry : logEntries) {
				if (logEntry.getLevel().getName().equalsIgnoreCase("SEVERE")) {
					severem.add(logEntry.getMessage());
				} else if (logEntry.getLevel().getName().equalsIgnoreCase("WARNING")) {
					warningm.add(logEntry.getMessage());
				}
			}

			if (severem.size() > 0) {
				String lsts = new String();
				for (int i = 0; i < severem.size(); i++) {
					lsts = lsts + (i + 1) + ".  " + severem.get(i).toString() + "</br>";
				}
				logger.log(LogStatus.ERROR, "Console Errors - Severe", lsts);
			} else {
				logger.log(LogStatus.PASS, "Console Errors - Severe", "No error found");
			}
			if (warningm.size() > 0) {
				String lsts = new String();
				for (int i = 0; i < warningm.size(); i++) {
					lsts = lsts + (i + 1) + ".  " + warningm.get(i).toString() + "</br>";
				}
				logger.log(LogStatus.ERROR, "Console Errors - Warning", lsts);
			} else {
				logger.log(LogStatus.PASS, "Console Errors - Warning", "No error found");
			}

		}

	

}
