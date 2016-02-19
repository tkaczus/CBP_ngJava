package asseco.CBP.pages;

import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private static final By USERNAME = By.id("j_username");
	private static final By PASSWORD = By.id("password");
	private static final By LOGIN_SUBMIT_BUTTON = By.id("loginSubmitButton");
	private static final By LOGIN_NAME = By.className("login-name");
	private static final By WYLOGUJ = ByAngular.buttonText("Wyloguj");
	private static final By ZALOGUJ_PONOWNIE = ByAngular.buttonText("Zaloguj ponownie");
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage loginAs(String username, String password) {
		driver.findElement(USERNAME).sendKeys(username);
		driver.findElement(LOGIN_SUBMIT_BUTTON).click();
		waitForAngularRequestsToFinish(driver);
		driver.findElement(PASSWORD).sendKeys(password);
		driver.findElement(LOGIN_SUBMIT_BUTTON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-name")));
		return new HomePage(driver);
	}

	public HomePage logOut() {
		driver.findElement(WYLOGUJ).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ZALOGUJ_PONOWNIE));
		return new HomePage(driver);
	}

	public boolean isLoaded() {
		return driver.findElement(LOGIN_NAME).isDisplayed();
	}

}
