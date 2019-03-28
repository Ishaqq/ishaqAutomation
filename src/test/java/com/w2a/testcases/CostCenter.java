package com.w2a.testcases;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class CostCenter extends TestBase {

	private Thread thread;

	@Test(dataProvider = "getData")
	public void costCenter(String costCenterName, String costCenterHead, String creationDate, String status, String description, String runmode) throws InterruptedException, IOException, ParseException {

		if (!TestUtil.isTestRunnable("costCenter", excel)) {
			throw new SkipException("Skipping the test " + "costCenter" + " as the Run mode is No ");
		}

		if (!"http://192.168.1.32:8080/HoneycombWeb/#/setup/dimension/costcenter".equals(driver.getCurrentUrl())) {
			click("Setup_XPATH");
			click("CostCenter_linkText");
		}

		if (!runmode.equals("Y")) {
			throw new SkipException("Skipping the test case for selected entry by setting run mode NO");
		}

		thread.sleep(3000);
		click("SetupAddCostCenter_CSS");
		click("AddCostCenterCode_CSS");
		type("SetupCostCenterName_CSS", costCenterName);
		type("SetupCostCenterHead_CSS", costCenterHead);

		click("costCenterCreationDate_CSS");
		// type("ProjectCreationDate_CSS","2019-01-04");		
		selectCalDate(creationDate);

		int status1 = Integer.parseInt(status);
		dropDown("CostCenterStatus_CSS", status1);
		type("CostCenterDescription_CSS", description);

		// Capture Screenshot
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");
		// Capture Screenshot

		click("CostCenterSave_CSS");
		click("CostCenterClose_CSS");
		Thread.sleep(3000);
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "costCenter";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}
}
