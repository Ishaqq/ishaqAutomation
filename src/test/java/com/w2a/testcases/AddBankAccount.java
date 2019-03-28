package com.w2a.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class AddBankAccount extends TestBase {
	@Test(dataProvider = "getData")
	public void addBankAccount(int type, String bankAccountNo, int bankName, String bankAccountTitle, int branchName,
			int currency) throws InterruptedException {

		Actions builder = new Actions(driver);
		WebElement bankMainMenue = driver.findElement(By.linkText("Bank"));
		builder.moveToElement(bankMainMenue).build().perform();

		log.debug("bank menue hovered");

		click("bankAccountListing_CSS");
		click("AddBankAccountBtn_CSS");
		dropDown("type_CSS", type);
		type("bankAccountNo_CSS", bankAccountNo);
		dropDown("bankName_CSS", bankName);
		type("bankAccountTitle_CSS", bankAccountTitle);
		dropDown("branchName_CSS", branchName);
		dropDown("currency_CSS", currency);
		click("saveBankAccount_CSS");
		Thread.sleep(3000);
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "AddBankAccount";
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
