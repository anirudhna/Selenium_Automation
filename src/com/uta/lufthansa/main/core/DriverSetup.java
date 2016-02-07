package com.uta.lufthansa.main.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

public class DriverSetup {
	/**
	 * For Core Selenium2 functionality
	 */
	protected static WebDriver driver = null;
	protected WebDriverWait wait;
	/**
	 * Standard log4j logger.
	 */
	protected Logger log = Logger.getLogger(getClass().getSimpleName());

	/**
	 * Instantiating the driver path
	 */
	private static final String IE_FILE_PATH = "";
	private static final String CHROME_FILE_PATH = "C:\\Users\\HOME\\Downloads\\chromedriver_win32\\chromedriver.exe";

	/**
	 * Returning the driver based on the browser
	 * 
	 * @param browser
	 * @return
	 * @throws Exception
	 */

	public static void setup(String browser) throws Exception {

		if (browser.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		}

		else if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "IE_FILE_PATH");
			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", IE_FILE_PATH);
			driver = new InternetExplorerDriver();

		}

		else {

			throw new Exception("Browser is not correct");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * Implict wait for elements
	 */

	public static void dynamicWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
	}

	/**
	 * explict wait for elements
	 */

	public static boolean waitUntilExists(String xpath, WebDriver driver) {
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		return true;
	}

	public static boolean logTitleMessage(String message1) {

		boolean isexists = driver.getTitle().trim().contains(message1);

		if (isexists == true) {
			System.out.println("title exits");
		} else {
			System.out.println("sorry the page logged in is not correct");

		}
		return isexists;
	}

	/**
	 * Read Properties.
	 */

	public static String getProp(String property) {

		String data = "";

		try {

			FileInputStream fileInput = new FileInputStream(new File("data.properties"));
			// Create Properties object
			Properties prop = new Properties();

			prop.load(fileInput);
			data = prop.getProperty(property);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}

	/**
	 * Assert Actual and Expected Strings
	 */

	public static void assertStrings(String actual, String expected) {

		try {
			Assert.assertEquals(actual, expected);
			// log.info("Actual string: [ "+actual+" ] does match with Expected
			// string: [ "+expected+" ]");

		} catch (AssertionError e) {
			// log.error("Actual string: [ "+actual+" ] does not match with
			// Expected string: [ "+expected+" ]");
			Assert.fail();

		}

	}

	/**
	 * Capture error text
	 */

	public static void error_capture() {
		// driver.findElement(By.xpath(".//*[@id='flightmanagerFlightsForm']/div[10]/div/button")).click();
		List<WebElement> alllinks = driver.findElements(By.tagName("a"));
		{
			for (int i = 0; i < alllinks.size(); i++)

				if (alllinks.get(i).getText().contains("Please")) {
					System.out.println(alllinks.get(i).getText());
					// break;
				}
		}
	}

	/**
	 * Click on elements
	 */

	public static void selectValue(String country, String data, WebDriver driver) {

		List<WebElement> column = driver.findElements(By.xpath(country));
		// comparing the text of cell with future date and clicking it.
		for (WebElement cell : column) {

			System.out.println(cell.getText());
			if (cell.getText().contains(data)) {
				cell.click();
				break;
			}
		}

	}

	/**
	 * Quit Driver.
	 */
	public static void quitDriver() {

		driver.quit();

	}
}
