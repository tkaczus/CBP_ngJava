package asseco.CBP.pages;

import asseco.CBP.components.Buttons;
import asseco.CBP.components.NavigationMenu;
import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PaymentsActive {

	private static final By SZUKAJ = By.className("toolbar-icons");
	private static final By WYSZUKAJ = ByAngular.model("ebSearchText");
	private static final By RAMKA_PLATNOSCI= ByAngular.repeater("payment in paymentList.content | filter:{wrappedText : filterOptions.paymentName}").row(0);
	private static final By ROZWNIN = By.tagName("button");
	private static final By EDYTUJ = ByAngular.cssContainingText(".option-row","Edytuj");
	private static final By ANULUJ = ByAngular.cssContainingText(".option-row","Anuluj");
	private static final By WROC = ByAngular.cssContainingText(".controls-container","Wróć do pulpitu");
	private static final By NASTEPNE = Buttons.NASTEPNE;

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
	public void wyszukajPlatnoscPoTekscie(String textDoWyszukania) throws InterruptedException {
		boolean czyZnalazlo=false;
		int liczbaProb = 1;

		while (!czyZnalazlo) {
			driver.findElement(SZUKAJ).click();
			driver.findElement(WYSZUKAJ).sendKeys(textDoWyszukania);
			System.out.println("wyszukuje strone nr="+liczbaProb);
			try {
				Assert.assertEquals(driver.findElement(RAMKA_PLATNOSCI).isDisplayed(), true);
				czyZnalazlo=true;
			}catch (org.openqa.selenium.NoSuchElementException e){

			} finally {
				Thread.sleep(10000);
				liczbaProb++;
				if (!czyZnalazlo)
				driver.findElement(NASTEPNE).click();
			}
		}
		Assert.assertEquals(driver.findElement(RAMKA_PLATNOSCI).isDisplayed(), true);
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
		driver.findElement(WROC).click();
	}

	public NavigationMenu navigationMenu() {
		return navigationMenu;
	}
}
