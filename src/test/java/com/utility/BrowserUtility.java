package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver); // initialize instance variables
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching the browser" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		}

		else {
			logger.error("Invalid Browser - Choose Chrome or FireFox");
			System.err.println("Invalid Browser - Choose Chrome or FireFox");
		}
	}

	public BrowserUtility(Browser browserName) {
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
		}

		else {
			System.err.println("Invalid Browser - Choose Chrome or FireFox");
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless= old");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));

			} else {
				driver.set(new ChromeDriver());
			}
		}

		else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless= old");
				options.addArguments("--window-size=1920,1080");
				driver.set(new EdgeDriver(options));
			} else {

				driver.set(new EdgeDriver());
			}
		}

		else {
			System.err.println("Invalid Browser - Choose Chrome or FireFox");
		}
	}

	public void goToWebsite(String url) {
		logger.info("Visiting the website " + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window");
		driver.get().manage().window().maximize();

	}

	public void onToClick(By locator) {
		WebElement element = driver.get().findElement(locator);
		element.click();
	}

	public void enterTest(By locator, String enterText) {
		WebElement element = driver.get().findElement(locator);
		element.sendKeys(enterText);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding Element with the locator" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and now returning the visibile " + element.getText());
		return element.getText();
	}

	public String takeScreenshot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);

		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "//screeshots" + name + " -" + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}

	public void quit() {
		driver.get().quit();
	}
}
