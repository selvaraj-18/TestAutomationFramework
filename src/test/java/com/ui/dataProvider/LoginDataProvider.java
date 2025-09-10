package com.ui.dataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CsvUtility;
import com.utility.ExcelUtility;

public class LoginDataProvider {

	@DataProvider(name = "LoginTestDataProvider")
	public Iterator<Object[]> LoginDataProvider() throws FileNotFoundException {
		Gson gson = new Gson();
		File testDataFile = new File(System.getProperty("user.dir") + "\\TestData\\logindata.json");
		FileReader fileReader = new FileReader(testDataFile);
		TestData data = gson.fromJson(fileReader, TestData.class);

		List<Object[]> dataToReturn = new ArrayList<Object[]>();
		for (User user : data.getData()) {
			dataToReturn.add(new Object[] { user });

		}
		return dataToReturn.iterator();

	}

	@DataProvider(name = "LoginTestCSVDataProvider")
	public Iterator<User> loginCSVProvider() {
		return CsvUtility.readCSV("logindata.csv");
	}

	@DataProvider(name = "LoginTestExcelDataProvider")
	public Iterator<User> loginExcelProvider() {
		return ExcelUtility.readExcelFile("logindata.xlsx");
	}

}
