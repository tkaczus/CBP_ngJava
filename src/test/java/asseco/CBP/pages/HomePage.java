package asseco.CBP.pages;

import asseco.CBP.components.NavigationMenu;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

public class HomePage {
//	private static final String CBP_HOME_PAGE_URL = "http://10.17.201.79:17017/frontend-web/app/auth.html#/de/authentication/login";
	private static Properties obj = returnFromProperties();
	private static final String CBP_HOME_PAGE_URL = obj.getProperty("DB_CBP_HOME_PAGE_URL");
	private final NavigationMenu navigationMenu;
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.navigationMenu = new NavigationMenu(driver);
	}

	public HomePage open() {
		System.out.println("DB_CBP_HOME_PAGE_URL=" + CBP_HOME_PAGE_URL);
		driver.get(CBP_HOME_PAGE_URL);
		driver.manage().window().maximize();
		waitForAngularRequestsToFinish(driver);
		return this;
	}

	@NotNull
	private static Properties returnFromProperties() {
		Properties obj = new Properties();
		FileInputStream objfile = null;
		try {
			objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\db.user.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			obj.load(objfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public NavigationMenu navigationMenu() {
		return navigationMenu;
	}
}
