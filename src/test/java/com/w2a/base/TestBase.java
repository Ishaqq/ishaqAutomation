package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("Object Repository loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}

	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}

		else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}

		else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		if (locator.endsWith("_linkText")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on:" + locator);
	}

	public void hover(String locator) {
		if (locator.endsWith("_linkText")) {

			Actions builder = new Actions(driver);
			WebElement element = driver.findElement(By.linkText(locator));
			builder.moveToElement(element).build().perform();
		}

		test.log(LogStatus.INFO, "Mouse hover on:" + locator);
	}

	public void dropDown(String locator, int index) {
		if (locator.endsWith("_CSS")) {
//			System.out.println(locator);
//			System.out.println(index);
			WebElement mySelectElement = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			Select dropdown = new Select(mySelectElement);
			dropdown.selectByIndex(index);

		}

		test.log(LogStatus.INFO, "The Selected dropdown is :" + locator + "the selected dorpdown is " + index);
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}

		else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}

		else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(LogStatus.INFO, "Typing in : " + locator + "Entered value is :" + value);
	}

	public void comboSelect(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			click(locator);
			type(locator, value);

			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.DOWN).build().perform();// press down arrow key
			actions = new Actions(driver);
			actions.sendKeys(Keys.ENTER).build().perform();// press enter
		}

		test.log(LogStatus.INFO, "Typing in : " + locator + "Selected Value having prefix :" + value);
	}

	////////// handling clander ///
	public void selectCalDate(String locator) throws InterruptedException {
		String[] dateParts = locator.split("-");
		String day = dateParts[2];
		int monthNumber = Integer.parseInt(dateParts[1]);
		String month;
		String year = dateParts[0];
      System.out.println(locator);
		switch (monthNumber) {
		case 01:
			month = "January";

			break;
		case 02:
			month = "February";
			break;
		case 03:
			month = "March";
			break;
		case 04:
			month = "April";
			break;
		case 05:
			month = "May";
			break;
		case 06:
			month = "June";
			break;
		case 07:
			month = "July";
			break;
		case 8:
			month = "August";
			break;
		case 9:
			month = "September";
			break;
		case 10:
			month = "October";
			break;
		case 11:
			month = "November";
			break;
		case 12:
			month = "December";
			break;
		default:
			month = "Invalid month";
			break;
		}
		click("CalandarYear_CSS");
		System.out.println(year);
		WebElement yearWidget = driver.findElement(By.cssSelector("bs-calendar-layout > div.bs-datepicker-body > table > tbody"));
		List<WebElement> columns = yearWidget.findElements(By.tagName("span"));
		for (int j = 0; j < columns.size(); j++) {
			if (columns.get(j).getText().equals(year)) {
				columns.get(j).click();
				Thread.sleep(2000);
				break;
			}
		}
		Thread.sleep(300);
		WebElement monthWidget = driver.findElement(By.cssSelector("bs-calendar-layout > div.bs-datepicker-body > table > tbody"));
		List<WebElement> monthCol = monthWidget.findElements(By.tagName("span"));
		System.out.println(month);
		for (int k = 0; k < monthCol.size(); k++) {
			if (monthCol.get(k).getText().equals(month)) {
				monthCol.get(k).click();
				Thread.sleep(2000);
				break;
			}
		}
		Thread.sleep(300);
		WebElement dayWidget = driver.findElement(By.cssSelector("bs-calendar-layout > div.bs-datepicker-body > table > tbody"));
		List<WebElement> dayCol = dayWidget.findElements(By.tagName("td"));
		System.out.println(day);
		for (int z = 0; z < dayCol.size(); z++) {
			if (!dayCol.get(z).findElement(By.tagName("span")).getAttribute("class").equals("is-other-month")) {
				if (dayCol.get(z).getText().equals(day)) {
					dayCol.get(z).click();
					Thread.sleep(2000);
					break;

				}
			}
		}
		test.log(LogStatus.INFO, "Slected Date from Calender in : " + locator);

	}

	//// handling calender

	public static void verifyEquals(String expected, String actual) throws IOException {

		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src=" + TestUtil.screenshotName
					+ " height=500 width=500></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			test.log(LogStatus.FAIL, "Varification failed with exeption" + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		}
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}

	}
}
