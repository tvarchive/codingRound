package com.testvagrant.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelFileManager {
	private static Map<String, Map<String, String>> excelPoolData = new LinkedHashMap<>();
	private static CustomLogger logger = new CustomLogger();

	/**
	 * Getting test data from excel configurable file based on Test case Name
	 * 
	 * @param testcaseName : Name of the test case
	 * @return : Map object of data read from excel
	 */
	public static Map<String, String> getTestCaseDate(String testcaseName) {
		Fillo fillo = new Fillo();
		Connection connection;
		if (excelPoolData.containsKey(testcaseName)) {
			return excelPoolData.get(testcaseName);
		} else {
			Map<String, String> excelData = new LinkedHashMap<>();
			try {
				String path = System.getProperty("user.dir");
				connection = fillo.getConnection(
						path + PropertFileManager.getPropertyFromFile("excelFilePath", MiscConstants.PROP_MISC));
				String strQuery = "Select * from TestData where TestCaseName = '" + testcaseName + "'";
				Recordset recordset = connection.executeQuery(strQuery);
				recordset.next();
				for (String eachName : recordset.getFieldNames()) {
					excelData.put(eachName, recordset.getField(eachName));
				}
			} catch (FilloException e) {
				logger.logException(e);
			}
			excelPoolData.put(testcaseName, excelData);
			return excelData;
		}
	}
}
