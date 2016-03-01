package asseco.CBP.tests;

import asseco.CBP.pages.HomePage;
import com.orasi.utils.ReplaceLogForScreenings;
import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


@Listeners(EmailableReporter.class)
public class WebDriverTestBase {
    public static WebDriver driver;
    public FirefoxDriver fdriver;
    public HomePage homePage;
    public NgWebDriver ngWebDriver;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        System.out.println("@BeforeClass");
        driver = new FirefoxDriver();
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
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
        String destFile = tr.getName() + "_" + dateFormat.format(new Date()) + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved [a href=\"file:///C:/Users/lukasz.tkaczyk/workspace/CBP_ngJava/test-output/ScreenShots/" + destFile + "]TestNG Reporter[/a]");
        Reporter.setEscapeHtml(false);
        Reporter.log("Saved [a href=*file:///C:/Users/lukasz.tkaczyk/workspace/CBP_ngJava/test-output/ScreenShots/" + destFile + "*]TestNG Reporter[/a]");
    }

    public void Zaloguj() throws IOException {
        Properties obj = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\db.user.properties");
        obj.load(objfile);
        System.out.println("USER_LOGIN=" + obj.getProperty("USER_LOGIN"));
        System.out.println("USER_PASSWORD=" + obj.getProperty("USER_PASSWORD"));
        homePage.navigationMenu().navigateToLoginPage().loginAs(obj.getProperty("USER_LOGIN"),
                obj.getProperty("USER_PASSWORD"));
    }

    public static void findNgRepeatAndClick(String ngRepeat, String searchText) {
        List<WebElement> rachunki = driver.findElements(ByAngular.repeater(ngRepeat));
        for (WebElement item : rachunki) {
            if (item.getText().contains(searchText)) {
                System.out.println("Wybralo z listy element=" + item.getText());
                item.click();
                break;
            }
            else
            {
                System.out.println("!! Nie znalazło na lisćie" + item.getText());
            }
        }
    }
}
