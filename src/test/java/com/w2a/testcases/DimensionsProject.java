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
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class DimensionsProject extends TestBase {

	private Thread thread;

	// String categoryName, String receivableAccount, String advanceAccount, String
	// description,String runmode
	@Test (dataProvider = "getData")
	public void dimensionsProject(String projectName, String projectManager, String creationDate, String startDate, String endDate, String status,String description, String runmode) throws InterruptedException, IOException, ParseException {

		if (!TestUtil.isTestRunnable("dimensionsProject", excel)) {
			throw new SkipException("Skipping the test " + "dimensionsProject" + " as the Run mode is No ");
		}

		if (!"http://192.168.1.32:8080/HoneycombWeb/#/setup/dimension/project".equals(driver.getCurrentUrl())) {
			Actions builder = new Actions(driver);
//			WebElement bankMainMenue = driver.findElement(By.linkText("Setup"));
//			builder.moveToElement(bankMainMenue).build().perform();
			click("Setup_XPATH");
			click("Project_linkText");
			thread.sleep(3030);
		}

		if(!runmode.equals("Y")) {
			throw new SkipException("Skipping the test case for selected entry by setting run mode NO");
		}


		click("SetupAddProject_CSS");
		click("AddProjectCode_CSS");
		type("SetupProjectName_CSS", projectName);
		type("SetupProjectManager_CSS", projectManager);

		click("ProjectCreationDate_CSS");
		// type("ProjectCreationDate_CSS","2019-01-04");
		
		selectCalDate(creationDate);

		click("ProjectStartDate_CSS");
		selectCalDate(startDate);

		click("ProjectEndDate_CSS");
		selectCalDate(endDate);

		dropDown("ProjectStatus_CSS", 0);
		type("ProjectDescription_CSS", description);
		click("ProjectSave_CSS");
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "DimensionsProject";
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
