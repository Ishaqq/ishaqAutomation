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

public class PurchaseOrder extends TestBase {

	@Test(dataProvider = "getData")
	
	public void purchaseOrder(String PODate, String SupplierCode, String ContactPerson, String Payee,String Currency,String description,String ItemCode, String ItemQuantity,String ItemRate,String ItemDiscount,String ItemTax, String runmode) throws InterruptedException, IOException {
		
		 if(!TestUtil.isTestRunnable("purchaseOrder", excel)) {
			 throw new SkipException("Skipping the test "+"purchaseOrder"+" as the Run mode is No ");
		 }

		// verifyEquals("ABC", "XYZ");
		
		if (!"http://192.168.1.32:8080/HoneycombWeb/#/purchase/purchaseorder".equals(driver.getCurrentUrl())) {
			Actions builder = new Actions(driver);
			click("Purchase_CSS");
			click("PuchaseOrder_CSS");
		}
		
		if(!runmode.equals("Y")) {
			throw new SkipException("Skipping the test case for selected entry by setting run mode NO");
		}
		
		Thread.sleep(3000);
		click("AddPurchaseOrder_XPATH");
		type("PODate_CSS", PODate);
		comboSelect("POSupplierCode_CSS",SupplierCode);
		type("POContactPerson_CSS",ContactPerson);
		type("POPayee_CSS",Payee);
		int Currency1 = Integer.parseInt(Currency);
		dropDown("POCurrency_CSS",Currency1);
		type("PODescription_CSS",description);
		// Capture Screenshot
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+ ">Screenshot</a>");
		// Capture Screenshot
		Thread.sleep(3000);
		click("POAddItem_CSS");
		driver.findElement(By.cssSelector("datatable-row-wrapper:nth-child(1) > datatable-body-row > div.datatable-row-center.datatable-row-group > datatable-body-cell:nth-child(1) > div > label > input[type='checkbox']")).click();
//		List<WebElement> Rows = driver.findElements((By.xpath("//*[@id=\"page-models-panel\"]/div[2]/div/div/div[2]/div/ngx-datatable/div/datatable-body/datatable-selection/datatable-scroller/datatable-row-wrapper/datatable-body-row/div/datatable-body-cell/div")));
//		//checkbox table 
//		//List<WebElement> Rows = driver.findElements((By.xpath("//*[@id=\"page-models-panel\"]/div[2]/div/div/div[2]/div/ngx-datatable/div/datatable-body/datatable-selection/datatable-scroller/datatable-row-wrapper/datatable-body-row/div/datatable-body-cell[2]/div[contains(text(), \" EXP/HLE/1 \")]")));
//		System.out.println("No of rows = "+Rows.size());
//		
//		int z=Rows.size();
//		int i =0; 
//		for(i=0;i<z;i++)
//		  {
//		    for (int j = 0; j < Rows.size(); j++ )
//		     {
//		    	driver.findElement(By.xpath(Rows+"datatable-body-cell/div[contains(text(), \" EXP/HLE/1 \")])).click();
//		     }
//		  }
//		//select from table
		Thread.sleep(3000);
		
		click("POIems_CSS");
		driver.quit();
		click("POItemSelect_CSS");
		type("POQuantity_CSS",ItemQuantity);
		type("POitemRate_CSS",ItemRate);
		type("POItemDiscount_CSS",ItemDiscount);
		type("POitemTax_CSS",ItemTax);
				
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
		Thread.sleep(3000);
		
		click("POSave_CSS");
		Thread.sleep(3000);
		
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "purchaseOrder";
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