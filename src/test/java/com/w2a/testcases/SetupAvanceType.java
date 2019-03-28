package com.w2a.testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

import javafx.scene.control.ComboBox;

public class SetupAvanceType extends TestBase {

	@Test(dataProvider = "getData")
	public void setupAvanceType(String typeName, String accountCode, String subsidieryType, String nature,
			String status, String description, String runmode) throws InterruptedException, IOException {
		
		 if(!TestUtil.isTestRunnable("setupAvanceType", excel)) {
			 throw new SkipException("Skipping the test "+"setupAvanceType"+" as the Run mode is No ");
		 }

		// verifyEquals("ABC", "XYZ");
		
		if (!"http://192.168.1.32:8080/HoneycombWeb/#/setup/advancetype".equals(driver.getCurrentUrl())) {
			Actions builder = new Actions(driver);
//			WebElement bankMainMenue = driver.findElement(By.linkText("Setup"));
//			builder.moveToElement(bankMainMenue).build().perform();
			click("Setup_XPATH");
			click("setupAdvanceType_linkText");
		}
		
		if(!runmode.equals("Y")) {
			throw new SkipException("Skipping the test case for selected entry by setting run mode NO");
		}
		

		click("setupAdvanceTypeAdd_CSS");
		int subsidieryType1 = Integer.parseInt(subsidieryType);
		int nature1 = Integer.parseInt(nature);
		int status1 = Integer.parseInt(status);
		type("advanceTypeName_CSS", typeName);
		comboSelect("advanceAccountCode_CSS", accountCode);
		
		// Capture Screenshot
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+ ">Screenshot</a>");
		// Capture Screenshot
		
		
		dropDown("AdvanceTypeSubsdType_CSS", subsidieryType1);
		dropDown("AdvanceTypeNature_CSS", nature1);
		dropDown("SettupAdvanceTypeStatus_CSS", status1);
		type("AdvanceTypeDesc_CSS", description);
		click("AdvanceTypeSave_CSS");
		Thread.sleep(3000);
		//click("AdvanceTypeClose_CSS");
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "SetupAvanceType";
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