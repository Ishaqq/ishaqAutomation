package com.w2a.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class AddBankBranches extends TestBase {
	@Test(dataProvider = "getData")
	public void addBankBranches(String email, String branchCode, String mobile, String branchName,String phone, String contactPerson, String fax, String address) throws InterruptedException {
		if(!TestUtil.isTestRunnable("addBankBranches", excel)) {
			 throw new SkipException("Skipping the test "+"addBankBranches"+" as the Run mode is No ");
		 }
		


		if (!"http://192.168.1.32:8080/HoneycombWeb/bank/banks".equals(driver.getCurrentUrl())) {
			Actions builder = new Actions(driver);
			WebElement bankMainMenue = driver.findElement(By.linkText("Bank"));
			builder.moveToElement(bankMainMenue).build().perform();
			click("banklisting_linkText");
			
		}

		// next
		click("nextbanklisting_CSS");
		click("banklist_linkText");
		click("addBankBranch_CSS");
		type("bankBranchEmail_CSS", email);
		type("bankBranchCode_CSS", branchCode);
		type("bankBranchMObil_CSS", mobile);
		type("bankBranchName_CSS", branchName);
		type("bankBranchPhone_CSS", phone);
		type("bankBranchContactPerson_CSS", contactPerson);
		type("bankBranchFax_CSS", fax);
		type("bankBranchAddress_CSS", address);
		click("saveBankBranch_CSS");
		Thread.sleep(3000);
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "AddBankBranches";
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
