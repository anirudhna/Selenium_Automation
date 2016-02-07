package com.uta.lufthansa.main.selenium.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.seleniumhq.jetty7.util.log.Log;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.uta.lufthansa.main.core.TestCaseListner;
import com.uta.lufthansa.main.core.ObjectRepostory;
import com.uta.lufthansa.main.core.DriverSetup;
import com.uta.lufthansa.main.inputs.InputConstants;

@Listeners({ TestCaseListner.class })

public class TicketBookingTest {

	public static WebDriver driver;

	ObjectRepostory page = new ObjectRepostory(driver);

	@BeforeClass
	public void login() throws Exception {
		Log.info("starting chrome browser");
		DriverSetup.setup(InputConstants.BROWSER);
		driver.get("http://www.lufthansa.com/online/portal/lh/in/homepage");
	}

	@AfterClass
	public void quit() {
		Log.info("closing the chrome driver");
		DriverSetup.quitDriver();
	}

	@Test(priority = 1, description = "verify home page", groups = { "sanity" })
	public void validateHomeURLTest() {
		boolean isexists = driver.getTitle().trim().contains("Online flight tickets worldwide - Lufthansa ® India");
		if (isexists == true) {
			System.out.println("title exits");
		} else {
			System.out.println("sorry the page logged in is not correct");
		}
		Assert.assertTrue(isexists);
	}

	@Test(priority = 2, description = "select origin, destination and number of adults", groups = { "sanity" })
	public void detailsSelectonTest() throws AWTException, InterruptedException {
		DriverSetup.dynamicWait(driver);
		page.origin.sendKeys(InputConstants.originName1);
		DriverSetup.dynamicWait(driver);
		Robot robot = new Robot();
		Thread.sleep(7000);
		robot.keyPress(KeyEvent.VK_DOWN);
		DriverSetup.dynamicWait(driver);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(4000);
		page.destination.sendKeys(InputConstants.destinationName1);
		Thread.sleep(7000);
		robot.keyPress(KeyEvent.VK_DOWN);
		DriverSetup.dynamicWait(driver);
		robot.keyPress(KeyEvent.VK_ENTER);
		Select dropdownq = new Select(page.numberofAdults);
		dropdownq.selectByIndex(1);
		String[] exp = { "1 Adult", "2 Adults", "3 Adults", "4 Adults", "5 Adults", "6 Adults" };
		Select dropdown = new Select(page.numberofAdults);

		List<WebElement> options = dropdown.getOptions();
		for (WebElement we : options) {
			boolean match = false;
			{
				System.out.println(we.getText());
				for (int i = 0; i < exp.length; i++) {
					if (we.getText().trim().equals(exp[i])) {
						System.out.println("matched");
					}
				}
			}
		}
	}

	@Test(priority = 3, description = "select to and fro dates", groups = { "sanity" })
	public void dateSelectionTest() {

		DateFormat dateFormat2 = new SimpleDateFormat("dd");
		Date date2 = new Date();
		String today = dateFormat2.format(date2);
		page.calander.click();
		driver.switchTo().frame("utag_55_iframe");
		List<WebElement> columns = driver.findElements(
				By.xpath(".//*[@id='kosa-cal-modal-1']/div/div/div[2]/div[1]/div[2]/div[2]/table/tbody/tr/td/button"));
		// comparing the text of cell with today's date and clicking it.
		for (WebElement cell : columns) {
			if (cell.getText().equals(date2))
				System.out.println(cell.getText());
			cell.click();
			break;
		}

		List<WebElement> column = driver.findElements(
				By.xpath(".//*[@id='kosa-cal-modal-2']/div/div/div[2]/div[1]/div[2]/div[2]/table/tbody/tr/td/button"));
		// comparing the text of cell with future date and clicking it.
		for (WebElement cell : column) {
			if (cell.getText().equals(InputConstants.returndate))
				System.out.println(cell.getText());
			cell.click();
			break;
		}
	}

	@Test(priority = 4, description = "enter access code", groups = { "sanity" })
	public void promotonsTest() {

		int code = 0;
		boolean access_code = false;
		String code_xpath = ".//*[@id='flightmanagerFlightsForm']/div[8]/div/a";
		if (DriverSetup.waitUntilExists(code_xpath, driver)) {
			driver.findElement(By.xpath(code_xpath)).click();
			code = 1;
		}
		if (code == 1) {
			page.Accesscode.sendKeys(InputConstants.sample_key);
			access_code = true;
		}
		Assert.assertTrue(access_code);
	}

	@Test(priority = 5, description = "Select children , infants", groups = { "sanity" })
	public void checkForChildrenTest() {

		int index = 0;
		boolean checkforchildren = false;
		String object = ".//*[@id='flightmanagerFlightsForm']/div[7]/div/a";
		if (DriverSetup.waitUntilExists(object, driver)) {
			driver.findElement(By.xpath(object)).click();
			index = 1;
		}
		if (index == 1) {
			Select child = new Select(page.children);
			child.selectByIndex(1);
			index = 2;
		}
		if (index == 2) {
			Select child = new Select(page.Infants);
			child.selectByIndex(1);
			checkforchildren = true;
		}
		Assert.assertTrue(checkforchildren);
	}

	@Test(priority = 6, description = "click on search", groups = { "sanity" })
	public void searchTest() {
		boolean Search = false;
		page.clicksearch.click();
		if (driver.getPageSource().contains("Search for flights, hotels and rental cars")) {
			System.out.println("sucessfully clicked on the Search funtionality");
			DriverSetup.error_capture();
			Search = true;
		}
		Assert.assertTrue(Search);
	}
}
