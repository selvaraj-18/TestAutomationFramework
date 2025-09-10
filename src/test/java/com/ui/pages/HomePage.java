package com.ui.pages;

import static com.constants.Env.QA;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

public final class HomePage extends BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static final By SIGN_IN_LOCATOR = (By.linkText("Sign in"));

	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);// To Call the Parent Class constructor from the child constructor
		// goToWebsite(readProperty(QA, "URL"));
		goToWebsite(JSONUtility.readJson(QA).getUrl());

	}

	public HomePage(WebDriver driver) {
		super(driver);// To Call the Parent Class constructor from the child constructor
		goToWebsite(JSONUtility.readJson(QA).getUrl());
	}

	public LoginPage goTOLoginPage() {
		logger.info("Trying to perform to click to goto signin page");
		onToClick(SIGN_IN_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}

}
