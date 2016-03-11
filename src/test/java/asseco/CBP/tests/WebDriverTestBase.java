package asseco.CBP.tests;

import asseco.CBP.pages.HomePage;
import com.google.common.io.Files;
import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
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
    }

    @AfterMethod(alwaysRun = true)
    public void onTestFailure(ITestResult result) throws IOException {
        System.out.println("@AfterMethod");
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        if (!result.isSuccess()) {
            File screenshot1 = new File("test-output\\html\\" + result.getMethod().getMethodName() + ".png");
//            screenshot1.delete();
            File screenshotTempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshotTempFile, screenshot1);
            } catch (Exception e) {
                System.out.println(e);
            }
//            Reporter.log("<a href=\"" + screenshot1.getCanonicalPath() + "\">" + result.getMethod().getMethodName() + "Screenshot</a>");
            Reporter.log("<font color=\"#00bfff\">" + result.getMethod().getMethodName() + "</font><br />");
            Reporter.log("<img src=\"" + screenshot1.getName() + "\" style=\"width:1051px;height:679px;\" alt=\"\"/><br />");
            Reporter.setEscapeHtml(false);
            Reporter.log("<a>");
        }
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
            } else {
                System.out.println("!! Nie znalazło na lisćie" + item.getText());
            }
        }
    }
}
