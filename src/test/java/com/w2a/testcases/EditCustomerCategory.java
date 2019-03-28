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

public class EditCustomerCategory extends TestBase {

	@Test(dataProvider = "getData")
	public void customerCategory(String categoryName, String receivableAccount, String advanceAccount, String description,String runmode) throws InterruptedException, IOException {
		
		 if(!TestUtil.isTestRunnable("customerCategory", excel)) {
			 throw new SkipException("Skipping the test "+"customerCategory"+" as the Run mode is No ");
		 }
		
		if (!"http://192.168.1.32:8080/HoneycombWeb/setup/customercategory".equals(driver.getCurrentUrl())) {
			Actions builder = new Actions(driver);
			WebElement bankMainMenue = driver.findElement(By.linkText("Setup"));
			builder.moveToElement(bankMainMenue).build().perform();
			click("CustomerCategory_linkText");
		}
		
		if(!runmode.equals("Y")) {
			throw new SkipException("Skipping the test case for selected entry by setting run mode NO");
		}
		
		click("AddCustomerCategory_CSS");
		
        click("addCusCategoryCode_CSS");		
		
        type("AddCustomerName_CSS", categoryName);
		comboSelect("CusReceivableAccount_CSS", receivableAccount);
		comboSelect("AddAdvanceAccount_CSS", advanceAccount);
		type("CustomerCategoryDescription_CSS", description);
		click("SaveCustomerCategory_CSS");
		Thread.sleep(3000);
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "CustomerCategory";
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