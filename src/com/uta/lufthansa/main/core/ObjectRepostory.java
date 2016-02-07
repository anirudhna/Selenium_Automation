package com.uta.lufthansa.main.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ObjectRepostory {
	WebDriver driver;

	public ObjectRepostory(WebDriver d) {
		PageFactory.initElements(driver, this);
	}

	///////////////// HOME PAGE ////////////////

	@FindBy(name = "originName1")
	public WebElement origin;

	@FindBy(name = "destinationName1")
	public WebElement destination;

	@FindBy(xpath = ".//*[@id='flightmanagerFlightsFormAdults']")
	public WebElement numberofAdults;

	@FindBy(xpath = ".//*[@id='flightmanagerFlightsFormOutboundDateDisplay']")
	public WebElement calander;

	@FindBy(xpath = ".//*[@id='flightmanagerFlightsFormAccessCode']")
	public WebElement Accesscode;

	@FindBy(xpath = ".//*[@id='flightmanagerFlightsForm']/div[8]/div/a")
	public WebElement code;

	@FindBy(xpath = ".//*[@id='flightmanagerFlightsForm']/div[10]/div/button")
	public WebElement clicksearch;

	@FindBy(xpath = ".//*[@id='flightmanagerFlightsFormChildren']")
	public WebElement children;

	@FindBy(xpath = ".//*[@id='flightmanagerFlightsFormInfants']")
	public WebElement Infants;

	//////////////// MORE OPTIONS //////////////

	@FindBy(xpath = "html/body/div[21]/div/div/form/div[2]/div/div[2]/div[2]/label")
	public WebElement Country;

	@FindBy(xpath = ".//*[@id='fdepdateDisplay']")
	public WebElement Cal;

	@FindBy(xpath = ".//*[@id='adults']")
	public WebElement adult;

	@FindBy(xpath = ".//*[@id='children']")
	public WebElement child;

	@FindBy(xpath = ".//*[@id='infants']")
	public WebElement infant;

	@FindBy(xpath = ".//*[@id='bookingClass']")
	public WebElement bookingClass;

	@FindBy(xpath = ".//*[@id='airline']")
	public WebElement airline;

	@FindBy(xpath = ".//*[@id='ADVANCED_SEARCH_FLIGHTS_FIND_FLIGHTS_0']")
	public WebElement searchadvanced;
}
