package asseco.CBP.pages;

import asseco.CBP.components.NavigationMenu;
import org.openqa.selenium.WebDriver;

import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

public class HomePage {
	private static final String CBP_HOME_PAGE_URL = "http://10.17.201.79:17017/frontend-web/app/auth.html#/de/authentication/login";
	private final NavigationMenu navigationMenu;
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.navigationMenu = new NavigationMenu(driver);
	}

	public HomePage open() {
		driver.get(CBP_HOME_PAGE_URL);
		driver.manage().window().maximize();
		waitForAngularRequestsToFinish(driver);
		return this;
	}

	public NavigationMenu navigationMenu() {
		return navigationMenu;
	}
}
