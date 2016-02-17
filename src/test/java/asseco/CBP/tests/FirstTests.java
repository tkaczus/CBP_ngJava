package asseco.CBP.tests;

import asseco.CBP.pages.HomePage;
import asseco.CBP.pages.Payments;
import com.orasi.utils.ReplaceLogForScreenings;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.reporters.EmailableReporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

//@Listeners({EmailableReporter.class, Screenshot.class})
@Listeners(EmailableReporter.class)
public class FirstTests {
	private static WebDriver driver;
	private FirefoxDriver fdriver;
	private HomePage homePage;
	private NgWebDriver ngWebDriver;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws IOException {
		System.out.println("@BeforeClass");
		driver = new FirefoxDriver();
//		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		ngWebDriver = new NgWebDriver(fdriver);
		homePage = new HomePage(driver).open();
		Zaloguj();
	}

	@BeforeMethod(alwaysRun = true)
	public void openHomePage() {
		System.out.println("@BeforeMethod");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("@AfterClass");
		driver.close();
		driver.quit();
	}

	@AfterSuite
	public void replaceOutput() {
		System.out.println("@AfterSuite");
		ReplaceLogForScreenings.replaceOutput();
	}

	@AfterMethod(alwaysRun = true)
	public void onTestFailure(ITestResult tr) {
		System.out.println("@AfterMethod");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		String destDir = "test-output\\screenshots";
		new File(destDir).mkdirs();
		String destFile = tr.getName()+"_"+dateFormat.format(new Date()) + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Saved [a href=\"file:///C:/Users/lukasz.tkaczyk/workspace/CBP_ngJava/test-output/ScreenShots/" + destFile+"]TestNG Reporter[/a]");
		Reporter.setEscapeHtml(false);
		Reporter.log("Saved [a href=*file:///C:/Users/lukasz.tkaczyk/workspace/CBP_ngJava/test-output/ScreenShots/" + destFile+"*]TestNG Reporter[/a]");
	}

	public void Zaloguj() throws IOException {
		Properties obj = new Properties();
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\user.properties");
		obj.load(objfile);
		System.out.println("USER_LOGIN=" + obj.getProperty("USER_LOGIN"));
		System.out.println("USER_PASSWORD=" + obj.getProperty("USER_PASSWORD"));
		homePage.navigationMenu().navigateToLoginPage().loginAs(obj.getProperty("USER_LOGIN"),
				obj.getProperty("USER_PASSWORD"));
	}

	@Test
	public void testPrzelewZwykly() throws IOException {
		Payments payments = homePage.navigationMenu().navigateToPayments();
		payments.uzupelnijPrzelewZwykly("Automat1","06 1130 0010 0000 0003 1620 0001", "22,22", "TYTUŁ");
	}

	@Test
	public void testPrzelewZwykly2() throws IOException {
		Payments payments = homePage.navigationMenu().navigateToPayments();
		payments.uzupelnijPrzelewZwykly2("Automat1","06 1130 0010 0000 0003 1620 0001", "22,22", "TYTUŁ");
	}

}
