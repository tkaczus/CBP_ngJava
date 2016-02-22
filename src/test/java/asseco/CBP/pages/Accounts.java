package asseco.CBP.pages;

import asseco.CBP.components.NavigationMenu;
import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Accounts {
//	ng-click="showSearchBox($event)"
//	class="toolbar-icons"
	private static final By SZUKAJ = By.className("toolbar-icons");
	private static final By WYSZUKAJ = ByAngular.model("ebSearchText");
//	account in accountList.content | filter: {wrappedText : filterOptions.accountName}
	private static final By RAMKA_RACHUNKU = ByAngular.repeater("account in accountList.content | filter: {wrappedText : filterOptions.accountName}").row(0);
	private static final By ROZWNIN = By.tagName("button");
	private static final By HISTORIA = ByAngular.cssContainingText(".option-row","Historia");

	private final NavigationMenu navigationMenu;
	private WebDriver driver;

	public Accounts(WebDriver driver) {
		this.driver = driver;
		this.navigationMenu = new NavigationMenu(driver);
	}

	public void wyszukajRachunek(String nrb) {
		driver.findElement(SZUKAJ).click();
		driver.findElement(WYSZUKAJ).sendKeys(nrb);
		driver.findElement(RAMKA_RACHUNKU).findElement(ROZWNIN).click();
		driver.findElement(HISTORIA).click();
	}

	public NavigationMenu navigationMenu() {
		return navigationMenu;
	}
}
