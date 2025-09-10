package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReportUtility;
import com.utility.LoggerUtility;

public class TestListeners implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtentReportUtility.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " + "Passed");
		ExtentReportUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "Passed");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "failed");
		logger.error(result.getThrowable().getMessage());
		ExtentReportUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "failed");
		ExtentReportUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());
		Object testclass = result.getInstance();
		BrowserUtility browserUtility = ((TestBase) testclass).getInstance();
		logger.info("Capturing Screeshot for the failed tests");
		String screenshotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());
		logger.info("Attaching the Screeshot to the HTML file");
		ExtentReportUtility.getTest().addScreenCaptureFromPath(screenshotPath);

	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "Test Skipped");
		ExtentReportUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "Skipped");
	}

	public void onStart(ITestContext context) {
		logger.info("Test Suit Started");
		ExtentReportUtility.setupSparkReporter("reports.html");

	}

	public void onFinish(ITestContext context) {
		logger.info("Test Suit finished");
		ExtentReportUtility.flushReport();
	}

}
