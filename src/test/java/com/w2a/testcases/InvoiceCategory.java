package com.w2a.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class InvoiceCategory extends TestBase {

	@Test(dataProvider = "getData")
	public void invoiceCategory(String categoryCode, String categoryName, String subsidieryType, String status,
			String description, String runmode) throws InterruptedException {
		
		if(!TestUtil.isTestRunnable("invoiceCategory", excel)) {
			 throw new SkipException("Skipping the test "+"invoiceCategory"+" as the Run mode is No ");
		 }
		
		if (!"http://192.168.1.32:8080/HoneycombWeb/#/setup/invoicecategory".equals(driver.getCurrentUrl())) {
			Actions builder = new Actions(driver);
//			WebElement bankMainMenue = driver.findElement(By.cssSelector("Setup_CSS"));
//			builder.moveToElement(bankMainMenue).build().perform();
			click("Setup_XPATH");
			click("invoiceCategory_linkText");
		}
		
		if(!runmode.equals("Y")) {
			throw new SkipException("Skipping the test case for selected entry by setting run mode NO");
		}


		click("invoiceCategoryAdd_CSS");
		// Thread.sleep(3000);

		int subsidieryType1 = Integer.parseInt(subsidieryType);
		int status1 = Integer.parseInt(status);

		type("invoiceCategCode_CSS", categoryCode);
		type("invoiceCategName_CSS", categoryName);
		dropDown("invoiceSubsAccount_CSS", subsidieryType1);
		dropDown("invoiceCategStatus_CSS", status1);
		type("invoiceCategDisc_CSS", description);
		click("invoiceCategSave_CSS");
		click("invoiceCategClose_CSS");
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "InvoiceCategory";
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