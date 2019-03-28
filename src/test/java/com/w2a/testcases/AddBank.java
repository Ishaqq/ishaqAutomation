package com.w2a.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class AddBank extends TestBase {
	@Test(dataProvider = "getData")
	public void addBank(String addAccountTitle) throws InterruptedException {

		if (!"http://192.168.1.32:8080/HoneycombWeb/bank/banks".equals(driver.getCurrentUrl())) {
			Actions builder = new Actions(driver);
			WebElement bankMainMenue = driver.findElement(By.linkText("Bank"));
			builder.moveToElement(bankMainMenue).build().perform();

			log.debug("bank menue hovered");
			click("banklisting_CSS");
		}

		click("addbank_CSS");
		click("generateBankCode_CSS");
		// bankAccountTitle
		type("bankAccountTitle_CSS", addAccountTitle);
		click("bankSave_CSS");
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "AddBank";
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
