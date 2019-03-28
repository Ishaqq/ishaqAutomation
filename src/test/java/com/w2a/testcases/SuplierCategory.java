package com.w2a.testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

import javafx.scene.control.ComboBox;

public class SuplierCategory extends TestBase {

	@Test(dataProvider = "getData")
	public void suplierCategory(String categoryName, String receivableAccount, String advanceAccount, String description,String runmode) throws InterruptedException, IOException {
		
		 if(!TestUtil.isTestRunnable("suplierCategory", excel)) {
			 throw new SkipException("Skipping the test "+"suplierCategory"+" as the Run mode is No ");
		 }
		
		if (!"http://192.168.1.32:8080/HoneycombWeb/setup/suppliercategory".equals(driver.getCurrentUrl())) {
			Actions builder = new Actions(driver);
//			WebElement bankMainMenue = driver.findElement(By.linkText("Setup"));
//			builder.moveToElement(bankMainMenue).build().perform();
			click("Setup_XPATH");
			click("SuplierCategory_linkText");
		}
		
		if(!runmode.equals("Y")) {
			throw new SkipException("Skipping the test case for selected entry by setting run mode NO");
		}
		
		click("AddSuplierCategory_CSS");
        click("addSupCategoryCode_CSS");
        type("AddSuplierCatName_CSS", categoryName);
		comboSelect("SupReceivableAccount_CSS", receivableAccount);
		comboSelect("AddSupAdvanceAccount_CSS", advanceAccount);
		type("SuplierCategoryDescription_CSS", description);
		click("SaveSuplierCategory_CSS");
		Thread.sleep(3000);
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "SuplierCategory";
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