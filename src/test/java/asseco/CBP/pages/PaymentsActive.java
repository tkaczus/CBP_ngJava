package asseco.CBP.pages;

import asseco.CBP.components.NavigationMenu;
import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PaymentsActive {
//	ng-click="showSearchBox($event)"
//	class="toolbar-icons"
	private static final By SZUKAJ = By.className("toolbar-icons");
	private static final By WYSZUKAJ = ByAngular.model("ebSearchText");
//	account in accountList.content | filter: {wrappedText : filterOptions.accountName}
	private static final By RAMKA_PLATNOSCI= ByAngular.repeater("payment in paymentList.content | filter:{wrappedText : filterOptions.paymentName}").row(0);
	private static final By ROZWNIN = By.tagName("button");
	private static final By EDYTUJ = ByAngular.cssContainingText(".option-row","Edytuj");
	private static final By ANULUJ = ByAngular.cssContainingText(".option-row","Anuluj");
	private static final By SZCZEGOLY = ByAngular.cssContainingText(".option-label","Szczegóły");

	private final NavigationMenu navigationMenu;
	private WebDriver driver;

	public PaymentsActive(WebDriver driver) {
		this.driver = driver;
		this.navigationMenu = new NavigationMenu(driver);
	}



	/**
	 * wyszukanie po fragmencie tekstu
	 * @param textDoWyszukania
     */
	public void wyszukajPlatnoscPoTekscie(String textDoWyszukania) {
		driver.findElement(SZUKAJ).click();
		driver.findElement(WYSZUKAJ).sendKeys(textDoWyszukania);
		Assert.assertEquals(driver.findElement(RAMKA_PLATNOSCI).isDisplayed(),true);
	}

	/**
	 * wybierz przycisk Edytuj
	 */
	public void kliknijWEdycje(){
		driver.findElement(RAMKA_PLATNOSCI).findElement(ROZWNIN).click();
		driver.findElement(EDYTUJ).click();
	}

	/**
	 * wybierz przycisk Anuluj
	 */
	public void kliknijWAnuluj(){
		driver.findElement(RAMKA_PLATNOSCI).findElement(ROZWNIN).click();
		driver.findElement(ANULUJ).click();
	}




	public NavigationMenu navigationMenu() {
		return navigationMenu;
	}
}
