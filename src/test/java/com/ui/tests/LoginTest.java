package com.ui.tests;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners({ com.ui.listeners.TestListeners.class })
public class LoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	@Test(description = "Verifies with valid user is able to login into the application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataProvider.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
	public void loginTest(User user) {
		assertEquals(homePage.goTOLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Selva raj");

	}

	@Test(description = "Verifies with valid user is able to login into the application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataProvider.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
	public void loginCSVTest(User user) {
		assertEquals(homePage.goTOLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Selva raj1");

	}

//	@Test(description = "Verifies with valid user is able to login into the application", groups = { "e2e", "sanity" } , dataProviderClass = com.ui.dataProvider.LoginDataProvider.class , dataProvider = "LoginTestExcelDataProvider" ,
//			retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
//	public void loginExcelTest(User user) {
//		assertEquals(homePage.goTOLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(), "Selva raj");
//	}

}
