package asseco.CBP.pages;

import asseco.CBP.components.NavigationMenu;
import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

public class Accounts {

	private static final By SZUKAJ = By.className("toolbar-icons");
	private static final By WYSZUKAJ = ByAngular.model("ebSearchText");
	private static final By RAMKA_RACHUNKU = ByAngular.repeater("account in accountList.content | filter: {wrappedText : filterOptions.accountName}").row(0);
	private static final By ROZWNIN = By.tagName("button");
	private static final By HISTORIA = ByAngular.cssContainingText(".option-row","Historia");
	private static final By SZCZEGOLY = ByAngular.cssContainingText(".default-desktop-option","Szczegóły");
//	szczegoly rachunku
	private static final By NUMER_RACHUNKU = By.xpath("//div[@label='accounts.details.label.account_number']");
	private static final By SALDO = By.xpath("//div[@label='accounts.details.label.currentBalance']");

	private final NavigationMenu navigationMenu;
	private WebDriver driver;

	public Accounts(WebDriver driver) {
		this.driver = driver;
		this.navigationMenu = new NavigationMenu(driver);
	}

	/**
	 * wyszukanie po fragmencie nrb
	 * @param nrb
     */
	public void wyszukajRachunek(String nrb) {
		driver.findElement(SZUKAJ).click();
		driver.findElement(WYSZUKAJ).sendKeys(nrb);

	}

	/**
	 * wyszukanie po fragmencie nrb
	 * @param nrb
	 */
	public void wybierzSzczegolyRachunku(String nrb,String saldo) {
		driver.findElement(SZCZEGOLY).click();
		waitForAngularRequestsToFinish(driver);
		Assert.assertSame(driver.findElement(SALDO).getText(),"Środki własne (saldo)\n"+saldo);
	}


	/**
	 * wyszukanie po fragmencie nrb
	 * @param nrb
	 */
	public void wybierzHistorie(String nrb) {
		driver.findElement(SZCZEGOLY).click();
		driver.findElement(RAMKA_RACHUNKU).findElement(ROZWNIN).click();
		driver.findElement(HISTORIA).click();
	}


	public NavigationMenu navigationMenu() {
		return navigationMenu;
	}
}
