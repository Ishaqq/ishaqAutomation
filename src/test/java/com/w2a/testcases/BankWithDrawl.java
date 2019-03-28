package com.w2a.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class BankWithDrawl extends TestBase {
	@Test
	public void bankWithDrawl() throws InterruptedException {
		if(!TestUtil.isTestRunnable("bankWithDrawl", excel)) {
			 throw new SkipException("Skipping the test "+"bankWithDrawl"+" as the Run mode is No ");
		 }
		Actions builder = new Actions(driver);
		WebElement bankMainMenue = driver.findElement(By.linkText("Bank"));
		builder.moveToElement(bankMainMenue).build().perform();
		click("withDrawListing_linkText");
		click("checkDatecalendar_CSS");
		Thread.sleep(3000);
	}

}
