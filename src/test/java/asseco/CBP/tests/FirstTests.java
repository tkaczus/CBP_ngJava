package asseco.CBP.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

import com.orasi.utils.GuiTestListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.paulhammant.ngwebdriver.NgWebDriver;

import asseco.CBP.pages.HomePage;
import asseco.CBP.pages.Payments;

@Listeners({GuiTestListener.class})
public class FirstTests {
	private WebDriver driver;
	private FirefoxDriver fdriver;
	private HomePage homePage;
	private NgWebDriver ngWebDriver;

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		ngWebDriver = new NgWebDriver(fdriver);
	}

	@BeforeMethod(alwaysRun = true)
	public void openHomePage() {
		homePage = new HomePage(driver).open();
		waitForAngularRequestsToFinish(driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

//	@AfterMethod
//	public void onTestFailure(ITestResult tr) {
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
//		String destDir = "test-output\\screenshots";
//		new File(destDir).mkdirs();
//		String destFile = dateFormat.format(new Date()) + ".png";
//		try {
//			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Reporter.setEscapeHtml(false);
//		Reporter.log("<a href='" + scrFile.getAbsolutePath() + "'>screenshot</a>");
////		Reporter.log("Saved <a href=../screenshots/" + scrFile + ">Screenshot</a>");
//	}

	@Test
	public void testZalogujiPrzelewZwykly() throws IOException {
		Properties obj = new Properties();
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\resources\\user.properties");
		obj.load(objfile);
		System.out.println("USER_LOGIN=" + obj.getProperty("USER_LOGIN"));
		System.out.println("USER_PASSWORD=" + obj.getProperty("USER_PASSWORD"));
		homePage.navigationMenu().navigateToLoginPage().loginAs(obj.getProperty("USER_LOGIN"),
				obj.getProperty("USER_PASSWORD"));
		Payments payments = homePage.navigationMenu().navigateToPayments();
		payments.uzupelnijPrzelewZwykly("Automat1","06 1130 0010 0000 0003 1620 0001", "22,22", "TYTUŁ");
	}

}
