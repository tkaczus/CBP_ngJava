package asseco.CBP.pages;

import asseco.CBP.components.Authorizations;
import asseco.CBP.components.Buttons;
import com.orasi.utils.date.DateTimeConversion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.paulhammant.ngwebdriver.ByAngular;
import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;
import static org.openqa.selenium.By.tagName;

import asseco.CBP.components.NavigationMenu;
import org.seleniumhq.selenium.fluent.FluentWebDriver;

import java.util.List;

public class Payments {
	private static final By NOWA_PLATNOSC = Buttons.NOWA_PLATNOSC;
	private static final By TYP_PLATNOSCI = By.xpath("//div[@class='row-value']")
			.xpath("//a[@class='select2-choice ui-select-match']");
	private static final By LIST= By.xpath("a[@class='select2-choice ui-select-match']");
	private static final By LIST2= By.tagName("a");
	private static final By PRZELEW_ZWYKLY = ByAngular.repeater("type in $select.items").row(0);
	private static final By PRZELEW_WLASNY = ByAngular.repeater("type in $select.items").row(1);
	private static final By PRZELEW_EUROPEJSKI = ByAngular.repeater("type in $select.items").row(2);
	private static final By PRZELEW_ZAGRANICZNY = ByAngular.repeater("type in $select.items").row(3);
	private static final By PRZELEW_PODATKU = ByAngular.repeater("type in $select.items").row(4);
	private static final By PRZELEW_DO_ZUS = ByAngular.repeater("type in $select.items").row(5);
	private static final By ODBIORCA = ByAngular.model("formData.formModel");
	private static final By ODBIORCA2 = By.name("templateSelect");
	private static final By RACHUNEK_ODBIORCY = ByAngular.model("formData.formModel.recipientAccountNo");
	private static final By KWOTA =  ByAngular.model("formData.formModel.amount");
	private static final By KWOTA_WEWNATRZ =  By.name("amount");
	private static final By TYTUL = ByAngular.model("formData.formModel.description");
	private static final By TYTUL_WEWNATRZ = By.name("description");
	private static final By WYSLIJ_PRZELEW = Buttons.WYSLIJ_PRZELEW;
	private static final By POTWIERDZENIE = By.className("dialog-message");
	private static final By UTWORZ_NOWA_PLATNOSC = Buttons.UTWORZ_NOWA_PLATNOSC;
	private static final By DATA = ByAngular.model("formData.formModel.realizationDate"); //24.02.2016
	private static final By POWTARZAJ = By.name("optionalCheck");
//PRZELEW_WLASNY
	private static final By Z_RACHUNKU = ByAngular.model("formData.formModel.remitterAccountId");
	private static final By NA_RACHUNEK = ByAngular.model("formData.formModel.beneficiaryAccountId");
//	ng-repeat="account in $select.items"
//	PRZELEW_EUROP
	private static final By ODBIORCA_ADRES = ByAngular.model("formData.formModel.recipientAddress");
	private static final By ODBIORCA_ADRES2 = By.name("address");
	private static final By BICSWIFT = ByAngular.model("formData.formModel.recipientSwift");
	private static final By PRZEWALUTOWANIE = By.name("exchange");
//US
	private static final By URZAD_SKARBOWY = ByAngular.model("formData.formModel.recipientName");
	private static final By GRUPA_PODATKOWA = ByAngular.model("formData.taxGroupId");
	private static final By SYMBOL_FORMULARZA = ByAngular.model("ormData.formModel.formCode");
	private static final By TYP_OKRESU = ByAngular.model("formData.formModel.periodType");
	private static final By NUMER_OKRESU = ByAngular.model("formData.formModel.periodNumber");
	private static final By ROK_OKRESU = ByAngular.model("formData.formModel.periodYear");
	private static final By TYP_IDENTYFIKATORA = ByAngular.model("formData.formModel.idType");
	private static final By IDENTYFIKATOR = ByAngular.model("formData.formModel.idNumber");
	private static final By IDENTYFIKACJA_ZOBOWIAZAN = ByAngular.model("formData.formModel.obligationId");
//ZUS
	private static final By NUMER_RACHUNKU_ZUS =RACHUNEK_ODBIORCY;
	private static final By TYP_WPLATY = ByAngular.model("formData.formModel.paymentType");
	private static final By DEKLARACJA = ByAngular.model("formData.formModel.declarationDate");
	private static final By NUMER_DEKLARACJI = ByAngular.model("formData.formModel.declarationNo");
	private static final By NIP_PLATNIKA = ByAngular.model("formData.formModel.nip");
	private static final By TYP_DRUGIEGO_IDENTYFIKATORA = ByAngular.model("formData.formModel.secondaryIdType");
	private static final By IDENTYFIKATOR_UZUPELNIAJACY = ByAngular.model("formData.formModel.secondaryIdTypeModel");
	private static final By NUMER_DECYZJI = ByAngular.model("formData.formModel.decisionNo");

	private final NavigationMenu navigationMenu;
	private WebDriver driver;
	private Authorizations podpis;
	private Accounts rachunki;
	private String zRachunku;

	public Payments(WebDriver driver) {
		this.driver = driver;
		this.navigationMenu = new NavigationMenu(driver);
		this.podpis = new Authorizations(driver);
		this.rachunki = new Accounts(driver);
	}

	public void uzupelnijPrzelewZwykly(String nazwaOdbiorcy, String rachunekOdbiorcy, String kwota, String tytul,String dataPrzelewu,boolean czyPowtarzac) {
		driver.findElement(NOWA_PLATNOSC).click();
		waitForAngularRequestsToFinish(driver);
		driver.findElement(TYP_PLATNOSCI).click();
		driver.findElement(PRZELEW_ZWYKLY).click();
		driver.findElement(ODBIORCA).findElement(ODBIORCA2).sendKeys(nazwaOdbiorcy);
		driver.findElement(RACHUNEK_ODBIORCY).sendKeys(rachunekOdbiorcy);
		driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
		driver.findElement(TYTUL).click();
		driver.findElement(TYTUL).findElement(TYTUL_WEWNATRZ).sendKeys(tytul);
		if (czyPowtarzac)
			driver.findElement(POWTARZAJ).click();
		if (dataPrzelewu!=null) {
			driver.findElement(DATA).clear();
			driver.findElement(DATA).sendKeys(DateTimeConversion.ConvertToDateDDMMYYYY(dataPrzelewu));
		}
		driver.findElement(WYSLIJ_PRZELEW).click();
		waitForAngularRequestsToFinish(driver);
		podpis.AuthorizeTan();
		waitForAngularRequestsToFinish(driver);
		driver.findElement(UTWORZ_NOWA_PLATNOSC).click();
		waitForAngularRequestsToFinish(driver);
		rachunki.navigationMenu().navigateToAccounts();
		rachunki.wyszukajRachunek("581910");
	}

	public void uzupelnijPrzelewWlasny(String zRachunku, String naRahunek, String kwota, String tytul,String dataPrzelewu,boolean czyPowtarzac) {
		driver.findElement(NOWA_PLATNOSC).click();
		waitForAngularRequestsToFinish(driver);
		driver.findElement(TYP_PLATNOSCI).click();
		driver.findElement(PRZELEW_WLASNY).click();
		waitForAngularRequestsToFinish(driver);
		driver.findElement(Z_RACHUNKU).findElement(LIST2).click();
		findNgRepeatAndClick("account in $select.items",zRachunku);
		driver.findElement(NA_RACHUNEK).findElement(LIST2).click();
		findNgRepeatAndClick("account in $select.items",naRahunek);
		driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
		driver.findElement(TYTUL).click();
		driver.findElement(TYTUL).findElement(TYTUL_WEWNATRZ).sendKeys(tytul);
		if (czyPowtarzac)
			driver.findElement(POWTARZAJ).click();
		if (dataPrzelewu!=null) {
			driver.findElement(DATA).clear();
			driver.findElement(DATA).sendKeys(DateTimeConversion.ConvertToDateDDMMYYYY(dataPrzelewu));
		}
		driver.findElement(WYSLIJ_PRZELEW).click();
		waitForAngularRequestsToFinish(driver);
		podpis.AuthorizeTan();
		waitForAngularRequestsToFinish(driver);
		driver.findElement(UTWORZ_NOWA_PLATNOSC).click();
		waitForAngularRequestsToFinish(driver);
		rachunki.navigationMenu().navigateToAccounts();
		rachunki.wyszukajRachunek(zRachunku);
	}

	public void uzupelnijPrzelewEuropejski(String nazwaOdbiorcy,String adresOdbiorcy, String rachunekOdbiorcy, String kodBicSwift,String zRachunku, String kwota,String tytul, String dataPrzelewu) {
		driver.findElement(NOWA_PLATNOSC).click();
		waitForAngularRequestsToFinish(driver);
		driver.findElement(TYP_PLATNOSCI).click();
		driver.findElement(PRZELEW_EUROPEJSKI).click();
		waitForAngularRequestsToFinish(driver);
		driver.findElement(ODBIORCA).findElement(ODBIORCA2).sendKeys(nazwaOdbiorcy);
		driver.findElement(ODBIORCA_ADRES).findElement(ODBIORCA_ADRES2).sendKeys(adresOdbiorcy);
		driver.findElement(BICSWIFT).sendKeys(kodBicSwift);
		driver.findElement(RACHUNEK_ODBIORCY).sendKeys(rachunekOdbiorcy);
		driver.findElement(Z_RACHUNKU).findElement(LIST2).click();
		findNgRepeatAndClick("account in $select.items",zRachunku);
		driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
		driver.findElement(TYTUL).click();
		driver.findElement(TYTUL).findElement(TYTUL_WEWNATRZ).sendKeys(tytul);
		if (dataPrzelewu!=null) {
			driver.findElement(DATA).clear();
			driver.findElement(DATA).sendKeys(DateTimeConversion.ConvertToDateDDMMYYYY(dataPrzelewu));
		}
		driver.findElement(PRZEWALUTOWANIE).click();
		driver.findElement(WYSLIJ_PRZELEW).click();
		waitForAngularRequestsToFinish(driver);
		podpis.AuthorizeTan();
		waitForAngularRequestsToFinish(driver);
		driver.findElement(UTWORZ_NOWA_PLATNOSC).click();
		waitForAngularRequestsToFinish(driver);
		rachunki.navigationMenu().navigateToAccounts();
		rachunki.wyszukajRachunek(zRachunku);
	}

	public void findNgRepeatAndClick(String ngRepeat,String searchText){
		List<WebElement> rachunki = driver.findElements(ByAngular.repeater(ngRepeat));
		for (WebElement item : rachunki) {
			if (item.getText().contains(searchText)) {
				System.out.println("Wybralo z listy element="+item.getText());
				item.click();
				break;
			}
		}
	}

	public NavigationMenu navigationMenu() {
		return navigationMenu;
	}
}
