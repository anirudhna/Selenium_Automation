package com.uta.lufthansa.main.selenium.tests;

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

public class AdvanceBookingOptionsTest {

	public static WebDriver driver;

	ObjectRepostory page = new ObjectRepostory(driver);

	@SuppressWarnings("deprecation")
	@BeforeClass
	public void login() throws Exception {
		Log.info("starting chrome browser");
		DriverSetup.setup(InputConstants.BROWSER);
		driver.get("http://www.lufthansa.com/online/portal/lh/in/homepage");
	}

	@SuppressWarnings("deprecation")
	@AfterClass
	public void quit() {
		Log.info("closing the chrome driver");
		DriverSetup.quitDriver();
	}

	@Test(priority = 1, description = "verify home page", groups = { "sanity" })
	public void validateHomeURLTest() {
		boolean isexists = driver.getTitle().trim().contains("Online flight tickets worldwide - Lufthansa ® India");
		if (isexists == true) {
			System.out.println("page logged  is  correct");
		} else {
			System.out.println("sorry the page logged in is not correct");
		}
		Assert.assertTrue(isexists);
	}

	@Test(priority = 2, description = "Select Depart from", groups = { "sanity" })
	public void departureFromTest() {
		driver.findElement(By.xpath(".//*[@id='f-AirportAtlasOrigin']")).click();
		DriverSetup.select_value("html/body/div[21]/div/div/form/div[2]/div/div[2]/div[2]/label", "Algeria", driver);
		DriverSetup.select_value("html/body/div[21]/div/div/form/div[2]/div/div[4]/div[2]/label", "Algiers", driver);
		DriverSetup.select_value("html/body/div[21]/div/div/form/div[2]/div/div[6]/div[2]/label", "Algiers (ALG)",
				driver);
	}

	@Test(priority = 3, description = "Select Depart to", groups = { "sanity" })
	public void departureToTest() {
		driver.findElement(By.xpath(".//*[@id='f-AirportAtlasOrigin']")).click();
		DriverSetup.select_value("html/body/div[21]/div/div/form/div[2]/div/div[2]/div[2]/label", "Austria", driver);
		DriverSetup.select_value("html/body/div[21]/div/div/form/div[2]/div/div[4]/div[2]/label", "Graz", driver);
		DriverSetup.select_value("html/body/div[21]/div/div/form/div[2]/div/div[6]/div[2]/label",
				" Graz - Thalerhof (GRZ)", driver);
	}

	@Test(priority = 4, description = "select calander depart on and depart to dates", groups = { "sanity" })
	public void calenderSelectionTest() {
		page.Cal.click();
		List<WebElement> co = driver.findElements(
				By.xpath(".//*[@id='kosa-cal-modal-1']/div/div/div[2]/div[1]/div[2]/div[2]/table/tbody/tr/td/button"));
		DateFormat dateFormat2 = new SimpleDateFormat("dd");
		Date date2 = new Date();
		String today = dateFormat2.format(date2);
		// comparing the text of cell with today's date and clicking it.
		for (WebElement cell : co) {
			if (cell.getText().equals(date2))
				System.out.println(cell.getText());
			cell.click();
			break;
		}

		List<WebElement> con = driver.findElements(
				By.xpath(".//*[@id='kosa-cal-modal-2']/div/div/div[2]/div[1]/div[2]/div[2]/table/tbody/tr/td/button"));
		// comparing the text of cell with future date and clicking it.
		for (WebElement cell : con) {
			if (cell.getText().equals(InputConstants.returndate))
				System.out.println(cell.getText());
			cell.click();
			break;
		}

	}

	@Test(priority = 5, description = "select travellers", groups = { "sanity" })
	public void travellerSelectionTest() {
		int index = 0;
		boolean checkforchildren = false;

		Select Adult = new Select(page.adult);
		Adult.selectByIndex(1);

		if (index == 0) {
			Select child = new Select(page.child);
			child.selectByIndex(1);
			index = 1;
		}

		if (index == 1) {
			Select infants = new Select(page.infant);
			infants.selectByIndex(1);
			checkforchildren = true;
		}
		Assert.assertTrue(checkforchildren);
	}

	@Test(priority = 6, description = "select class and airlines", groups = { "sanity" })
	public void airlineClassSelectionTest() {
		int index = 0;
		boolean classcheck = false;
		if (index == 0) {
			Select bookclass = new Select(page.bookingClass);
			bookclass.selectByIndex(1);
			index = 1;
		}
		if (index == 1) {
			Select Airline = new Select(page.airline);
			Airline.selectByIndex(1);
			classcheck = true;
		}
		Assert.assertTrue(classcheck);
	}

	@Test(priority = 7, description = "select class and airlines", groups = { "sanity" })
	public void advanceSearchTest() {
		page.searchadvanced.click();
		Assert.assertTrue(driver.getPageSource().contains("Before you can continue, please check the following:"));
	}

}
