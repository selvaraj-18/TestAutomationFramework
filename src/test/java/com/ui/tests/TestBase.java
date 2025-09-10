package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.ui.pages.HomePage;

import com.constants.Browser;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	protected HomePage homePage;
	private boolean isLambdaTest;

	@BeforeMethod(description = "load the homepage of the browser ")
	@Parameters({ "browser", "isLambdaTest", "isHeadless" })

	public void setup(@Optional("chrome") String browser, @Optional("false") boolean isLambdaTest,
			@Optional("true") boolean isHeadless, ITestResult result) {
		WebDriver lambdaDriver;
		this.isLambdaTest = isLambdaTest;
		if (isLambdaTest) {
			lambdaDriver = LambdaTestUtility.initializeLambaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);

		} else {
			logger.info("loading the homepage of the browser"); // Running the test on local machine!!!
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);

		}
	}

	public BrowserUtility getInstance() {

		return homePage;
	}

	@AfterMethod(description = "Teardown the instance")
	public void tearDown() {
		if (isLambdaTest) {
			LambdaTestUtility.quitSession();

		}

	}
}