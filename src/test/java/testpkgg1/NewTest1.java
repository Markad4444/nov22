package testpkgg1;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import base.BasClass;
import pommpkg.Homepge;
import pommpkg.LogPage;
import util.Utilclass;

public class NewTest1 {
	ExtentHtmlReporter extentReporter;
	ExtentReports report;
	ExtentTest test;
	WebDriver ldriver;
	LogPage lp;
	Homepge hp;

	@BeforeClass
	public void beforeClass() {
		ldriver = BasClass.getwebdriver();
		extentReporter = BasClass.getHtmlReporter();
		report = BasClass.getExtentReport();
		test = BasClass.getTestForReporter("NewTest1");
	}

	@BeforeMethod
	public void beforeMethod() throws EncryptedDocumentException, IOException {

		lp = new LogPage(ldriver);
		hp = new Homepge(ldriver);

	}

	@Test(priority = 1)
	public void toverifyloginsuccess() throws EncryptedDocumentException, IOException, InterruptedException {
		lp.entermail();
		lp.enterpass();
		lp.clickaction();
		Thread.sleep(2000);
		hp.tomovetowordsprofilename();
		String txt = hp.togetprofilename();
		System.out.println(txt);
		Assert.assertEquals(txt, "vitthal");
	}

	@Test(priority = 3)
	public void toverifyhomepage() throws InterruptedException {
		Thread.sleep(100);
		hp.tomovesubsubprofile();
		hp.toclicksubprofile();
		Thread.sleep(1000);
		String text1 = hp.togetlogouttext();
		System.out.println(text1);
		Assert.assertEquals(text1, "Logout");

	}

	@Test(priority = 2)
	public void toverifyothertab() throws InterruptedException {
		Thread.sleep(1000);
		String text3 = hp.togetmoretext();
		

//		String text4 = hp.togetcarttext();
//		System.out.println(text4);
//		Thread.sleep(1000);
//
//
//		String text5 = hp.togetexploretext();
//		System.out.println(text5);

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test is passed" + result.getName());
		} else {
			String path = Utilclass.getScreenshot(ldriver, result.getName());
			test.log(Status.FAIL, "Test is failed " + result.getName(),
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}

	}

	@AfterClass
	public void afterClass() {
		report.flush();
		hp.tockicklogout();
	}

}
