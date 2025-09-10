package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility {
	
	private static final By SIGN_IN_LOCATOR = By.id("SubmitLogin");
	private static final By EMAIL_ADDRRESS_LOCATOR =By.id("email");
	private static final By PASSWORD_LOCATOR = By.id("passwd");
	private static final By ERROR_MESSAGE_LOCATOR =  By.xpath("//div[contains(@class,\"alert-danger\")]/ol/li");
;

	public LoginPage(WebDriver driver) {
		super(driver);
	}
 
	public MyAccountPage doLoginWith( String emailAddress , String password) {
	  enterTest(EMAIL_ADDRRESS_LOCATOR , emailAddress);
	  enterTest(PASSWORD_LOCATOR , password);
	  onToClick(SIGN_IN_LOCATOR);
	  return new MyAccountPage(getDriver());

	}
	
	
    public LoginPage doLoginWithInvalidCredentials(String emailAddress, String password) {
    	enterTest(EMAIL_ADDRRESS_LOCATOR, emailAddress);
    	enterTest(PASSWORD_LOCATOR, password);
    	onToClick(SIGN_IN_LOCATOR);
            LoginPage loginPage = new LoginPage(getDriver());
            return loginPage;
    }
    public String getErrorMessage() {
            return getVisibleText(ERROR_MESSAGE_LOCATOR);
    }
}
