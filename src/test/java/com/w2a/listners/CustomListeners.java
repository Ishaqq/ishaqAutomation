package com.w2a.listners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.mysql.cj.core.util.TestUtils;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener {

	public void onTestStart(ITestResult arg0) {
		test =rep.startTest(arg0.getName().toUpperCase());
		System.out.println(TestUtil.isTestRunnable(arg0.getName(), excel));
		//Y runmode run and N for Skip
		
	}

	public void onTestSuccess(ITestResult args0) {
		test.log(LogStatus.PASS, args0.getName().toUpperCase()+"PASS");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+ ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src="+TestUtil.screenshotName+" height=500 width=500></img></a>");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailure(ITestResult args0) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, args0.getName().toUpperCase()+"FAILED with exeption"+args0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		Reporter.log("Click to see the screenshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+ ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src="+TestUtil.screenshotName+" height=500 width=500></img></a>");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestSkipped(ITestResult args0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, args0.getName().toUpperCase()+" Test was Skipped the test is RunMode No ");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
