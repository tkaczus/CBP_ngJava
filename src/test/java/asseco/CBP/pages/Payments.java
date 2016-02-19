package asseco.CBP.pages;

import asseco.CBP.components.Authorizations;
import asseco.CBP.components.Buttons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.paulhammant.ngwebdriver.ByAngular;
import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;
import asseco.CBP.components.NavigationMenu;

public class Payments {
	private static final By NOWA_PLATNOSC = Buttons.NOWA_PLATNOSC;
	private static final By TYP_PLATNOSCI = By.xpath("//div[@class='row-value']")
			.xpath("//a[@class='select2-choice ui-select-match']");
	private static final By PRZELEW_ZWYKLY = ByAngular.repeater("type in $select.items").row(0);
	private static final By ODBIORCA = ByAngular.model("formData.formModel");
	private static final By ODBIORCA2 = By.name("templateSelect");
	private static final By RACHUNEK_ODBIORCY = ByAngular.model("formData.formModel.recipientAccountNo");
	private static final By KWOTA =  ByAngular.model("formData.formModel.amount");
	private static final By KWOTA2 =  By.name("amount");
	private static final By TYTUL = ByAngular.model("formData.formModel.description");
	private static final By TYTUL2 = By.name("description");
	private static final By WYSLIJ_PRZELEW = Buttons.WYSLIJ_PRZELEW;

	private final NavigationMenu navigationMenu;
	private WebDriver driver;
	private Authorizations podpis;

	public Payments(WebDriver driver) {
		this.driver = driver;
		this.navigationMenu = new NavigationMenu(driver);
		this.podpis = new Authorizations(driver);
	}

	public void uzupelnijPrzelewZwykly(String nazwaOdbiorcy, String rachunekOdbiorcy, String kwota, String tytul) {
		driver.findElement(NOWA_PLATNOSC).click();
		waitForAngularRequestsToFinish(driver);
		driver.findElement(TYP_PLATNOSCI).click();
		new WebDriverWait(driver, 30);
		driver.findElement(PRZELEW_ZWYKLY).click();
		new WebDriverWait(driver, 60);
		driver.findElement(ODBIORCA).findElement(ODBIORCA2).sendKeys(nazwaOdbiorcy);
        new WebDriverWait(driver, 60);
		driver.findElement(RACHUNEK_ODBIORCY).sendKeys(rachunekOdbiorcy);
		new WebDriverWait(driver, 60);
		driver.findElement(KWOTA).findElement(KWOTA2).sendKeys(kwota);
		new WebDriverWait(driver, 30);
		driver.findElement(TYTUL).click();
		driver.findElement(TYTUL).findElement(TYTUL2).sendKeys(tytul);
		driver.findElement(WYSLIJ_PRZELEW).click();
		new WebDriverWait(driver, 60);
		waitForAngularRequestsToFinish(driver);
		podpis.AuthorizeTan();
	}

	public void uzupelnijPrzelewZwykly2(String nazwaOdbiorcy, String rachunekOdbiorcy, String kwota, String tytul) {
		driver.findElement(NOWA_PLATNOSC).click();
		waitForAngularRequestsToFinish(driver);
		// waitForAngularRequestsToFinish(driver);
		driver.findElement(TYP_PLATNOSCI).click();
		new WebDriverWait(driver, 30);
		driver.findElement(PRZELEW_ZWYKLY).click();
		new WebDriverWait(driver, 60);
		driver.findElement(ODBIORCA).findElement(ODBIORCA2).sendKeys(nazwaOdbiorcy);
		new WebDriverWait(driver, 60);
		driver.findElement(WYSLIJ_PRZELEW).click();
		driver.findElement(RACHUNEK_ODBIORCY).sendKeys(rachunekOdbiorcy);
		new WebDriverWait(driver, 60);
		driver.findElement(KWOTA).findElement(KWOTA2).sendKeys(kwota);
		new WebDriverWait(driver, 30);
		driver.findElement(TYTUL).click();
		driver.findElement(TYTUL).findElement(TYTUL2).sendKeys(tytul);

	}

	public NavigationMenu navigationMenu() {
		return navigationMenu;
	}
}
